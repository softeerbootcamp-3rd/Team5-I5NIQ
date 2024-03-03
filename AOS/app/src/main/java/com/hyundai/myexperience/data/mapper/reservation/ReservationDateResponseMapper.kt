package com.hyundai.myexperience.data.mapper.reservation

import com.hyundai.myexperience.RESERVATION_STATUS_ABLE
import com.hyundai.myexperience.RESERVATION_STATUS_UNABLE
import com.hyundai.myexperience.data.dto.reservation.ReservationDateResponse
import com.hyundai.myexperience.data.entity.reservation.ReservationDate

fun ReservationDateResponse.Result.mapToReservationDate(): ReservationDate {
    return ReservationDate(
        id = -1,
        date = key,
        status = getStatus(value)
    )
}

private fun getStatus(value: String): String {
    return when (value) {
        "POSSIBLE" -> RESERVATION_STATUS_ABLE
        "IMPOSSIBLE_YET" -> RESERVATION_STATUS_UNABLE
        else -> RESERVATION_STATUS_UNABLE
    }
}