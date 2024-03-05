package com.hyundai.myexperience.data.entity.notice

data class NoticeDetailItem (
    val noticeId: Int,
    val noticeDate: String,
    val noticeTitle: String,
    val imageName: String,
    val imageUrl: String,
    val content: String
)