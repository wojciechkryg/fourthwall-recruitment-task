package com.wojdor.fourthwallrecruitmenttask.di.module

import com.wojdor.fourthwallrecruitmenttask.BuildConfig
import com.wojdor.fourthwallrecruitmenttask.data.source.remote.service.PhotoApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder().apply {
        if (BuildConfig.DEBUG) {
            addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
        }
    }.build()

    @Provides
    @Singleton
    fun providePhotosRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl(API_URL_PHOTOS)
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun providePhotoApiService(retrofit: Retrofit): PhotoApiService =
        retrofit.create(PhotoApiService::class.java)

    companion object {
        private const val API_URL_PHOTOS = "https://picsum.photos/v2/"
    }
}