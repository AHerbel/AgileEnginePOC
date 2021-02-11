package com.aherbel.agileenginetest.app.networking

import com.aherbel.agileenginetest.app.networking.services.AuthAPI
import com.aherbel.agileenginetest.app.networking.services.ImageAPI
import com.google.gson.FieldNamingPolicy
import com.google.gson.FieldNamingStrategy
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitClient {
    
    private const val BASE_URL = "http://interview.agileengine.com/"
    
    var token: String? = null
    
    val imageApi: ImageAPI
    val authApi: AuthAPI
    
    init {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }
        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(AuthInterceptor())
            .build()
        
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        
        imageApi = retrofit.create()
        authApi = retrofit.create()
    }
    
}