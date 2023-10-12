package com.filipeoliveira.emojicenter.data.remote

import com.filipeoliveira.emojicenter.data.remote.model.CategoryRemote
import com.filipeoliveira.emojicenter.data.remote.model.EmojiRemote
import javax.inject.Inject

class EmojiRemoteData @Inject constructor (
    private val service: EmojiService
) : IEmojiRemoteData {
    override suspend fun getEmojis(): List<EmojiRemote> {
        return service.getAllEmojis()
    }

    override suspend fun getCategoryList(): List<CategoryRemote> {
        return service.getCategoryList()
    }

}