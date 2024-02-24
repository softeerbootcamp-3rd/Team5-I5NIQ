package com.hyundai.myexperience.data.remote.service

import com.hyundai.myexperience.data.dto.my_page.JoinedProgramDetailResponse
import com.hyundai.myexperience.data.dto.my_page.JoinedProgramResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface JoinedProgramService {
    @GET("/user/programs")
    suspend fun requestJoinedPrograms(@Query("status") status: String): Response<JoinedProgramResponse>

    @GET("/user/participations/{participationId}")
    suspend fun requestJoinedProgramDetail(@Path("participationId") id: Int): Response<JoinedProgramDetailResponse>

}