package com.hyundai.myexperience.data

import com.hyundai.myexperience.data.entity.SchedulesItem
import com.hyundai.myexperience.data.mapper.mapToScheduleList
import com.hyundai.myexperience.data.remote.ScheduleDataSource
import javax.inject.Inject

class ScheduleRepository @Inject constructor(
    private val scheduleDataSource: ScheduleDataSource
) {
    suspend fun requestExperienceSchedules(): List<SchedulesItem>? {
        val schedules = scheduleDataSource.requestExperienceSchedules()?.mapToScheduleList()

        return schedules?.schedules
    }

    suspend fun requestPleasureSchedules(): List<SchedulesItem>? {
        val schedules = scheduleDataSource.requestPleasureSchedules()?.mapToScheduleList()

        return schedules?.schedules
    }
}