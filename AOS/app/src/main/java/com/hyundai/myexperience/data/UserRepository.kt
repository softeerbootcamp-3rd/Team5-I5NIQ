package com.hyundai.myexperience.data

import com.hyundai.myexperience.data.dto.user.SignInRequest
import com.hyundai.myexperience.data.dto.user.SignUpRequest
import com.hyundai.myexperience.data.remote.UserRemoteDataSource
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userRemoteDataSource: UserRemoteDataSource
) {
    suspend fun requestSignIn(id: String, password: String): Boolean {
        return userRemoteDataSource.requestSignIn(SignInRequest(id, password))
    }

    suspend fun requestSignUp(id: String, name: String, password: String): Boolean {
        return userRemoteDataSource.requestSignUp(SignUpRequest(id, name, password))
    }
}