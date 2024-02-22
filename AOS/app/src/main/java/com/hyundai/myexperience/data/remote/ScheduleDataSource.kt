package com.hyundai.myexperience.data.remote

import android.util.Log
import com.hyundai.myexperience.data.dto.schedule.ScheduleListResponse
import com.hyundai.myexperience.data.mapper.mapToErrorResponse
import com.hyundai.myexperience.data.remote.service.ScheduleService
import javax.inject.Inject

class ScheduleDataSource @Inject constructor(
    private val service: ScheduleService
) {
    suspend fun requestSchedules(program: String): ScheduleListResponse? {
        val response = service.requestSchedules(program)

        if (response.isSuccessful) return response.body()

        val error = response.errorBody()?.mapToErrorResponse()
        Log.d("response", error?.message ?: "null")

        return null
    }
}