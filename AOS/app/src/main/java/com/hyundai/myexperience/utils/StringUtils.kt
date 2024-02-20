package com.hyundai.myexperience.utils

fun convertDateFormat(value: String): String {
    return value.split("-").subList(1, 3).joinToString(".")
}