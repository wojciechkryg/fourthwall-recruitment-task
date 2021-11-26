package com.wojdor.fourthwallrecruitmenttask.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.wojdor.fourthwallrecruitmenttask.R

@Composable
fun ErrorMessage(
    title: String = stringResource(R.string.gallery_error_title),
    message: String = stringResource(R.string.gallery_error_message)
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(title, style = MaterialTheme.typography.h2)
        Text(message, style = MaterialTheme.typography.subtitle1)
    }
}