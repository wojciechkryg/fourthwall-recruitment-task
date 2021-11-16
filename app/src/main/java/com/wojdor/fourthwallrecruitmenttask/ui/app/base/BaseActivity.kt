package com.wojdor.fourthwallrecruitmenttask.ui.app.base

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import com.wojdor.fourthwallrecruitmenttask.ui.app.theme.AppTheme

abstract class BaseActivity<VM : BaseViewModel> : ComponentActivity() {

    protected abstract val viewModel: VM

    @Composable
    abstract fun Content()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createContent()
    }

    private fun createContent() {
        setContent {
            AppTheme {
                Surface(color = MaterialTheme.colors.background) {
                    Content()
                }
            }
        }
    }
}