package com.hyundai.myexperience.utils

import com.hyundai.myexperience.data.entity.NoticesItem
import com.hyundai.myexperience.data.entity.SchedulesItem
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.Locale

/*fun formatDate(inputDate: String): String {
    val inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val outputFormat = DateTimeFormatter.ofPattern("yyyy년 M월 d일")
    val date = LocalDate.parse(inputDate, inputFormat)
    return outputFormat.format(date)
}*/

fun String.formatScheduleDate(): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val outputFormat = SimpleDateFormat("yyyy년 M월 d일", Locale.getDefault())
    val date = inputFormat.parse(this)
    return outputFormat.format(date!!)
}