package com.hyundai.myexperience.data.remote.service

import com.hyundai.myexperience.data.dto.schedule.ScheduleListResponse
import retrofit2.Response
import retrofit2.http.GET

interface ScheduleListService {
    @GET("schedule/DRIVING_EXPERIENCE")
    suspend fun responseExperienceScheduleList(): Response<ScheduleListResponse>

    @GET("schedule/DRIVING_PLEASURE")
    suspend fun responsePleasureScheduleList(): Response<ScheduleListResponse>
}