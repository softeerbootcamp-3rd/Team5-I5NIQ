package com.hyundai.myexperience.data.remote.service

import retrofit2.http.GET
import retrofit2.http.Url

interface UserService {
    @GET
    suspend fun requestUserInfo(@Url url: String)
}