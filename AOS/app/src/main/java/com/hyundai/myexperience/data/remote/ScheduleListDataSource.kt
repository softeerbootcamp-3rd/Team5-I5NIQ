package com.hyundai.myexperience.data.remote

import android.util.Log
import com.hyundai.myexperience.data.dto.schedule.ScheduleListResponse
import com.hyundai.myexperience.data.mapper.mapToErrorResponse
import com.hyundai.myexperience.data.remote.service.ScheduleListService
import javax.inject.Inject

class ScheduleListDataSource @Inject constructor(
    private val service: ScheduleListService
) {
    suspend fun responseExperienceScheduleList(): ScheduleListResponse? {
        val response = service.responseExperienceScheduleList()

        if (response.isSuccessful) return response.body()

        val error = response.errorBody()?.mapToErrorResponse()
        Log.d("response", error?.message ?: "null")

        return null
    }

    suspend fun responsePleasureScheduleList(): ScheduleListResponse? {
        val response = service.responsePleasureScheduleList()

        if (response.isSuccessful) return response.body()

        val error = response.errorBody()?.mapToErrorResponse()
        Log.d("response", error?.message ?: "null")

        return null
    }
}