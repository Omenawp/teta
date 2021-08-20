package com.oelrun.teta.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

object MovieApiClient {
    private const val BASE_URL = "https://api.themoviedb.org/3/"
    private const val API_KEY = "92a3535428637ac6b81e876b7065bd2e"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .setClient()
        .addJsonConverter()
        .build()

    @Suppress("EXPERIMENTAL_API_USAGE")
    private fun Retrofit.Builder.addJsonConverter() = apply {
        val json = Json { ignoreUnknownKeys = true }
        val contentType = "application/json".toMediaType()
        this.addConverterFactory(json.asConverterFactory(contentType))
    }

    private fun OkHttpClient.Builder.addApiKeyInterceptor() = apply {
        val interceptor = Interceptor { chain ->
            var request = chain.request()
            val url = request.url.newBuilder()
                .addQueryParameter("api_key", API_KEY)
                .addQueryParameter("language", "ru")
                .build()

            request = request.newBuilder().url(url).build()

            chain.proceed(request)
        }
        this.addInterceptor(interceptor)
    }

    private fun Retrofit.Builder.setClient() = apply {
        val okHttpClient = OkHttpClient.Builder()
            .addApiKeyInterceptor()
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()

        this.client(okHttpClient)
    }

    val service: MovieApiService by lazy {
        retrofit.create(MovieApiService::class.java)
    }
}