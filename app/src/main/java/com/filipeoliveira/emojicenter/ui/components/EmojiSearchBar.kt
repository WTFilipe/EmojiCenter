package com.filipeoliveira.emojicenter.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.filipeoliveira.emojicenter.R

@Composable
fun EmojiSearchBar(
    modifier: Modifier = Modifier,
    value: String,
    onValueChanged: (String) -> Unit
) {
    EmojiTextField(
        value = value,
        onValueChanged = onValueChanged,
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp),
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null
            )
        },
        placeholder = {
            EmojiText(stringResource(R.string.search))
        }
    )
}

@Preview
@Composable
fun EmojiSearchBarPreview() {
    EmojiSearchBar(value = ""){

    }
}