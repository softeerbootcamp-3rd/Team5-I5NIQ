package com.hyundai.myexperience.data.remote.service

import com.hyundai.myexperience.data.dto.user.SignInRequest
import com.hyundai.myexperience.data.dto.user.SignInResponse
import com.hyundai.myexperience.data.dto.user.SignUpRequest
import com.hyundai.myexperience.data.dto.user.SignUpResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {
    @POST("user/login")
    suspend fun requestSignIn(@Body signInRequest: SignInRequest): Response<SignInResponse>

    @POST("user/join")
    suspend fun requestSignUp(@Body signUpRequest: SignUpRequest): Response<SignUpResponse>
}