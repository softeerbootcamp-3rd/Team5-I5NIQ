package com.hyundai.myexperience.data.remote

import android.util.Log
import com.hyundai.myexperience.data.dto.my_page.JoinedProgramDetailResponse
import com.hyundai.myexperience.data.dto.my_page.JoinedProgramResponse
import com.hyundai.myexperience.data.entity.my_page.JoinedProgramDetail
import com.hyundai.myexperience.data.remote.service.JoinedProgramService
import javax.inject.Inject

class JoinedProgramRemoteDataSource @Inject constructor(private val service: JoinedProgramService) {
    suspend fun requestJoinedPrograms(status: String): List<JoinedProgramResponse.Result>? {
        val response = service.requestJoinedPrograms(status)

        if (response.isSuccessful) return response.body()?.result

        else return null
    }

    suspend fun requestJoinedProgramDetail(id: Long): JoinedProgramDetailResponse? {
        val response = service.requestJoinedProgramDetail(id)

        if (response.isSuccessful) {
            return response.body()
        }

        else return null
    }
}