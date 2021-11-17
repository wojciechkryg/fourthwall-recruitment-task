package com.wojdor.fourthwallrecruitmenttask.data.remote.service

import com.wojdor.fourthwallrecruitmenttask.data.entity.PhotoEntity
import retrofit2.http.GET

interface PhotoApiService {

    @GET("list")
    suspend fun getPhotos(): List<PhotoEntity?>?
}