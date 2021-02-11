package com.aherbel.agileenginetest.domain.model

import com.google.gson.annotations.SerializedName

data class ImagePlaceholder(
    val id: String,
    @SerializedName("cropped_picture") val croppedPicture: String,
)