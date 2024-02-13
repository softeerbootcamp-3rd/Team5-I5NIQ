package com.softeer.BE.domain.dto;

import com.softeer.BE.domain.entity.Car;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ReservationResponse {
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class Step1CarStatus{
        private Long carId;
        private String carName;
        private boolean isAvailable;
        public static Step1CarStatus of(Car car, boolean isAvailable){
            return new Step1CarStatus(
                    car.getId(),
                    car.getName(),
                    isAvailable
            );
        }
    }
}
