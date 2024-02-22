package com.hyundai.myexperience.data.mapper.reservation

import com.hyundai.myexperience.RESERVATION_STATUS_ABLE
import com.hyundai.myexperience.RESERVATION_STATUS_UNABLE
import com.hyundai.myexperience.data.dto.reservation.ReservationProgramResponse
import com.hyundai.myexperience.data.entity.reservation.Level
import com.hyundai.myexperience.data.entity.reservation.LevelsItem
import com.hyundai.myexperience.utils.getCompanyName
import com.hyundai.myexperience.utils.getLevel


fun ReservationProgramResponse.Result.Program.CompanyProgram.mapToLevelsItem(): LevelsItem {
    return LevelsItem(
        getCompanyName(companyName),
        programs.map { it.mapToLevel() }
    )
}

fun ReservationProgramResponse.Result.Program.CompanyProgram.Program.mapToLevel(): Level {
    return Level(
        getLevel(programLevel),
        programId,
        getStatus(canReservation)
    )
}

private fun getStatus(value: Boolean): String {
    return if (value) RESERVATION_STATUS_ABLE else RESERVATION_STATUS_UNABLE
}