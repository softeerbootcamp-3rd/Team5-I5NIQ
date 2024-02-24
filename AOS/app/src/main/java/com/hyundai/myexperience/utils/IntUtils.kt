package com.hyundai.myexperience.utils

fun Int.toCostWithSeparator(): String {
    return "%,d".format(this)
}