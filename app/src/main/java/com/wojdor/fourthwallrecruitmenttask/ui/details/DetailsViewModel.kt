package com.wojdor.fourthwallrecruitmenttask.ui.details

import androidx.lifecycle.viewModelScope
import com.wojdor.fourthwallrecruitmenttask.domain.usecase.GetPhotoUriUseCase
import com.wojdor.fourthwallrecruitmenttask.domain.usecase.base.Result
import com.wojdor.fourthwallrecruitmenttask.ui.base.mvi.MviViewModel
import com.wojdor.fourthwallrecruitmenttask.ui.details.DetailsEffect.SharePhoto
import com.wojdor.fourthwallrecruitmenttask.ui.details.DetailsEffect.ShowError
import com.wojdor.fourthwallrecruitmenttask.ui.details.DetailsIntent.ShowPhotoDetails
import com.wojdor.fourthwallrecruitmenttask.ui.details.DetailsIntent.ShowSharePhoto
import com.wojdor.fourthwallrecruitmenttask.ui.details.DetailsState.PhotoDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getPhotoUriUseCase: GetPhotoUriUseCase
) : MviViewModel<DetailsIntent, DetailsState>(PhotoDetails()) {

    override fun onIntent(intent: DetailsIntent) {
        when (intent) {
            is ShowPhotoDetails -> publishState(PhotoDetails(intent.photo))
            is ShowSharePhoto -> getUriFromImageUrl(intent.imageUrl)
        }
    }

    private fun getUriFromImageUrl(imageUrl: String) {
        getPhotoUriUseCase(imageUrl).onEach {
            when (it) {
                is Result.Success -> sendEffect(SharePhoto(it.data))
                is Result.Error -> sendEffect(ShowError)
            }
        }.launchIn(viewModelScope)
    }
}