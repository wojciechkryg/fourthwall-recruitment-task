package com.wojdor.fourthwallrecruitmenttask.ui.gallery

import androidx.lifecycle.viewModelScope
import com.wojdor.fourthwallrecruitmenttask.domain.usecase.GetPhotosUseCase
import com.wojdor.fourthwallrecruitmenttask.domain.usecase.base.Result
import com.wojdor.fourthwallrecruitmenttask.ui.base.mvi.MviViewModel
import com.wojdor.fourthwallrecruitmenttask.ui.gallery.GalleryEffect.NavigateToPhotoDetails
import com.wojdor.fourthwallrecruitmenttask.ui.gallery.GalleryIntent.DownloadPhotos
import com.wojdor.fourthwallrecruitmenttask.ui.gallery.GalleryIntent.ShowPhotoDetails
import com.wojdor.fourthwallrecruitmenttask.ui.gallery.GalleryState.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(
    private val getPhotosUseCase: GetPhotosUseCase
) : MviViewModel<GalleryIntent, GalleryState>(Idle) {

    override fun onIntent(intent: GalleryIntent) {
        when (intent) {
            DownloadPhotos -> downloadPhotos()
            is ShowPhotoDetails -> sendEffect(NavigateToPhotoDetails(intent.photo))
        }
    }

    private fun downloadPhotos() {
        publishState(Loading)
        getPhotosUseCase().onEach {
            when (it) {
                is Result.Success -> publishState(Photos(it.data))
                is Result.Error -> publishState(Error(it.error))
            }
        }.launchIn(viewModelScope)
    }
}