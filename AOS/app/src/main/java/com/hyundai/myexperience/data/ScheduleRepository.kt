package com.hyundai.myexperience.data

import com.hyundai.myexperience.data.entity.schedule.ScheduleDetailsItem
import com.hyundai.myexperience.data.entity.schedule.SchedulesItem
import com.hyundai.myexperience.data.mapper.schedule.mapToScheduleDetailsItem
import com.hyundai.myexperience.data.mapper.schedule.mapToScheduleList
import com.hyundai.myexperience.data.remote.ScheduleDataSource
import javax.inject.Inject

class ScheduleRepository @Inject constructor(
    private val scheduleDataSource: ScheduleDataSource
) {
    suspend fun requestSchedules(program: String): List<SchedulesItem>? {
        return scheduleDataSource.requestSchedules(program)?.mapToScheduleList()?.schedules
    }

    suspend fun requestScheduleDetail(program: String, date: String): List<ScheduleDetailsItem>? {
        return scheduleDataSource.requestScheduleDetail(program, date)?.map { it.mapToScheduleDetailsItem() }
    }
}