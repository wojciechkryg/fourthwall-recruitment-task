package com.wojdor.fourthwallrecruitmenttask.ui.details

import com.wojdor.fourthwallrecruitmenttask.ui.base.mvi.MviViewModel
import com.wojdor.fourthwallrecruitmenttask.ui.details.DetailsIntent.ShowSharePhoto
import com.wojdor.fourthwallrecruitmenttask.ui.details.DetailsIntent.ShowPhotoDetails
import com.wojdor.fourthwallrecruitmenttask.ui.details.DetailsState.PhotoDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor() :
    MviViewModel<DetailsIntent, DetailsState>(PhotoDetails()) {

    override fun onIntent(intent: DetailsIntent) {
        when (intent) {
            is ShowPhotoDetails -> publishState(PhotoDetails(intent.photo))
            is ShowSharePhoto -> {
                // TODO: get image from url and call share effect
            }
        }
    }
}