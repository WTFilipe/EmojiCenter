package com.filipeoliveira.emojicenter.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitConfig {
    var service: EmojiService = getEmojiService()

    private fun getRetrofitBuild() : Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://emoji-api.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    private fun getEmojiService() : EmojiService {
        return getRetrofitBuild().create(EmojiService::class.java)
    }
}