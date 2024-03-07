package com.hyundai.myexperience.data.entity.notice

data class NoticesItem (
    val noticeId: Int,
    val noticeDate: String = "",
    var noticeTitle: String = ""
)