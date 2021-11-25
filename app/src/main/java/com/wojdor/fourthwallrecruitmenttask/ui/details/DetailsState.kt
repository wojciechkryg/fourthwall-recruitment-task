package com.wojdor.fourthwallrecruitmenttask.ui.details

import com.wojdor.fourthwallrecruitmenttask.domain.model.Photo
import com.wojdor.fourthwallrecruitmenttask.ui.base.mvi.UiState

sealed class DetailsState : UiState {
    object Idle : DetailsState()
    data class PhotoDetails(val photo: Photo) : DetailsState()
}