package com.hyundai.myexperience.data.mapper.reservation

import com.hyundai.myexperience.RESERVATION_STATUS_ABLE
import com.hyundai.myexperience.RESERVATION_STATUS_UNABLE
import com.hyundai.myexperience.data.dto.reservation.ReservationProgramByDateResponse
import com.hyundai.myexperience.data.entity.reservation.Level
import com.hyundai.myexperience.data.entity.reservation.LevelsItem
import com.hyundai.myexperience.utils.getCompanyName
import com.hyundai.myexperience.utils.getLevel

fun ReservationProgramByDateResponse.Result.Company.mapToLevelsItem(): LevelsItem {
    return LevelsItem(
        getCompanyName(key),
        list.map { it.mapToLevel() }
    )
}

private fun ReservationProgramByDateResponse.Result.Company.Program.mapToLevel(): Level {
    return Level(
        getLevel(programLevel),
        programId,
        getStatus(reservationStatus)
    )
}

private fun getStatus(value: String): String {
    return when (value) {
        "POSSIBLE" -> RESERVATION_STATUS_ABLE
        "IMPOSSIBLE" -> RESERVATION_STATUS_UNABLE
        else -> RESERVATION_STATUS_UNABLE
    }
}