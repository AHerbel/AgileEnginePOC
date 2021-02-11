package com.aherbel.agileenginetest.domain.datasources

import com.aherbel.agileenginetest.domain.responses.ImagesResponse
import com.aherbel.agileenginetest.domain.model.Image

interface ImagesDataSource {
    
    suspend fun getImages(page: Int = 1, limit: Int? = null): ImagesResponse
    suspend fun getImage(id: String): Image
    
}