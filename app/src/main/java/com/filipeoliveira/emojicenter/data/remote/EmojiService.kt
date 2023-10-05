package com.filipeoliveira.emojicenter.data.remote

import com.filipeoliveira.emojicenter.data.remote.model.CategoryRemote
import com.filipeoliveira.emojicenter.data.remote.model.EmojiRemote
import retrofit2.http.GET

interface EmojiService {

    @GET("emojis")
    suspend fun getAllEmojis() : List<EmojiRemote>

    @GET("categories")
    suspend fun getCategoryList() : List<CategoryRemote>
}