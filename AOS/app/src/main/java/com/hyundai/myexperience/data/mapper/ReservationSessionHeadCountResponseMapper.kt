package com.hyundai.myexperience.data.mapper

import com.hyundai.myexperience.SESSION
import com.hyundai.myexperience.data.dto.reservation.ReservationSessionResponse
import com.hyundai.myexperience.data.entity.ReservationDate

fun ReservationSessionResponse.Result.Class.mapToReservationDateItem(): ReservationDate {
    return ReservationDate(
        SESSION,
        classId,
        reservationDateTime
    )
}