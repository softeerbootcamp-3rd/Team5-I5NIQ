package com.hyundai.myexperience.data.mapper.reservation

import com.hyundai.myexperience.RESERVATION_STATUS_ABLE
import com.hyundai.myexperience.RESERVATION_STATUS_SOLDOUT
import com.hyundai.myexperience.data.dto.reservation.ReservationSessionResponse
import com.hyundai.myexperience.data.entity.reservation.ReservationDate

fun ReservationSessionResponse.Result.Class.mapToReservationDateItem(): ReservationDate {
    return ReservationDate(
        reservationDateTime,
        classId,
        getStatus(canReservation)
    )
}

private fun getStatus(value: Boolean): String {
    return if (value) RESERVATION_STATUS_ABLE else RESERVATION_STATUS_SOLDOUT
}