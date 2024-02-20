package com.hyundai.myexperience.data.mapper

import com.hyundai.myexperience.data.dto.reservation.ReservationProgramResponse
import com.hyundai.myexperience.data.entity.Level
import com.hyundai.myexperience.data.entity.LevelsItem


fun ReservationProgramResponse.Result.CompanyProgram.toLevelsItem(): LevelsItem {
    return LevelsItem(
        companyName,
        programs.map { it.toLevel() }
    )
}

fun ReservationProgramResponse.Result.CompanyProgram.Program.toLevel(): Level {
    return Level(
        programLevel,
        canReservation.toString()
    )
}