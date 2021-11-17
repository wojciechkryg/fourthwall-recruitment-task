package com.wojdor.fourthwallrecruitmenttask.data.repository.photo

import com.wojdor.fourthwallrecruitmenttask.domain.model.Photo

interface PhotoRepository {

    suspend fun getPhotos(): List<Photo>
}