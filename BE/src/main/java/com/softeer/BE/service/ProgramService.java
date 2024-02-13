package com.softeer.BE.service;

import com.softeer.BE.domain.dto.KeyAndList;
import com.softeer.BE.domain.dto.KeyAndValue;
import com.softeer.BE.domain.entity.ClassCar;
import com.softeer.BE.domain.entity.DrivingClass;
import com.softeer.BE.domain.entity.Participation;
import com.softeer.BE.domain.entity.Program;
import com.softeer.BE.domain.entity.enums.ProgramCategory;
import com.softeer.BE.domain.entity.enums.ProgramLevel;
import com.softeer.BE.domain.entity.enums.ProgramName;
import com.softeer.BE.domain.entity.enums.ReservationStatus;
import com.softeer.BE.repository.ProgramRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProgramService {
    private final ProgramRepository programRepository;

    public List<KeyAndList<ProgramName, KeyAndList<ProgramCategory, KeyAndValue<ProgramLevel, ReservationStatus>>>>
    getCategoryListAt(LocalDate localDate) {
        List<Program> programList = programRepository.findAllByDate(localDate);
        programList.sort(((o1, o2) -> {
            if (o1.getName() == o2.getName()) {
                if(o1.getCategory() == o2.getCategory()) return o1.getLevel().compareTo(o2.getLevel());
                else return o1.getCategory().compareTo(o2.getCategory());
            }
            else return o1.getName().compareTo(o2.getName());
        }));
        List<KeyAndList<ProgramName, KeyAndList<ProgramCategory, KeyAndValue<ProgramLevel, ReservationStatus>>>>
                levelStatusList = new ArrayList<>();
        for(Program program : programList) {
            ReservationStatus status = ReservationStatus.FULL;
            for(DrivingClass drivingClass : program.getDrivingClassList()) {
                Long currentNumber = 0L;
                for(ClassCar car : drivingClass.getCarList())
                    for(Participation participation : car.getParticipationList())
                        currentNumber += participation.getParticipants();
                if(currentNumber < program.getMaximumOccupancy()) {
                    status = ReservationStatus.POSSIBLE;
                    break;
                }
            }
            if(levelStatusList.isEmpty() || levelStatusList.get(levelStatusList.size()-1).getKey() != program.getName()) {
                KeyAndValue<ProgramLevel, ReservationStatus> levelAndStatus = new KeyAndValue<>(program.getLevel(), status);
                KeyAndList<ProgramCategory, KeyAndValue<ProgramLevel, ReservationStatus>>
                        categoryAndKeyValue = new KeyAndList<>(program.getCategory(), new ArrayList<>(List.of(levelAndStatus)));
                KeyAndList<ProgramName, KeyAndList<ProgramCategory, KeyAndValue<ProgramLevel, ReservationStatus>>>
                        nameAndKeyList = new KeyAndList<>(program.getName(), new ArrayList<>(List.of(categoryAndKeyValue)));
                levelStatusList.add(nameAndKeyList);
                continue;
            }

            KeyAndList<ProgramName, KeyAndList<ProgramCategory, KeyAndValue<ProgramLevel, ReservationStatus>>>
                    last = levelStatusList.get(levelStatusList.size()-1);
            if(last.getList().get(last.getList().size()-1).getKey() != program.getCategory()) {
                KeyAndValue<ProgramLevel, ReservationStatus> levelAndStatus = new KeyAndValue<>(program.getLevel(), status);
                KeyAndList<ProgramCategory, KeyAndValue<ProgramLevel, ReservationStatus>>
                        categoryAndKeyValue = new KeyAndList<>(program.getCategory(), new ArrayList<>(List.of(levelAndStatus)));
                last.getList().add(categoryAndKeyValue);
            }
            else {
                KeyAndValue<ProgramLevel, ReservationStatus> levelAndStatus = new KeyAndValue<>(program.getLevel(), status);
                last.getList().get(last.getList().size()-1).getList().add(levelAndStatus);
            }
        }
        return levelStatusList;
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
