package com.hyundai.myexperience.data.mapper

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
import com.hyundai.myexperience.RESERVATION_STATUS_ABLE
import com.hyundai.myexperience.RESERVATION_STATUS_UNABLE
import com.hyundai.myexperience.TYPE_TAXI
import com.hyundai.myexperience.data.dto.reservation.ReservationProgramResponse
import com.hyundai.myexperience.data.entity.Level
import com.hyundai.myexperience.data.entity.LevelsItem


fun ReservationProgramResponse.Result.Program.CompanyProgram.toLevelsItem(): LevelsItem {
    return LevelsItem(
        getCompanyName(companyName),
        programs.map { it.toLevel() }
    )
}

fun ReservationProgramResponse.Result.Program.CompanyProgram.Program.toLevel(): Level {
    return Level(
        getLevel(programLevel),
        getStatus(canReservation)
    )
}

private fun getCompanyName(value: String): String {
    return when(value) {
        "HYUNDAI" -> COMPANY_HYUNDAI
        "KIA" -> COMPANY_KIA
        "GENESIS" -> COMPANY_GENESIS
        "HMG" -> COMPANY_HMG
        "TAXI" -> TYPE_TAXI
        else -> value.split("_").joinToString(" ")
    }
}

private fun getLevel(value: String): String {
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

private fun getStatus(value: Boolean): String {
    return if (value) RESERVATION_STATUS_ABLE else RESERVATION_STATUS_UNABLE
}