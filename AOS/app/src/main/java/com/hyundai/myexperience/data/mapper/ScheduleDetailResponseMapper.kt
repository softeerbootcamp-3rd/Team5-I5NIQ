package com.hyundai.myexperience.data.mapper

import com.hyundai.myexperience.data.dto.notice.NoticeListResponse
import com.hyundai.myexperience.data.dto.schedule.ScheduleDetailListResponse
import com.hyundai.myexperience.data.entity.NoticesItem
import com.hyundai.myexperience.data.entity.ScheduleDetailList
import com.hyundai.myexperience.data.entity.ScheduleDetailsItem
import com.hyundai.myexperience.data.entity.SchedulesItem
import com.hyundai.myexperience.utils.formatScheduleDate

fun ScheduleDetailListResponse.mapToScheduleDetailList(): ScheduleDetailList {
    return ScheduleDetailList(
        scheduleDetails = result.mapToScheduleDetailsItemList()
    )
}

fun List<ScheduleDetailListResponse.Result>.mapToScheduleDetailsItemList(): List<ScheduleDetailsItem> {
    val detailsItemList = mutableListOf<ScheduleDetailsItem>()
    for (value in this)
        detailsItemList.add(ScheduleDetailsItem(
            category = value.key,
            carList = value.list))
    return detailsItemList
}