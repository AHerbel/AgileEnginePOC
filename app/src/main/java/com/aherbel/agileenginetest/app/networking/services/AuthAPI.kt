package com.aherbel.agileenginetest.app.networking.services

import com.aherbel.agileenginetest.app.networking.AuthResponse
import com.google.gson.JsonObject
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthAPI {
    
    @POST("auth")
    suspend fun auth(@Body body: JsonObject): AuthResponse
    
}