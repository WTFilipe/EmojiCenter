package com.filipeoliveira.emojicenter.data.remote

import com.filipeoliveira.emojicenter.data.remote.model.CategoryRemote
import com.filipeoliveira.emojicenter.data.remote.model.EmojiRemote


interface IEmojiRemoteData {

    suspend fun getEmojis() : List<EmojiRemote>
    suspend fun getCategoryList() : List<CategoryRemote>
}