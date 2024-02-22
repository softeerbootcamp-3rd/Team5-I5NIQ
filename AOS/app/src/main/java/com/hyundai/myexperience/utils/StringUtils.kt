package com.hyundai.myexperience.utils

import com.hyundai.myexperience.COMPANY_GENESIS
import com.hyundai.myexperience.COMPANY_HMG
import com.hyundai.myexperience.COMPANY_HYUNDAI
import com.hyundai.myexperience.COMPANY_KIA
import com.hyundai.myexperience.PROGRAM_LEVEL_1
import com.hyundai.myexperience.PROGRAM_LEVEL_2
import com.hyundai.myexperience.PROGRAM_LEVEL_3
import com.hyundai.myexperience.PROGRAM_LEVEL_N_ADVANCED
import com.hyundai.myexperience.PROGRAM_LEVEL_N_MASTERS
import com.hyundai.myexperience.PROGRAM_OFF_ROAD
import com.hyundai.myexperience.TYPE_TAXI

fun convertDateFormat(value: String): String {
    if (value.contains("T")) {
        return value.split("T")[1].substring(0..4)
    } else {
        return value.split("-").subList(1, 3).joinToString(".")
    }
}

fun getCompanyName(value: String): String {
    return when(value) {
        "HYUNDAI" -> COMPANY_HYUNDAI
        "KIA" -> COMPANY_KIA
        "GENESIS" -> COMPANY_GENESIS
        "HMG" -> COMPANY_HMG
        "TAXI" -> TYPE_TAXI
        else -> value.split("_").joinToString(" ")
    }
}

fun getLevel(value: String): String {
    return when(value) {
        "LEVEL_1" -> PROGRAM_LEVEL_1
        "LEVEL_2" -> PROGRAM_LEVEL_2
        "LEVEL_3" -> PROGRAM_LEVEL_3
        "OFF_ROAD" -> PROGRAM_OFF_ROAD
        "N_ADVANCED" -> PROGRAM_LEVEL_N_ADVANCED
        "N_MASTERS" -> PROGRAM_LEVEL_N_MASTERS
        else -> value.split("_").joinToString(" ")
    }
}