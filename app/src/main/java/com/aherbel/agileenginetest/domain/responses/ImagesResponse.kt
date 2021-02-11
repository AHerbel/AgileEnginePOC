package com.aherbel.agileenginetest.domain.responses

import com.aherbel.agileenginetest.domain.model.ImagePlaceholder

data class ImagesResponse(
    val pictures: List<ImagePlaceholder>?,
    val page: Int?,
    val pageCount: Int?,
    val hasMore: Boolean?,
)