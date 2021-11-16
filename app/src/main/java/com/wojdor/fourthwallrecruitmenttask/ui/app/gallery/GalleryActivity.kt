package com.wojdor.fourthwallrecruitmenttask.ui.app.gallery

import androidx.activity.viewModels
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.wojdor.fourthwallrecruitmenttask.ui.app.base.mvi.MviActivity
import com.wojdor.fourthwallrecruitmenttask.ui.app.base.mvi.UiState
import com.wojdor.fourthwallrecruitmenttask.ui.app.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GalleryActivity : MviActivity<GalleryViewModel>() {

    override val viewModel: GalleryViewModel by viewModels()

    @Composable
    override fun Content() {
        GalleryScreen()
    }

    override fun onUiState(state: UiState) {
        // TODO: Handle new UiState
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