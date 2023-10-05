package com.filipeoliveira.emojicenter.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.filipeoliveira.emojicenter.data.model.Emoji
import com.filipeoliveira.emojicenter.ui.theme.dimen20Dp
import com.filipeoliveira.emojicenter.ui.theme.dimen40Dp
import com.filipeoliveira.emojicenter.ui.theme.dimen8Dp

@Composable
fun EmojiItemRightLayout(
    emoji: Emoji?,
    modifier: Modifier = Modifier
) {
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = dimen8Dp
        )
    ) {
        Row(
            modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface),
            verticalAlignment = Alignment.CenterVertically
        ) {
            EmojiText(
                text = emoji?.unicodeName ?: "",
                modifier = Modifier
                    .weight(1F)
                    .padding(dimen8Dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
            Box(
                modifier = Modifier
                    .background(
                        MaterialTheme.colorScheme.surfaceVariant,
                        shape = RoundedCornerShape(
                            topStart = dimen40Dp,
                            bottomStart = dimen40Dp
                        ))
                    .padding(
                        horizontal = dimen20Dp,
                        vertical = dimen8Dp
                    )
            ) {
                EmojiText(
                    emoji?.character ?: "",
                    fontSize = 50.sp
                )
            }
        }
    }

}

@Composable
fun EmojiItemLeftLayout(
    emoji: Emoji?,
    modifier: Modifier = Modifier
) {
    Card (
      elevation = CardDefaults.cardElevation(
          defaultElevation = dimen8Dp
      )
    ){
        Row(
            modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .background(
                        MaterialTheme.colorScheme.surfaceVariant,
                        shape = RoundedCornerShape(
                            topEnd = dimen40Dp,
                            bottomEnd = dimen40Dp
                        ))
                    .padding(
                        horizontal = dimen20Dp,
                        vertical = dimen8Dp)

            ) {
                EmojiText(
                    emoji?.character ?: "",
                    fontSize = 50.sp
                )
            }
            EmojiText(
                text = emoji?.unicodeName ?: "",
                modifier = Modifier
                    .weight(1F)
                    .padding(dimen8Dp)
                ,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}

@Preview
@Composable
fun EmojiItemRightPreview() {
    EmojiItemRightLayout(
        emoji = Emoji(
            character = "\ud83d\ude03",
            slug = "grinning-face-with-big-eyes",
            unicodeName = "grinning face with big eyes"
        ),
    )
}

@Preview
@Composable
fun EmojiItemLeftPreview() {
    EmojiItemLeftLayout(
        emoji = Emoji(
            character = "\ud83d\ude03",
            slug = "grinning-face-with-big-eyes",
            unicodeName = "grinning face with big eyes"
        ),
    )
}