package com.softeer.BE.service;

import com.softeer.BE.domain.dto.CursorResult;
import com.softeer.BE.domain.dto.KeyAndList;
import com.softeer.BE.domain.entity.Program;
import com.softeer.BE.domain.entity.enums.ProgramCategory;
import com.softeer.BE.domain.entity.enums.ProgramLevel;
import com.softeer.BE.domain.entity.enums.ProgramName;
import com.softeer.BE.repository.DrivingClassRepository;
import com.softeer.BE.repository.ProgramRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final DrivingClassRepository drivingClassRepository;
    private final ProgramRepository programRepository;

    public CursorResult<LocalDate> getScheduleDateList(ProgramName programName, LocalDate lastLocalDate, Integer pageSize) {
        List<Date> dateList = this.drivingClassRepository.findAll(programName, lastLocalDate, PageRequest.of(0, pageSize)).getContent();
        List<LocalDate> localDateList = new ArrayList<>();
        for(Date date : dateList) {
            LocalDate localDate = new java.sql.Date(date.getTime()).toLocalDate();
            localDateList.add(localDate);
        }
        boolean hasNext = false;
        if(!localDateList.isEmpty()) {
            LocalDate lastDate = localDateList.get(localDateList.size()-1);
            hasNext = this.drivingClassRepository.existsByDateLessThan(lastDate);
        }
        return new CursorResult<>(localDateList, hasNext);
    }

    public List<KeyAndList<ProgramLevel, ProgramCategory>> getCategoryListAt(ProgramName programName, LocalDate localDate) {
        List<Program> programList = programRepository.findAllByDateAndName(localDate, programName);
        programList.sort(((o1, o2) -> {
            if (o1.getLevel() == o2.getLevel()) return o1.getCategory().compareTo(o2.getCategory());
            else return o1.getLevel().compareTo(o2.getLevel());
        }));
        List<KeyAndList<ProgramLevel, ProgramCategory>> categoryList = new ArrayList<>();
        for(Program program : programList) {
            if(categoryList.isEmpty() || categoryList.get(categoryList.size()-1).getKey() != program.getLevel()) {
                List<ProgramCategory> categories = new ArrayList<>(List.of(program.getCategory()));
                categoryList.add(new KeyAndList<>(program.getLevel(), categories));
            }
            else {
                KeyAndList<ProgramLevel, ProgramCategory> prev = categoryList.get(categoryList.size() - 1);
                if(prev.getList().get(prev.getList().size() - 1) != program.getCategory()) {
                    categoryList.get(categoryList.size()-1).getList().add(program.getCategory());
                }
            }
        }
        return categoryList;
    }
}
