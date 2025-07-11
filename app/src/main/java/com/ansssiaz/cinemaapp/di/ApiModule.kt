package com.ansssiaz.cinemaapp.di

import com.ansssiaz.cinemaapp.BuildConfig
import com.ansssiaz.data.FilmsApi
import com.ansssiaz.shared.film.core.BASE_URL
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit

private val contentType = "application/json".toMediaType()
private val json = Json { ignoreUnknownKeys = true }

private fun provideOkHttpClient() = OkHttpClient.Builder()
    .addInterceptor(
        HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
    )
    .build()

private fun provideRetrofit(
    okHttpClient: OkHttpClient,
): Retrofit =
    Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(BASE_URL)
        .addConverterFactory(json.asConverterFactory(contentType))
        .build()

private fun provideFilmsApi(retrofit: Retrofit): FilmsApi =
    retrofit.create(FilmsApi::class.java)

val apiModule = module {
    single { provideOkHttpClient() }
    single { provideRetrofit(get()) }
    single { provideFilmsApi(get()) }
}