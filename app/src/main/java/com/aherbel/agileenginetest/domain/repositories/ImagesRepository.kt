package com.aherbel.agileenginetest.domain.repositories

import com.aherbel.agileenginetest.domain.model.Image
import com.aherbel.agileenginetest.domain.model.ImagePlaceholder
import com.aherbel.agileenginetest.domain.responses.ImagesResponse
import kotlinx.coroutines.flow.StateFlow

interface ImagesRepository {
    
    val images: StateFlow<List<ImagePlaceholder>>
    
    suspend fun getImages(page: Int = 1, limit: Int? = null): ImagesResponse
    suspend fun getImage(id: String): Image
    
}