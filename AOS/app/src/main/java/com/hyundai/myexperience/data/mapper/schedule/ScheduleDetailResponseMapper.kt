package com.hyundai.myexperience.data.mapper.schedule

import com.hyundai.myexperience.data.dto.schedule.ScheduleDetailResponse
import com.hyundai.myexperience.data.entity.schedule.ScheduleDetailsItem
import com.hyundai.myexperience.utils.getCompanyName
import com.hyundai.myexperience.utils.getLevel

fun ScheduleDetailResponse.Result.mapToScheduleDetailsItem(): ScheduleDetailsItem {
    return ScheduleDetailsItem(
        category = getLevel(key),
        carList = list.map { getCompanyName(it) }.joinToString(" / ")
    )
}