package com.hyundai.myexperience.utils

fun convertDateFormat(value: String): String {
    if (value.contains("T")) {
        return value.split("T")[1].substring(0..4)
    } else {
        return value.split("-").subList(1, 3).joinToString(".")
    }
}