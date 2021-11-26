package com.wojdor.fourthwallrecruitmenttask.data.source.remote.photo

import com.wojdor.fourthwallrecruitmenttask.domain.model.Photo

interface PhotoRemoteSource {

    suspend fun getPhotos(): List<Photo>
}