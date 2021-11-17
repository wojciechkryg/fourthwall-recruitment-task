package com.wojdor.fourthwallrecruitmenttask.data.repository.photo

import com.wojdor.fourthwallrecruitmenttask.data.remote.photo.PhotoRemoteSource
import javax.inject.Inject

class PhotoDataRepository @Inject constructor(
    private val photoRemoteSource: PhotoRemoteSource
) : PhotoRepository {

    override suspend fun getPhotos() = photoRemoteSource.getPhotos()
}