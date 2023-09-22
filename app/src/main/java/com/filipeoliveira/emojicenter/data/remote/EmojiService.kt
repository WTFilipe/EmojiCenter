package com.filipeoliveira.emojicenter.data.remote

import com.filipeoliveira.emojicenter.data.remote.model.CategoryRemote
import com.filipeoliveira.emojicenter.data.remote.model.EmojiRemote
import retrofit2.http.GET
import retrofit2.http.Path

interface EmojiService {

    @GET("emojis")
    suspend fun getAllEmojis() : List<EmojiRemote>

    @GET("emojis?search={search}")
    suspend fun searchEmoji(@Path("search") search: String) : List<EmojiRemote>

    @GET("emojis/{slug}")
    suspend fun getEmojiBySlug(@Path("slug") slug: String) : EmojiRemote

    @GET("categories")
    suspend fun getCategoryList() : List<CategoryRemote>

    @GET("categories/{category}")
    suspend fun getEmojisByCategory(@Path("category") category: String) : List<EmojiRemote>
}