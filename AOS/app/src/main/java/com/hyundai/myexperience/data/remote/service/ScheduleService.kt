package com.hyundai.myexperience.data.remote.service

import com.hyundai.myexperience.data.dto.schedule.ScheduleDetailResponse
import com.hyundai.myexperience.data.dto.schedule.ScheduleListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ScheduleService {
    @GET("schedule/{program}")
    suspend fun requestSchedules(@Path("program") program: String): Response<ScheduleListResponse>

    @GET("schedule/{program}/{date}")
    suspend fun requestScheduleDetail(
        @Path("program") program: String,
        @Path("date") date: String
    ): Response<ScheduleDetailResponse>
}