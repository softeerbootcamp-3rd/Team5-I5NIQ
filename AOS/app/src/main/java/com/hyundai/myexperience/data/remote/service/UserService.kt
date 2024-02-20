package com.hyundai.myexperience.data.remote.service

import com.hyundai.myexperience.data.dto.user.SignInRequest
import com.hyundai.myexperience.data.dto.user.SignResponse
import com.hyundai.myexperience.data.dto.user.SignUpRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {
    @POST("user/login")
    suspend fun requestSignIn(@Body signInRequest: SignInRequest): Response<SignResponse>

    @POST("user/join")
    suspend fun requestSignUp(@Body signUpRequest: SignUpRequest): Response<SignResponse>

    @POST("user/logout")
    suspend fun requestSignOut(): Response<SignResponse>
}