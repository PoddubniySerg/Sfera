package ru.zavod.data_api

import io.tasknet.data_api_tasknet_io.di.TokenProviderApi
import okhttp3.Interceptor
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val tokenProvider: TokenProviderApi
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return try {
            val modifiedRequest = modifyRequest(chain = chain)
            chain.proceed(modifiedRequest)
        } catch (e: Exception) {
            buildResponse(request = chain.request(), throwable = e)
        }
    }

    private fun modifyRequest(chain: Interceptor.Chain): Request {
        val modifiedRequestBuilder = chain.request().newBuilder()
        val token = tokenProvider.getToken()
        if (token != null) {
            modifiedRequestBuilder.addHeader(JWT_TOKEN_HEADER, "$JWT_TOKEN_PREFIX${token.access}")
        }
        return modifiedRequestBuilder.build()
    }

    private fun buildResponse(request: Request, throwable: Throwable): Response {
        val builder = Response.Builder()
            .request(request)
            .protocol(Protocol.HTTP_1_1)
            .code(DEFAULT_RESPONSE_CODE)
        throwable.message?.let {
            builder.message(it).body(ResponseBody.create(null, it))
        }
        return builder.build()
    }
}
