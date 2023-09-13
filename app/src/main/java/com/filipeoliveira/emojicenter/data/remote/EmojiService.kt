package com.filipeoliveira.emojicenter.data.remote

import com.filipeoliveira.emojicenter.data.remote.model.CategoryRemote
import com.filipeoliveira.emojicenter.data.remote.model.EmojiRemote
import retrofit2.http.GET
import retrofit2.http.Path

interface EmojiService {

    @GET("emojis")
    fun getAllEmojis() : List<EmojiRemote>

    @GET("emojis?search={search}")
    fun searchEmoji(@Path("search") search: String) : List<EmojiRemote>

    @GET("emojis/{slug}")
    fun getEmojiBySlug(@Path("slug") slug: String) : EmojiRemote

    @GET("categories")
    fun getCategoryList() : List<CategoryRemote>

    @GET("categories/{category}")
    fun getEmojisByCategory(@Path("category") category: String) : List<CategoryRemote>
}