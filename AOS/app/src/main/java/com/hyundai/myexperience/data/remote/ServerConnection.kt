package com.hyundai.myexperience.data.remote

import com.hyundai.myexperience.BASE_URL
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit

class ServerConnection {
    companion object {
        private var instance: Retrofit? = null

        fun getInstance(): Retrofit {
            if (instance == null) {
                val client = OkHttpClient.Builder().build()
                val contentType = "application/json".toMediaType()

                instance = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(Json.asConverterFactory(contentType))
                    .build()
            }

            return instance!!
        }
    }
}