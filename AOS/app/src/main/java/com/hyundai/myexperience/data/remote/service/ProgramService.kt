package com.hyundai.myexperience.data.remote.service

import com.hyundai.myexperience.data.dto.program.ProgramConfResponse
import com.hyundai.myexperience.data.dto.program.ProgramMajorResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ProgramService {
    @GET("program/information")
    suspend fun requestProgramMajorData(@Query("program-id") programId: Int): Response<ProgramMajorResponse>

    @GET("program/detail")
    suspend fun requestProgramDetailData(@Query("program-id") programId: Int): Response<ProgramConfResponse>
}