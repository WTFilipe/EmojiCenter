package com.filipeoliveira.emojicenter.ui.utils

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.TextUnit
import com.filipeoliveira.emojicenter.ui.components.EmojiText

@Composable
fun ShimmerText(
    modifier: Modifier = Modifier,
    style: TextStyle? = null,
    fontSize: TextUnit? = null
) {
    Row(
        modifier = modifier
            .shimmerEffect(
                gradientLight = MaterialTheme.colorScheme.surfaceVariant,
                gradientDark = MaterialTheme.colorScheme.inverseOnSurface,
            )
    ) {
        style?.let {
            EmojiText(
                text = "",
                style = it,
            )
        }

        fontSize?.let {
            EmojiText(
                text = "",
                fontSize = it,
            )
        }
    }
}