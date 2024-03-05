package com.hyundai.myexperience.data.mapper

import com.hyundai.myexperience.data.dto.notice.NoticeDetailResponse
import com.hyundai.myexperience.data.entity.notice.NoticeDetailItem

fun NoticeDetailResponse.mapToNoticesItem(): NoticeDetailItem {
    return NoticeDetailItem(
        noticeId = result.id,
        noticeDate = "2024년 1월 1일",
        noticeTitle = result.title,
        imageName = result.imageName,
        imageUrl = result.imageUrl,
        content = result.content
    )
}