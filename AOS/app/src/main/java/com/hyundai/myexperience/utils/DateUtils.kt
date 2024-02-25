package com.hyundai.myexperience.utils

import java.text.SimpleDateFormat
import java.util.Locale

fun String.formatScheduleDate(): String {
    if (this == "") return this

    val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val outputFormat = SimpleDateFormat("yyyy년 M월 d일", Locale.getDefault())

    val date = inputFormat.parse(this)!!

    return outputFormat.format(date)
}

fun String.formatMyPageDate(): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.KOREAN)
    val outputFormat = SimpleDateFormat("yyyy년 M월 d일 a h시", Locale.KOREAN)

    val date = inputFormat.parse(this)
    return outputFormat.format(date!!)
}