package com.wojdor.fourthwallrecruitmenttask.ui.gallery

import androidx.activity.viewModels
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.wojdor.fourthwallrecruitmenttask.ui.base.mvi.MviActivity
import com.wojdor.fourthwallrecruitmenttask.ui.base.mvi.UiState
import com.wojdor.fourthwallrecruitmenttask.ui.gallery.GalleryIntent.DownloadPhotos
import com.wojdor.fourthwallrecruitmenttask.ui.gallery.GalleryState.Idle
import com.wojdor.fourthwallrecruitmenttask.ui.gallery.GalleryState.Photos
import com.wojdor.fourthwallrecruitmenttask.ui.theme.AppTheme
import com.wojdor.fourthwallrecruitmenttask.ui.util.extension.logD
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GalleryActivity : MviActivity<GalleryViewModel>() {

    override val viewModel: GalleryViewModel by viewModels()

    @Composable
    override fun Content() {
        GalleryScreen()
    }

    override fun onUiState(state: UiState) {
        when (state) {
            Idle -> viewModel.sendIntent(DownloadPhotos)
            is Photos -> logD(state.photos.toString())
        }
    }
}

@Composable
fun GalleryScreen() {
    Greeting("Android")
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GalleryScreenPreview() {
    AppTheme {
        GalleryScreen()
    }
}