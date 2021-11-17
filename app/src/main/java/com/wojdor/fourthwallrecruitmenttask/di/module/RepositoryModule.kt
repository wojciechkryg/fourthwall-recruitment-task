package com.wojdor.fourthwallrecruitmenttask.di.module

import com.wojdor.fourthwallrecruitmenttask.data.repository.photo.PhotoDataRepository
import com.wojdor.fourthwallrecruitmenttask.data.repository.photo.PhotoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun providePhotoRepository(photoDataRepository: PhotoDataRepository): PhotoRepository
}