package com.hyundai.myexperience.utils

import java.text.SimpleDateFormat
import java.util.Locale

fun String.formatScheduleDate(): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val outputFormat = SimpleDateFormat("yyyy년 M월 d일", Locale.getDefault())
    val date = inputFormat.parse(this)
    return outputFormat.format(date!!)
}