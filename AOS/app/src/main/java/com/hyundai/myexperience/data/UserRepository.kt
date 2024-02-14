package com.hyundai.myexperience.data

import com.hyundai.myexperience.data.dto.LoginRequest
import com.hyundai.myexperience.data.mapper.mapToUser
import com.hyundai.myexperience.data.remote.UserRemoteDataSource
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userRemoteDataSource: UserRemoteDataSource
) {
    suspend fun requestMessage(request: LoginRequest): String? {
        val user = userRemoteDataSource.requestLogin(request)?.mapToUser()

        return user?.message
    }
}