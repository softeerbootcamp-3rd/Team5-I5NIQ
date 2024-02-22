package com.hyundai.myexperience.data

import com.hyundai.myexperience.data.entity.SchedulesItem
import com.hyundai.myexperience.data.mapper.mapToScheduleList
import com.hyundai.myexperience.data.remote.ScheduleDataSource
import javax.inject.Inject

class ScheduleRepository @Inject constructor(
    private val scheduleDataSource: ScheduleDataSource
) {
    suspend fun requestSchedules(program: String): List<SchedulesItem>? {
        val schedules = scheduleDataSource.requestSchedules(program)?.mapToScheduleList()

        return schedules?.schedules
    }
}