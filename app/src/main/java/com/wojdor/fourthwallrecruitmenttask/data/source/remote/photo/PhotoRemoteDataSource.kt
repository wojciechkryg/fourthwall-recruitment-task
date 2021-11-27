package com.wojdor.fourthwallrecruitmenttask.data.source.remote.photo

import com.wojdor.fourthwallrecruitmenttask.data.mapper.toPhotos
import com.wojdor.fourthwallrecruitmenttask.data.source.remote.service.PhotoApiService
import javax.inject.Inject

class PhotoRemoteDataSource @Inject constructor(
    private val photoApiService: PhotoApiService
) : PhotoRemoteSource {

    override suspend fun getPhotos() = photoApiService.getPhotos().toPhotos()
}