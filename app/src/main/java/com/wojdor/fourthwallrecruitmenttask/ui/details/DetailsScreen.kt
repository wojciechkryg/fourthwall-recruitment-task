package com.wojdor.fourthwallrecruitmenttask.ui.details

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import com.wojdor.fourthwallrecruitmenttask.domain.model.Photo

@Composable
fun DetailsScreen(navBackStackEntry: NavBackStackEntry) {
    navBackStackEntry.arguments?.getParcelable<Photo>(PHOTO_EXTRA)?.let {
        DetailsScreen(photo = it)
    }
}

@Composable
fun DetailsScreen(viewModel: DetailsViewModel = hiltViewModel(), photo: Photo) {
    val state by viewModel.uiState.collectAsState()
}

const val PHOTO_EXTRA = "PHOTO_EXTRA"