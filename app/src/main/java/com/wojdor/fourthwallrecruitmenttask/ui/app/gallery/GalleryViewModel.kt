package com.wojdor.fourthwallrecruitmenttask.ui.app.gallery

import com.wojdor.fourthwallrecruitmenttask.ui.app.base.mvi.MviViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor() :
    MviViewModel<GalleryIntent, GalleryState>(GalleryState.Idle)
