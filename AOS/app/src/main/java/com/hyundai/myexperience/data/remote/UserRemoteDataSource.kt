package com.hyundai.myexperience.data.remote

import android.util.Log
import com.hyundai.myexperience.data.dto.LoginRequest
import com.hyundai.myexperience.data.dto.LoginResponse
import com.hyundai.myexperience.data.mapper.mapToErrorResponse
import com.hyundai.myexperience.data.remote.service.UserService
import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(private val service: UserService) {
    suspend fun requestLogin(request: LoginRequest): LoginResponse? {
        val response = service.requestLogin(request)

        if (response.isSuccessful) return response.body()

        val error = response.errorBody()?.mapToErrorResponse()
        Log.e("response", error?.message ?: "null")

        return null
    }
}