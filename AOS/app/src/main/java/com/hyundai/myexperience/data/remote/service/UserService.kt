package com.hyundai.myexperience.data.remote.service

import com.hyundai.myexperience.data.dto.LoginRequest
import com.hyundai.myexperience.data.dto.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {
    @POST("user/login")
    suspend fun requestLogin(@Body loginRequest: LoginRequest): Response<LoginResponse>
}