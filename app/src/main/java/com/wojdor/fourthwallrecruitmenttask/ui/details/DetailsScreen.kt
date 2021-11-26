package com.wojdor.fourthwallrecruitmenttask.ui.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import coil.compose.rememberImagePainter
import coil.size.OriginalSize
import com.wojdor.fourthwallrecruitmenttask.R
import com.wojdor.fourthwallrecruitmenttask.domain.model.Photo
import com.wojdor.fourthwallrecruitmenttask.ui.details.DetailsEffect.SharePhoto
import com.wojdor.fourthwallrecruitmenttask.ui.details.DetailsIntent.ShowPhotoDetails
import com.wojdor.fourthwallrecruitmenttask.ui.details.DetailsState.PhotoDetails


@Composable
fun DetailsScreen(navBackStackEntry: NavBackStackEntry) {
    navBackStackEntry.arguments?.getParcelable<Photo>(PHOTO_EXTRA)?.let {
        DetailsScreen(photo = it)
    }
}

@Composable
fun DetailsScreen(viewModel: DetailsViewModel = hiltViewModel(), photo: Photo) {
    viewModel.sendIntent(ShowPhotoDetails(photo))
    val state by viewModel.uiState.collectAsState()
    DetailsScreen(viewModel, state)
}

@Composable
fun DetailsScreen(viewModel: DetailsViewModel, state: DetailsState) {
    when (state) {
        is PhotoDetails -> PhotoDetails(state.photo) {
            // TODO send intent to ViewModel
        }
    }
}

@Composable
fun PhotoDetails(photo: Photo, onShareClick: () -> Unit) {
    Scaffold(floatingActionButton = { ShareButton(onShareClick) }) {
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            Photo(photo)
            Author(photo.author)
        }
    }
}

@Composable
fun ShareButton(onClick: () -> Unit) {
    FloatingActionButton(onClick) {
        Icon(Icons.Filled.Share, stringResource(R.string.details_share))
    }
}

@Composable
fun Photo(photo: Photo) {
    Image(
        painter = rememberImagePainter(photo.imageUrl) {
            crossfade(true)
            size(OriginalSize)
        },
        contentDescription = stringResource(R.string.photo_grid_item_description, photo.author),
        contentScale = ContentScale.FillWidth,
        modifier = Modifier.fillMaxSize()
    )
}

@Composable
fun Author(author: String) {
    Text(
        author,
        style = MaterialTheme.typography.h6,
        modifier = Modifier.padding(dimensionResource(R.dimen.spacing_m))
    )
}

const val PHOTO_EXTRA = "PHOTO_EXTRA"