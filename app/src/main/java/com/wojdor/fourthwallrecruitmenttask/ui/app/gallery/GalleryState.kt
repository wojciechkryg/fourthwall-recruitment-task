package com.wojdor.fourthwallrecruitmenttask.ui.app.gallery

import com.wojdor.fourthwallrecruitmenttask.ui.app.base.mvi.UiState

sealed class GalleryState : UiState {
    object Idle : GalleryState()
}