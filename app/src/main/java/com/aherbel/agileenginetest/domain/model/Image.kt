package com.aherbel.agileenginetest.domain.model

import com.google.gson.annotations.SerializedName

data class Image(
    val id: String?,
    val author: String?,
    val camera: String?,
    val tags: String?,
    @SerializedName("cropped_picture")val croppedPicture: String?,
    @SerializedName("full_picture")val fullPicture: String?,
)