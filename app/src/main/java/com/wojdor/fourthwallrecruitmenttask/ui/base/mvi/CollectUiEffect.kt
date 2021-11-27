package com.wojdor.fourthwallrecruitmenttask.ui.base.mvi

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@Composable
fun CollectUiEffects(viewModel: MviViewModel<*, *>, onUiEffect: (UiEffect) -> Unit) {
    LaunchedEffect(Unit) {
        launch {
            viewModel.uiEffect
                .onEach { onUiEffect(it) }
                .collect()
        }
    }
}