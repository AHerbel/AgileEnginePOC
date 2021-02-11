package com.aherbel.agileenginetest.app.networking

import com.google.gson.JsonObject
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    
    override fun intercept(chain: Interceptor.Chain): Response {
        val accessToken = RetrofitClient.token.orEmpty()
        val request = chain.request().newBuilder()
            .addHeader(HEADER_AUTHORIZATION, accessToken)
            .build()
        val response = chain.proceed(request)
        
        if (response.code == UNAUTHORIZED) {
            val newToken: String = runBlocking {
                val authResponse = requestToken()
                return@runBlocking authResponse.token.orEmpty()
            }
            if (newToken.isNotEmpty()) {
                response.close()
                val newRequest = chain.request().newBuilder()
                    .addHeader(HEADER_AUTHORIZATION, newToken)
                    .build()
                return chain.proceed(newRequest)
            }
        }
        
        return response
    }
    
    private suspend fun requestToken(): AuthResponse {
        val body = JsonObject().apply { addProperty("apiKey", API_KEY) }
        val authResponse = RetrofitClient.authApi.auth(body)
        RetrofitClient.token = authResponse.token
        return authResponse
    }
    
    companion object {
        private const val HEADER_AUTHORIZATION = "Authorization"
        private const val UNAUTHORIZED = 401
        private const val API_KEY = "23567b218376f79d9415"
    }
    
}