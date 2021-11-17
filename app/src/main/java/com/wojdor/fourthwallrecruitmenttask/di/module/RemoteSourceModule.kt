package com.wojdor.fourthwallrecruitmenttask.di.module

import com.wojdor.fourthwallrecruitmenttask.data.remote.photo.PhotoRemoteDataSource
import com.wojdor.fourthwallrecruitmenttask.data.remote.photo.PhotoRemoteSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteSourceModule {

    @Binds
    abstract fun providePhotoRemoteSource(
        photoRemoteDataSource: PhotoRemoteDataSource
    ): PhotoRemoteSource
}