package com.hyundai.myexperience.data.mapper.schedule

import com.hyundai.myexperience.data.dto.schedule.ScheduleListResponse
import com.hyundai.myexperience.data.entity.schedule.ScheduleList
import com.hyundai.myexperience.data.entity.schedule.SchedulesItem

fun ScheduleListResponse.mapToScheduleList(): ScheduleList {
    return ScheduleList(
        schedules = result.values.mapToSchedulesItemList()
    )
}

fun List<String>.mapToSchedulesItemList(): List<SchedulesItem> {
    val schedulesItemList = mutableListOf<SchedulesItem>()
    for (value in this)
        schedulesItemList.add(SchedulesItem(scheduleDate = value))
    return schedulesItemList
}