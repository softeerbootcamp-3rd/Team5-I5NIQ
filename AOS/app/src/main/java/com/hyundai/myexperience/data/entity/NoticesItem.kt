package com.hyundai.myexperience.data.entity

data class NoticesItem (
    val noticeId: Int,
    val noticeDate: String = "",
    var noticeTitle: String = "",
    val noticeDetail: String = "",
)