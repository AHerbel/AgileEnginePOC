package com.aherbel.agileenginetest.presentation.screens.imagedetail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aherbel.agileenginetest.domain.model.Image
import com.aherbel.agileenginetest.domain.repositories.ImagesRepository
import com.reservando.android.presentation.mvvm.EventLiveData
import com.reservando.android.presentation.mvvm.MutableEventLiveData
import kotlinx.coroutines.launch

class ImageDetailViewModel(
    private val imagesRepository: ImagesRepository,
) : ViewModel() {
    
    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading
    
    private val _hasNextImage: MutableLiveData<Boolean> = MutableLiveData()
    val hasNextImage: LiveData<Boolean> = _hasNextImage
    
    private val _hasPreviousImage: MutableLiveData<Boolean> = MutableLiveData()
    val hasPreviousImage: LiveData<Boolean> = _hasPreviousImage
    
    private val _image: MutableLiveData<Image> = MutableLiveData()
    val image: LiveData<Image> = _image
    
    private val _onShare: MutableEventLiveData<Image> = MutableEventLiveData()
    val onShare: EventLiveData<Image> = _onShare
    
    private val selectedImageIndex: Int
        get() = imagesRepository.images.value.indexOfFirst { image -> image.id == this.image.value?.id }
    
    private fun updateNextAndPreviousImageStates() {
        _hasNextImage.value = selectedImageIndex < (imagesRepository.images.value.size - 1)
        _hasPreviousImage.value = selectedImageIndex > 0
    }
    
    fun getImage(id: String) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                _image.value = imagesRepository.getImage(id)
                _isLoading.value = false
                updateNextAndPreviousImageStates()
            } catch (e: Exception) {
                Log.e("ERROR", e.message, e)
                _isLoading.value = false
            }
        }
    }
    
    fun goToNextImage() {
        val images = imagesRepository.images.value
        _hasNextImage.value = selectedImageIndex < (images.size - 1)
        _hasPreviousImage.value = selectedImageIndex > 0
        
        if (hasNextImage.value == true) {
            val nextImage = images[selectedImageIndex + 1]
            getImage(nextImage.id)
        }
    }
    
    fun goToPreviousImage() {
        val images = imagesRepository.images.value
        _hasNextImage.value = selectedImageIndex < (images.size - 1)
        _hasPreviousImage.value = selectedImageIndex > 0
        
        if (hasNextImage.value == true) {
            val previousImage = images[selectedImageIndex - 1]
            getImage(previousImage.id)
        }
    }
    
    fun share() {
        image.value?.let { _onShare.sendEvent(it) }
    }
    
}