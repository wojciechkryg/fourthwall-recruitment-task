package com.wojdor.fourthwallrecruitmenttask.di.module

import android.content.Context
import com.wojdor.fourthwallrecruitmenttask.data.source.local.file.FileLocalDataSource
import com.wojdor.fourthwallrecruitmenttask.data.source.local.file.FileLocalSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.io.File
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class BindLocalSourceModule {

    @Binds
    abstract fun provideFileLocalSource(fileLocalDataSource: FileLocalDataSource): FileLocalSource
}

@Module
@InstallIn(SingletonComponent::class)
class ProvideLocalSourceModule {

    @Provides
    @Singleton
    fun provideCacheImageFile(@ApplicationContext context: Context) =
        File(context.cacheDir, FILE_NAME)

    companion object {
        private const val FILE_NAME = "imageCache.jpg"
    }
}