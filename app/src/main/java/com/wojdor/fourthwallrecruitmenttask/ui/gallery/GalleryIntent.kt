package com.wojdor.fourthwallrecruitmenttask.ui.gallery

import com.wojdor.fourthwallrecruitmenttask.ui.base.mvi.UiIntent

sealed class GalleryIntent : UiIntent {
    object DownloadPhotos : GalleryIntent()
}