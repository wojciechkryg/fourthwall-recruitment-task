package com.wojdor.fourthwallrecruitmenttask.ui.base.mvi

import androidx.lifecycle.viewModelScope
import com.wojdor.fourthwallrecruitmenttask.ui.base.BaseViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

abstract class MviViewModel<I : UiIntent, S : UiState>(initialState: S) : BaseViewModel() {

    private val _uiIntent = Channel<I>(Channel.UNLIMITED)

    private val _uiEffect = Channel<UiEffect>(Channel.UNLIMITED)
    val uiEffect: Flow<UiEffect>
        get() = _uiEffect.receiveAsFlow()

    private val _uiState = MutableStateFlow(initialState)
    val uiState: StateFlow<S>
        get() = _uiState

    init {
        startCollectingIntents()
    }

    fun sendIntent(intent: I) {
        viewModelScope.launch { _uiIntent.send(intent) }
    }

    protected abstract fun onIntent(intent: I)

    protected fun sendEffect(effect: UiEffect) {
        viewModelScope.launch { _uiEffect.send(effect) }
    }

    protected fun publishState(state: S) {
        _uiState.value = state
    }

    private fun startCollectingIntents() {
        viewModelScope.launch { _uiIntent.receiveAsFlow().collect { onIntent(it) } }
    }
}