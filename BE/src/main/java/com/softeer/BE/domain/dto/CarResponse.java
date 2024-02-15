package com.softeer.BE.domain.dto;

import com.softeer.BE.domain.entity.Car;
import com.softeer.BE.domain.entity.enums.ReservationStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class CarResponse {
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class CarStatus {
        private Long carId;
        private String carName;
        private ReservationStatus reservationStatus;
        public static CarStatus of(Car car, ReservationStatus status) {
            return new CarStatus(car.getId(), car.getName(), status);
        }
    }
}
