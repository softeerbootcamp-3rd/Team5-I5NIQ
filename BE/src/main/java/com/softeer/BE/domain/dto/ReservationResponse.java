package com.softeer.BE.domain.dto;

import com.softeer.BE.domain.entity.Car;
import com.softeer.BE.domain.entity.Program;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

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

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class ClassDetail{
        private String monthDate;
        private String status;
        public static ClassDetail of(String monthDate, String status){
            return new ClassDetail(
                    monthDate,
                    status
            );
        }
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class ProgramDetail{
        private String category;
        private String level;
        private List<ClassDetail> classDetailList;
        public static ProgramDetail of(Program program, List<ClassDetail> classDetailList){
            return new ProgramDetail(
                    program.getCategory().name(),
                    program.getLevel().name(),
                    classDetailList
            );
        }
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class Step2ProgramStatus{
        private Long programId;
        private String programName;
        private List<ProgramDetail> programDetailList;
        public static Step2ProgramStatus of(Program program, List<ProgramDetail> programDetailList){
            return new Step2ProgramStatus(
                    program.getId(),
                    program.getName().name(),
                    programDetailList
            );
        }
    }
}
