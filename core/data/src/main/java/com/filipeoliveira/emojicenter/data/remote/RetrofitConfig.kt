package com.filipeoliveira.emojicenter.data.remote

import com.filipeoliveira.emojicenter.data.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitConfig {
    var service: EmojiService = getEmojiService()

    private fun getRetrofitBuild() : Retrofit{

        return Retrofit.Builder()
            .baseUrl("https://emoji-api.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(getOkHttpClient())
            .build()
    }

    private fun getOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(ApiKeyInterceptor(BuildConfig.EMOJI_API_KEY))
            .addInterceptor(interceptor)
            .build()
    }

    private fun getEmojiService() : EmojiService {
        return getRetrofitBuild().create(EmojiService::class.java)
    }
}

class ApiKeyInterceptor(private val apiKey: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val urlWithApiKey = originalRequest.url.newBuilder()
            .addQueryParameter("access_key", apiKey)
            .build()

        val newRequest = originalRequest.newBuilder()
            .url(urlWithApiKey)
            .build()

        return chain.proceed(newRequest)
    }
}