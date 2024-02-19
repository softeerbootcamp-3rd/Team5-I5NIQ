package com.hyundai.myexperience.data.remote

import com.hyundai.myexperience.data.dto.user.SignInRequest
import com.hyundai.myexperience.data.dto.user.SignUpRequest
import com.hyundai.myexperience.data.remote.service.UserService
import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(private val service: UserService) {
    suspend fun requestSignIn(request: SignInRequest): Boolean {
        val response = service.requestSignIn(request)

        return response.isSuccessful
    }

    suspend fun requestSignUp(request: SignUpRequest): Boolean {
        val response = service.requestSignUp(request)

        return response.isSuccessful
    }

    suspend fun requestSignOut(): Boolean {
        val response = service.requestSignOut()

        return response.isSuccessful
    }
}