package com.hyundai.myexperience.data

import com.hyundai.myexperience.data.entity.SchedulesItem
import com.hyundai.myexperience.data.mapper.mapToScheduleList
import com.hyundai.myexperience.data.remote.ScheduleListDataSource
import javax.inject.Inject

class ScheduleListRepository @Inject constructor(
    private val scheduleListDataSource: ScheduleListDataSource
) {
    suspend fun responsePleasure(): List<SchedulesItem>? {
        val schedules = scheduleListDataSource.responsePleasureScheduleList()?.mapToScheduleList()

        return schedules?.schedules
    }

    suspend fun responseExperience(): List<SchedulesItem>? {
        val schedules = scheduleListDataSource.responseExperienceScheduleList()?.mapToScheduleList()

        return schedules?.schedules
    }
}