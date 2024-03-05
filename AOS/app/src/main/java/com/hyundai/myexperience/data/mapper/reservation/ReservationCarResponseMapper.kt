package com.hyundai.myexperience.data.mapper.reservation

import com.hyundai.myexperience.RESERVATION_STATUS_ABLE
import com.hyundai.myexperience.RESERVATION_STATUS_UNABLE
import com.hyundai.myexperience.data.dto.reservation.ReservationCarResponse
import com.hyundai.myexperience.data.entity.reservation.ReservationCar

fun ReservationCarResponse.Result.mapToReservationCar(): ReservationCar {
    return ReservationCar(
        id = carId,
        name = carName,
        status = getStatus(isAvailable)
    )
}

private fun getStatus(value: Boolean): String {
    return if (value) RESERVATION_STATUS_ABLE else RESERVATION_STATUS_UNABLE
}