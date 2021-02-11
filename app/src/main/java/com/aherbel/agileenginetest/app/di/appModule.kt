package com.aherbel.agileenginetest.app.di

import com.aherbel.agileenginetest.app.datasources.ApiImagesDataSource
import com.aherbel.agileenginetest.app.repositories.ApiImagesRepository
import com.aherbel.agileenginetest.domain.datasources.ImagesDataSource
import com.aherbel.agileenginetest.domain.repositories.ImagesRepository
import com.aherbel.agileenginetest.presentation.screens.imagedetail.ImageDetailViewModel
import com.aherbel.agileenginetest.presentation.screens.imagelist.ImageListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    
    factory<ImagesDataSource> { ApiImagesDataSource() }
    single<ImagesRepository> { ApiImagesRepository(get()) }
    
    viewModel { ImageListViewModel(get()) }
    viewModel { ImageDetailViewModel(get()) }
    
}