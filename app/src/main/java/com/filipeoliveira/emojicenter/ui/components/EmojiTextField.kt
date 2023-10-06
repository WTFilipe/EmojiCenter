package com.filipeoliveira.emojicenter.ui.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmojiTextField(
    onValueChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
    value: String = "",
    leadingIcon: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    colors: TextFieldColors = TextFieldDefaults.textFieldColors(),
) {
    TextField(
        value = value,
        onValueChange = onValueChanged,
        modifier = modifier,
        leadingIcon = leadingIcon,
        placeholder = placeholder,
        colors = colors
    )
}

@Preview
@Composable
fun EmojiTextFieldPreview() {
    EmojiText("sidjisdjsidj")
}