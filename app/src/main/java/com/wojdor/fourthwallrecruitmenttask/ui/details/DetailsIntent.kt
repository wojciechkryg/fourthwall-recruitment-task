package com.wojdor.fourthwallrecruitmenttask.ui.details

import com.wojdor.fourthwallrecruitmenttask.domain.model.Photo
import com.wojdor.fourthwallrecruitmenttask.ui.base.mvi.UiIntent

sealed class DetailsIntent : UiIntent {
    data class ShowPhotoDetails(val photo: Photo) : DetailsIntent()
}