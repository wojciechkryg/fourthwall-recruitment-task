package com.wojdor.fourthwallrecruitmenttask.data.mapper

import com.wojdor.fourthwallrecruitmenttask.data.entity.PhotoEntity
import com.wojdor.fourthwallrecruitmenttask.domain.model.Photo

fun List<PhotoEntity?>?.toPhotos() = this?.let {
    filterNotNull().map { it.toPhoto() }
} ?: emptyList()

fun PhotoEntity.toPhoto() = Photo(id.orEmpty(), author.orEmpty(), imageUrl.orEmpty())