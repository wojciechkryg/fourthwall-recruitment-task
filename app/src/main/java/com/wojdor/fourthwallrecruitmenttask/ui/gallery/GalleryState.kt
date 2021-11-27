package com.wojdor.fourthwallrecruitmenttask.ui.gallery

import com.wojdor.fourthwallrecruitmenttask.domain.model.Photo
import com.wojdor.fourthwallrecruitmenttask.ui.base.mvi.UiState

sealed class GalleryState : UiState {
    object Idle : GalleryState()
    object Loading : GalleryState()
    data class Photos(val photos: List<Photo>) : GalleryState()
    data class Error(val error: Throwable) : GalleryState()
}