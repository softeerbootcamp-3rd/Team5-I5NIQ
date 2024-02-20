package com.hyundai.myexperience.data.mapper

import com.hyundai.myexperience.RESERVATION_STATUS_ABLE
import com.hyundai.myexperience.RESERVATION_STATUS_SOLDOUT
import com.hyundai.myexperience.data.dto.reservation.ReservationCarDateResponse
import com.hyundai.myexperience.data.entity.ReservationDate
import com.hyundai.myexperience.data.entity.ReservationDatesItem

fun ReservationCarDateResponse.Result.SelectMenu.mapToReservationDatesItem(): ReservationDatesItem {
    return ReservationDatesItem(
        carName,
        programDates.map { it.mapToReservationDate() }
    )
}

fun ReservationCarDateResponse.Result.SelectMenu.ProgramDate.mapToReservationDate(): ReservationDate {
    return ReservationDate(
        getDate(reservationDate),
        getStatus(canReservation)
    )
}

private fun getDate(value: String): String {
    return value.split("-").subList(1, 3).joinToString(".")
}

private fun getStatus(value: Boolean): String {
    return if (value) RESERVATION_STATUS_ABLE else RESERVATION_STATUS_SOLDOUT
}