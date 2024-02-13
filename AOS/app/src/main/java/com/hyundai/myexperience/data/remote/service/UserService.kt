package com.hyundai.myexperience.data.remote.service

import com.hyundai.myexperience.data.dto.UserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface UserService {
    @GET
    suspend fun requestUserInfo(@Url url: String): Response<UserResponse>
}