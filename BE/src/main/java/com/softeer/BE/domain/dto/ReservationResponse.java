package com.softeer.BE.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ReservationResponse {
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class Step1CarStatus{
        private String carName;
        private boolean isAvailable;
        public static Step1CarStatus of(String carName, boolean isAvailable){
            return new Step1CarStatus(
                    carName,
                    isAvailable
            );
        }
    }
}
