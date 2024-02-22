package com.hyundai.myexperience.data.mapper.schedule

import com.hyundai.myexperience.data.dto.schedule.ScheduleDetailResponse
import com.hyundai.myexperience.data.entity.schedule.ScheduleDetailsItem

fun ScheduleDetailResponse.Result.mapToScheduleDetailsItem(): ScheduleDetailsItem {
    return ScheduleDetailsItem(
        category = key,
        carList = list
    )
}