package com.hyundai.myexperience.data.remote

import com.hyundai.myexperience.BASE_URL
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit

class ServerConnection {
    companion object {
        private var instance: Retrofit? = null
        private var cookie: String? = null

        private val requestInterceptor = Interceptor { chain ->
            val originalRequest = chain.request()
            val modifiedRequestBuilder = originalRequest.newBuilder()

            cookie?.let {
                modifiedRequestBuilder.header("Cookie", it)
            }

            val modifiedRequest = modifiedRequestBuilder.build()
            chain.proceed(modifiedRequest)
        }

        private val responseInterceptor = Interceptor { chain ->
            val response = chain.proceed(chain.request())

            if (response.headers("Set-Cookie").isNotEmpty()) {
                cookie = response.header("Set-Cookie")
            }

            response
        }

        fun getInstance(): Retrofit {
            if (instance == null) {
                val client = OkHttpClient.Builder()
                    .addInterceptor(requestInterceptor)
                    .addInterceptor(responseInterceptor)
                    .build()

                val contentType = "application/json".toMediaType()

                instance = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(Json.asConverterFactory(contentType))
                    .build()
            }

            return instance!!
        }

        fun setCookie(cookie: String) {
            this.cookie = cookie
        }

        fun getCookie(): String? {
            return cookie
        }
    }
}