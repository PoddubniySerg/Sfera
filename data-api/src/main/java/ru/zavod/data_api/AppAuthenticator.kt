package ru.zavod.data_api

import android.util.Log
import io.tasknet.data_api_tasknet_io.di.TokenProviderApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import ru.zavod.app_core.model.Token
import ru.zavod.data_api.dto.RefreshTokenDto
import javax.inject.Inject

class AppAuthenticator @Inject constructor(
    private val tokenProvider: TokenProviderApi,
    private val apiProvider: ApiProvider
) : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {
        return when {
            refreshable(response = response) -> refresh(response = response)
            response.code() == UNAUTHORIZED_CODE -> removeToken()
            else -> buildRequest(response = response)
        }
    }

    private fun refreshable(response: Response): Boolean {
        return response.code() == UNAUTHORIZED_CODE
                && response.request().header(JWT_TOKEN_HEADER) != null
    }

    private fun refresh(response: Response): Request? {
        val refreshToken = tokenProvider.getToken()?.refresh ?: return null
        refreshToken(refreshToken = refreshToken)
        return buildRequest(response = response)
    }

    private fun removeToken(): Request? {
        tokenProvider.removeToken()
        return null
    }

    private fun refreshToken(refreshToken: String) {
        runBlocking(Dispatchers.IO) {
            try {
                val params = RefreshTokenDto(refreshToken = refreshToken)
                val response = apiProvider.api.refreshToken(refreshTokenDto = params)
                val token = Token(
                    access = response.body()?.access,
                    refresh = response.body()?.refresh,
                    userExist = response.body()?.userId != null
                )
                tokenProvider.saveToken(token = token)
            } catch (e: Exception) {
                Log.e("LogError", e.toString())
            }
        }
    }

    private fun buildRequest(response: Response): Request {
        val token = tokenProvider.getToken() ?: return response.request().newBuilder().build()
        return response
            .request()
            .newBuilder()
            .addHeader(JWT_TOKEN_HEADER, "$JWT_TOKEN_PREFIX${token.access}")
            .build()
    }
}