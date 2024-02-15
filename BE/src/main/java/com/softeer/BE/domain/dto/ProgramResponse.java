package com.softeer.BE.domain.dto;

import com.softeer.BE.domain.entity.*;
import com.softeer.BE.domain.entity.enums.ProgramCategory;
import com.softeer.BE.domain.entity.enums.ProgramLevel;
import com.softeer.BE.domain.entity.enums.ProgramName;
import com.softeer.BE.domain.entity.enums.ReservationStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class ProgramResponse {
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class ProgramCarStatusList{
        private Long programId;
        private ProgramName programName;
        private ProgramCategory programCategory;
        private ProgramLevel programLevel;
        private LocalDate startDate;
        private Long cost;
        private List<CarResponse.CarStatus> carStatusList;
        public static ProgramCarStatusList of(Program p,
                                              LocalDate startDate,
                                              List<CarResponse.CarStatus> carStatusList){
            return new ProgramCarStatusList(p.getId(), p.getName(), p.getCategory(),
                    p.getLevel(), startDate, p.getCost(), carStatusList);
        }
    }
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class ProgramReservationInfo {
        private Long programId;
        private ProgramLevel programLevel;
        private ReservationStatus reservationStatus;
        public static ProgramReservationInfo of(Program p, ReservationStatus status) {
            return new ProgramReservationInfo(p.getId(), p.getLevel(), status);
        }
    }
}
