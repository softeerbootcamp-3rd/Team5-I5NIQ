package com.hyundai.myexperience.data.remote.data_source

import android.util.Log
import com.hyundai.myexperience.data.dto.user.JoinedProgramDetailResponse
import com.hyundai.myexperience.data.dto.user.JoinedProgramResponse
import com.hyundai.myexperience.data.dto.user.MyPageResponse
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

    suspend fun requestMyPage(): MyPageResponse? {
        val response = service.requestMyPage()

        if (response.isSuccessful) return response.body()

        return null
    }

    suspend fun requestJoinedPrograms(status: String): List<JoinedProgramResponse.Result>? {
        val response = service.requestJoinedPrograms(status)

        if (response.isSuccessful) return response.body()?.result

        else return null
    }

    suspend fun requestJoinedProgramDetail(id: Long): JoinedProgramDetailResponse? {
        val response = service.requestJoinedProgramDetail(id)
        Log.d("check_response", response.isSuccessful.toString())

        if (response.isSuccessful) {
            return response.body()
        }

        else return null
    }
}