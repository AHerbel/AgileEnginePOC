package com.aherbel.agileenginetest.presentation.screens.imagelist

import android.util.Log
import androidx.lifecycle.*
import com.aherbel.agileenginetest.domain.repositories.ImagesRepository
import com.aherbel.agileenginetest.domain.responses.ImagesResponse
import kotlinx.coroutines.launch

class ImageListViewModel(
    private val imagesRepository: ImagesRepository,
) : ViewModel() {
    
    private var imagesResponse: ImagesResponse? = null
    
    val images = imagesRepository.images.asLiveData()
    
    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading
    
    private val _isLastPage: MutableLiveData<Boolean> = MutableLiveData(false)
    val isLastPage: LiveData<Boolean> = _isLastPage
    
    fun fetchNextImages() {
        if (imagesResponse?.hasMore == false) {
            return
        }
        
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val page = (imagesResponse?.page ?: 0) + 1
                imagesResponse = imagesRepository.getImages(page)
                
                _isLoading.value = false
                _isLastPage.value = !(imagesResponse?.hasMore ?: false)
            } catch (e: Exception) {
                Log.e("ERROR", e.message, e)
                _isLoading.value = false
            }
        }
    }
    
}