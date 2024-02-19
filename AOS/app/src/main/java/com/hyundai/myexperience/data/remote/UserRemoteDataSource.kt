package com.hyundai.myexperience.data.remote

import android.util.Log
import com.hyundai.myexperience.data.dto.user.SignInRequest
import com.hyundai.myexperience.data.dto.user.SignInResponse
import com.hyundai.myexperience.data.dto.user.SignUpRequest
import com.hyundai.myexperience.data.dto.user.SignUpResponse
import com.hyundai.myexperience.data.mapper.mapToErrorResponse
import com.hyundai.myexperience.data.remote.service.UserService
import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(private val service: UserService) {
    suspend fun requestSignIn(request: SignInRequest): SignInResponse? {
        val response = service.requestSignIn(request)

        if (response.isSuccessful) return response.body()

        val error = response.errorBody()?.mapToErrorResponse()
        Log.e("response", error?.message ?: "null")

        return null
    }

    suspend fun requestSignUp(request: SignUpRequest): Boolean {
        val response = service.requestSignUp(request)

        return response.isSuccessful
    }
}