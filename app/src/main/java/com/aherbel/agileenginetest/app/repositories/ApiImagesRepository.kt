package com.aherbel.agileenginetest.app.repositories

import com.aherbel.agileenginetest.domain.datasources.ImagesDataSource
import com.aherbel.agileenginetest.domain.model.Image
import com.aherbel.agileenginetest.domain.model.ImagePlaceholder
import com.aherbel.agileenginetest.domain.repositories.ImagesRepository
import com.aherbel.agileenginetest.domain.responses.ImagesResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ApiImagesRepository(
    private val imagesDataSource: ImagesDataSource,
) : ImagesRepository {
    
    private val _images: MutableStateFlow<List<ImagePlaceholder>> = MutableStateFlow(listOf())
    override val images: StateFlow<List<ImagePlaceholder>> = _images
    
    override suspend fun getImages(page: Int, limit: Int?): ImagesResponse {
        val imagesResponse = imagesDataSource.getImages(page, limit)
        _images.emit(images.value + imagesResponse.pictures.orEmpty())
        return imagesResponse
    }
    
    override suspend fun getImage(id: String): Image =
        imagesDataSource.getImage(id)
    
}