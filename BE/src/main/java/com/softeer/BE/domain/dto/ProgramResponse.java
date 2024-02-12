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
import java.util.List;

public class ProgramResponse {
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class ProgramCarStatusList{
        private Long programId;
        private ProgramName programName;
        private ProgramCategory programCategory;
        private ProgramLevel programLevel;
        private LocalDate startDateTime;
        private Long cost;
        private List<KeyAndValue<String, ReservationStatus>> carStatusList;
        public static ProgramCarStatusList of(Program p,
                                              LocalDate startDateTime,
                                              List<KeyAndValue<String, ReservationStatus>> carStatusList){
            return new ProgramCarStatusList(p.getId(), p.getName(), p.getCategory(),
                    p.getLevel(),startDateTime, p.getCost(), carStatusList);
        }
    }
}
