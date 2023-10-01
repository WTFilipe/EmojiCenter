package com.filipeoliveira.emojicenter.ui.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.filipeoliveira.emojicenter.R

@Composable
fun EmojiImage(
    modifier: Modifier = Modifier,
    painter: Painter,
    contentDescription: String = ""
){
    Image(
        painter = painter,
        modifier = modifier,
        contentDescription = contentDescription
    )
}

@Preview
@Composable
fun EmojiImagePreview(){
    EmojiImage(painter = painterResource(R.drawable.ic_launcher_background))
}