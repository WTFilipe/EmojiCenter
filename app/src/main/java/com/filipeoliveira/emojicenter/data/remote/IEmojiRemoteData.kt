package com.filipeoliveira.emojicenter.data.remote

import com.filipeoliveira.emojicenter.data.remote.model.CategoryRemote
import com.filipeoliveira.emojicenter.data.remote.model.EmojiRemote
import kotlinx.coroutines.flow.Flow


interface IEmojiRemoteData {

    suspend fun getEmojis() : Flow<List<EmojiRemote>>
    suspend fun searchEmoji(search: String) : Flow<List<EmojiRemote>>
    suspend fun getEmojiBySlug(slug: String) : Flow<EmojiRemote>
    suspend fun getCategoryList() : Flow<List<CategoryRemote>>
    suspend fun getEmojisByCategory(category: String) : Flow<List<CategoryRemote>>
}