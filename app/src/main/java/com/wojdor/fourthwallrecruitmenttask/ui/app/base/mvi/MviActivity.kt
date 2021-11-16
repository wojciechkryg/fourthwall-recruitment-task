package com.wojdor.fourthwallrecruitmenttask.ui.app.base.mvi

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.wojdor.fourthwallrecruitmenttask.ui.app.base.BaseActivity
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

abstract class MviActivity<VM : MviViewModel<out UiIntent, out UiState>> : BaseActivity<VM>() {

    abstract fun onUiState(state: UiState)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startCollectingUiStates()
    }

    private fun startCollectingUiStates() {
        lifecycleScope.launch { viewModel.uiState.collect { onUiState(it) } }
    }
}