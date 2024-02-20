package com.hyundai.myexperience.data.mapper

import com.hyundai.myexperience.data.dto.NoticeDetailResponse
import com.hyundai.myexperience.data.entity.NoticesItem

fun NoticeDetailResponse.mapToNoticesItem(): NoticesItem {
    return NoticesItem(
        noticeId = result.id, noticeDate = "2024년 1월 1일", noticeTitle = result.title, noticeDetail = result.content
    )
}