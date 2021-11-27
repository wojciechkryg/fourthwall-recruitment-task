package com.wojdor.fourthwallrecruitmenttask.ui.gallery

import com.wojdor.fourthwallrecruitmenttask.domain.model.Photo
import com.wojdor.fourthwallrecruitmenttask.ui.base.mvi.UiEffect

sealed class GalleryEffect : UiEffect {
    data class NavigateToPhotoDetails(val photo: Photo) : GalleryEffect()
}