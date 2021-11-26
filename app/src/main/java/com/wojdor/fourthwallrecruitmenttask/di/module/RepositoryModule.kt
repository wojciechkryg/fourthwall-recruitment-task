package com.wojdor.fourthwallrecruitmenttask.di.module

import com.wojdor.fourthwallrecruitmenttask.data.repository.file.FileDataRepository
import com.wojdor.fourthwallrecruitmenttask.data.repository.file.FileRepository
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

    @Binds
    abstract fun provideFileRepository(fileDataRepository: FileDataRepository): FileRepository
}