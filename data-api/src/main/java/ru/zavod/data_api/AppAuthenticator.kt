package ru.zavod.data_api

import android.util.Log
import io.tasknet.data_api_tasknet_io.di.TokenProviderApi
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
        return try {
            val refreshToken = tokenProvider.getToken()?.refresh!!
            val token = refresh(refreshToken = refreshToken)
            buildRequest(response = response, token = token)
        } catch (e: Exception) {
            Log.e("AppAuthenticator", e.stackTrace.toString())
            tokenProvider.removeToken()
            null
        }
    }

    private fun refresh(refreshToken: String): Token {
        var token: Token?
        val params = RefreshTokenDto(refreshToken = refreshToken)
        runBlocking {
            val response = apiProvider.api.refreshToken(refreshTokenDto = params)
            token = Token(
                access = response.body()?.access,
                refresh = response.body()?.refresh,
                userExist = response.body()?.userId != null
            )
        }
        tokenProvider.saveToken(token = token!!)
        return token!!
    }

    private fun buildRequest(response: Response, token: Token): Request {
        return response
            .request()
            .newBuilder()
            .addHeader(JWT_TOKEN_HEADER, "$JWT_TOKEN_PREFIX${token.access}")
            .build()
    }
}