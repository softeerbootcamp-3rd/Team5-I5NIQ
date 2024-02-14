package com.hyundai.myexperience.data.remote

import com.hyundai.myexperience.data.dto.LoginRequest
import com.hyundai.myexperience.data.dto.LoginResponse
import com.hyundai.myexperience.data.remote.service.UserService
import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(private val service: UserService) {
    suspend fun requestLogin(request: LoginRequest): LoginResponse? {
        return service.requestLogin(request).body()
    }
}