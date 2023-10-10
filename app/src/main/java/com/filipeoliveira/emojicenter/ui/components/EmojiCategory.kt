package com.filipeoliveira.emojicenter.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.filipeoliveira.emojicenter.data.model.Emoji
import com.filipeoliveira.emojicenter.ui.screens.search.CategoryAndEmojis
import com.filipeoliveira.emojicenter.ui.theme.dimen16Dp
import com.filipeoliveira.emojicenter.ui.theme.dimen8Dp
import com.filipeoliveira.emojicenter.ui.utils.ShimmerText

@Composable
fun EmojiCategory(
    categoryAndEmojis: CategoryAndEmojis,
    modifier: Modifier = Modifier
) {
    if (
        categoryAndEmojis.emojisError != null
        || categoryAndEmojis.emojis.isEmpty()
    ) return

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
    ) {
        if (categoryAndEmojis.title.isNotBlank()){
            Title(modifier, categoryAndEmojis.title)
        } else {
            ShimmerText(
                modifier = Modifier
                    .fillMaxWidth(0.7f),
                style = MaterialTheme.typography.titleLarge
            )
        }

        Spacer(modifier = modifier.height(dimen8Dp))
        
        if (categoryAndEmojis.areEmojisLoading) {
            ShimmerText(
                modifier = Modifier
                    .fillMaxWidth(),
                fontSize = 50.sp
            )
        } else {
            EmojiList(emojiList = categoryAndEmojis.emojis)
        }


    }
}

@Composable
private fun EmojiList(emojiList: List<Emoji>) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = dimen16Dp),
        horizontalArrangement = Arrangement.spacedBy(dimen8Dp)
    ) {
        items(emojiList.size) {
            emojiList[it].character?.let { emojiUnicode ->
                EmojiText(
                    text = emojiUnicode,
                    fontSize = 50.sp
                )
            }
        }
    }
}

@Composable
private fun Title(modifier: Modifier, categoryName: String?) {
    Row(
        modifier = modifier
            .fillMaxWidth()
    ) {
        EmojiText(
            modifier = Modifier.padding(horizontal = dimen16Dp),
            text = categoryName ?: "",
            style = MaterialTheme.typography.titleLarge,
        )
    }
}

@Preview
@Composable
fun EmojiCategoryPreview() {
    EmojiCategory(
        CategoryAndEmojis(
            title = "Categoria",
            emojis = List(10){
                Emoji(
                    character = "\ud83d\ude03",
                    slug = "grinning-face-with-big-eyes",
                    unicodeName = "grinning face with big eyes"
                )
            },
            areEmojisLoading = false,
            isTitleLoading = false
        )
    )
}
