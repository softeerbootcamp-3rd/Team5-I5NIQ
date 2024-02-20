package com.hyundai.myexperience.data.mapper

import com.hyundai.myexperience.data.dto.NoticeListResponse
import com.hyundai.myexperience.data.entity.NoticeList
import com.hyundai.myexperience.data.entity.NoticesItem

fun NoticeListResponse.mapToNoticeList(): NoticeList {
    return NoticeList(
        notices = result.values.mapToNoticesItemList()
    )
}

fun List<NoticeListResponse.Result.Value>.mapToNoticesItemList(): List<NoticesItem>{
    val noticesItemList = mutableListOf<NoticesItem>()
    for (value in this) {
        noticesItemList.add(NoticesItem(noticeTitle = value.title))
    }
    return noticesItemList
}