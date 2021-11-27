package com.wojdor.fourthwallrecruitmenttask.ui.gallery

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.wojdor.fourthwallrecruitmenttask.R
import com.wojdor.fourthwallrecruitmenttask.domain.model.Photo
import com.wojdor.fourthwallrecruitmenttask.ui.base.mvi.CollectUiEffects
import com.wojdor.fourthwallrecruitmenttask.ui.common.ErrorMessage
import com.wojdor.fourthwallrecruitmenttask.ui.common.Loader
import com.wojdor.fourthwallrecruitmenttask.ui.gallery.GalleryEffect.NavigateToPhotoDetails
import com.wojdor.fourthwallrecruitmenttask.ui.gallery.GalleryIntent.DownloadPhotos
import com.wojdor.fourthwallrecruitmenttask.ui.gallery.GalleryState.*
import com.wojdor.fourthwallrecruitmenttask.ui.util.getColumnCount
import com.wojdor.fourthwallrecruitmenttask.ui.util.navigateToDetails

@ExperimentalFoundationApi
@Composable
fun GalleryScreen(viewModel: GalleryViewModel = hiltViewModel(), navController: NavController) {
    val state by viewModel.uiState.collectAsState()
    HandleGalleryEffect(viewModel, navController)
    HandleGalleryState(viewModel, state)
}

@Composable
private fun HandleGalleryEffect(viewModel: GalleryViewModel, navController: NavController) {
    CollectUiEffects(viewModel) {
        when (it) {
            is NavigateToPhotoDetails -> navController.navigateToDetails(it.photo)
        }
    }
}

@ExperimentalFoundationApi
@Composable
private fun HandleGalleryState(
    viewModel: GalleryViewModel,
    state: GalleryState
) {
    when (state) {
        Idle -> viewModel.sendIntent(DownloadPhotos)
        Loading -> Loader()
        is Photos -> PhotoGrid(state.photos) {
            viewModel.sendIntent(GalleryIntent.ShowPhotoDetails(it))
        }
        is Error -> ErrorMessage()
    }
}

@ExperimentalFoundationApi
@Composable
fun PhotoGrid(photos: List<Photo>, onClick: (Photo) -> Unit) {
    LazyVerticalGrid(GridCells.Fixed(getColumnCount())) {
        items(photos) { PhotoGridItem(it, onClick) }
    }
}

@Composable
fun PhotoGridItem(photo: Photo, onClick: (Photo) -> Unit) {
    Image(
        painter = rememberImagePainter(photo.imageUrl) {
            crossfade(true)
        },
        contentDescription = stringResource(R.string.photo_grid_item_description, photo.author),
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(ITEM_ASPECT_RATIO)
            .clickable { onClick(photo) }
    )
}

private const val ITEM_ASPECT_RATIO = 1f