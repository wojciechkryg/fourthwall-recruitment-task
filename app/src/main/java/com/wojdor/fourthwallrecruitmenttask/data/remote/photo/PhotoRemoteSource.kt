package com.wojdor.fourthwallrecruitmenttask.data.remote.photo

import com.wojdor.fourthwallrecruitmenttask.domain.model.Photo

interface PhotoRemoteSource {

    suspend fun getPhotos(): List<Photo>
}