package com.hyundai.myexperience.data.mapper

import com.hyundai.myexperience.data.dto.notice.NoticeListResponse
import com.hyundai.myexperience.data.entity.NoticeList
import com.hyundai.myexperience.data.entity.NoticesItem

fun NoticeListResponse.mapToNoticeList(): NoticeList {
    return NoticeList(
        notices = result.values.mapToNoticesItemList()
    )
}

fun List<NoticeListResponse.Result.Value>.mapToNoticesItemList(): List<NoticesItem> {
    val noticesItemList = mutableListOf<NoticesItem>()
    for (value in this)
        noticesItemList.add(NoticesItem(noticeId = value.id, noticeDate = "2024년 1월 1일", noticeTitle = value.title))
    return noticesItemList
}