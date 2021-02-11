package com.aherbel.agileenginetest.app.datasources

import com.aherbel.agileenginetest.app.networking.RetrofitClient
import com.aherbel.agileenginetest.domain.datasources.ImagesDataSource
import com.aherbel.agileenginetest.domain.model.Image
import com.aherbel.agileenginetest.domain.responses.ImagesResponse

class ApiImagesDataSource : ImagesDataSource {
    
    override suspend fun getImages(page: Int, limit: Int?): ImagesResponse =
        RetrofitClient.imageApi.getImages(page, limit)
    
    override suspend fun getImage(id: String): Image =
        RetrofitClient.imageApi.getImage(id)
    
}