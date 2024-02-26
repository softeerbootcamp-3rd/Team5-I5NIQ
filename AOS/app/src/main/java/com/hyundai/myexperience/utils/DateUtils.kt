package com.hyundai.myexperience.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Locale

fun String.formatScheduleDate(format: String): String {
    return try {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val outputFormat = SimpleDateFormat(format, Locale.getDefault())

        val date = inputFormat.parse(this)!!

        outputFormat.format(date)
    } catch (e: ParseException) {
        this
    }
}

fun String.formatMyPageDate(): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.KOREAN)
    val outputFormat = SimpleDateFormat("yyyy년 M월 d일 a h시", Locale.KOREAN)

    val date = inputFormat.parse(this)
    return outputFormat.format(date!!)
}