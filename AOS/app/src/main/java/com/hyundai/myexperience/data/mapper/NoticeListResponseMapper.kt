package com.hyundai.myexperience.data.mapper

import com.hyundai.myexperience.data.dto.NoticeListResponse
import com.hyundai.myexperience.data.entity.NoticeList
import com.hyundai.myexperience.data.entity.NoticesItem

fun NoticeListResponse.mapToNoticeList(): NoticeList {
    return NoticeList(
        notices = result.values.convertToNoticeList()
    )
}

fun List<NoticeListResponse.Result.Value>.convertToNoticeList(): List<NoticesItem>{
    val noticeList = mutableListOf<NoticesItem>()
    for (value in this) {
        noticeList.add(NoticesItem(noticeTitle = value.title))
    }
    return noticeList
}