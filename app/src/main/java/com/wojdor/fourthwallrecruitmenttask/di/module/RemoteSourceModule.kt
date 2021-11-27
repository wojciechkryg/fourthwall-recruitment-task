package com.wojdor.fourthwallrecruitmenttask.di.module

import android.content.Context
import coil.ImageLoader
import coil.request.ImageRequest
import com.wojdor.fourthwallrecruitmenttask.data.source.remote.image.ImageRemoteDataSource
import com.wojdor.fourthwallrecruitmenttask.data.source.remote.image.ImageRemoteSource
import com.wojdor.fourthwallrecruitmenttask.data.source.remote.photo.PhotoRemoteDataSource
import com.wojdor.fourthwallrecruitmenttask.data.source.remote.photo.PhotoRemoteSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class BindRemoteSourceModule {

    @Binds
    abstract fun providePhotoRemoteSource(
        photoRemoteDataSource: PhotoRemoteDataSource
    ): PhotoRemoteSource

    @Binds
    abstract fun provideImageRemoteSource(
        imageRemoteDataSource: ImageRemoteDataSource
    ): ImageRemoteSource
}

@Module
@InstallIn(SingletonComponent::class)
class ProvideRemoteSourceModule {

    @Provides
    @Singleton
    fun provideImageLoader(@ApplicationContext context: Context) = ImageLoader(context)

    @Provides
    @Singleton
    fun provideImageRequestBuilder(@ApplicationContext context: Context) =
        ImageRequest.Builder(context)
}