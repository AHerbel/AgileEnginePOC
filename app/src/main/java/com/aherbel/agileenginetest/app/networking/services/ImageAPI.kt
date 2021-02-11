package com.aherbel.agileenginetest.app.networking.services

import com.aherbel.agileenginetest.domain.model.Image
import com.aherbel.agileenginetest.domain.responses.ImagesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ImageAPI {
    
    @GET("images")
    suspend fun getImages(
        @Query("page") page: Int,
        @Query("limit") limit: Int?,
    ): ImagesResponse
    
    @GET("images/{id}")
    suspend fun getImage(@Path("id") id: String): Image
    
}