package com.hyundai.myexperience.data

import com.hyundai.myexperience.data.dto.user.SignInRequest
import com.hyundai.myexperience.data.dto.user.SignUpRequest
import com.hyundai.myexperience.data.mapper.mapToUser
import com.hyundai.myexperience.data.remote.UserRemoteDataSource
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userRemoteDataSource: UserRemoteDataSource
) {
    suspend fun requestMessage(request: SignInRequest): String? {
        val user = userRemoteDataSource.requestSignIn(request)?.mapToUser()

        return user?.message
    }

    suspend fun requestSignUp(id: String, name: String, password: String): Boolean {
        return userRemoteDataSource.requestSignUp(SignUpRequest(id, name, password))
    }
}