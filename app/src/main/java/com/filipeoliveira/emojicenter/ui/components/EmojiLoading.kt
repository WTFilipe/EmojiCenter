package com.filipeoliveira.emojicenter.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun EmojiLoading(
    modifier: Modifier = Modifier,
    text: @Composable (() -> Unit)? = null
) {
    Box(
        modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator()
            text?.let { it() }
        }
    }
}

@Preview
@Composable
fun LoadingPreview() {
    EmojiLoading(text = { Text(text = "sidjisdjsidj") })
}