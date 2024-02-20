package com.hyundai.myexperience.data.mapper

import com.hyundai.myexperience.data.dto.schedule.ScheduleListResponse
import com.hyundai.myexperience.data.entity.ScheduleList
import com.hyundai.myexperience.data.entity.SchedulesItem

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