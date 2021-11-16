package com.wojdor.fourthwallrecruitmenttask.ui.app.base.mvi

import androidx.lifecycle.viewModelScope
import com.wojdor.fourthwallrecruitmenttask.ui.app.base.BaseViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class MviViewModel<I : UiIntent, S : UiState>(initialState: S) : BaseViewModel() {

    private val _uiIntent = Channel<I>(Channel.UNLIMITED)
    private val _uiState = MutableStateFlow(initialState)
    val uiState: StateFlow<S>
        get() = _uiState

    init {
        startCollectingUiIntents()
    }

    fun sendUiIntent(intent: I) {
        viewModelScope.launch { _uiIntent.send(intent) }
    }

    protected open fun onUiIntent(intent: I) {
        // can be overridden by children implementations
    }

    protected fun publishUiState(state: S) {
        _uiState.value = state
    }

    private fun startCollectingUiIntents() {
        viewModelScope.launch { _uiIntent.receiveAsFlow().collect { onUiIntent(it) } }
    }
}