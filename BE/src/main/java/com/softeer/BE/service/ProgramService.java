package com.softeer.BE.service;

import com.softeer.BE.domain.dto.KeyAndList;
import com.softeer.BE.domain.entity.Program;
import com.softeer.BE.domain.entity.enums.ProgramCategory;
import com.softeer.BE.domain.entity.enums.ProgramLevel;
import com.softeer.BE.domain.entity.enums.ProgramName;
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

    public List<KeyAndList<ProgramName, ProgramCategory>> getCategoryListAt(LocalDate localDate) {
        List<Program> programList = programRepository.findAllByDate(localDate);
        programList.sort(((o1, o2) -> {
            if (o1.getName() == o2.getName()) return o1.getCategory().compareTo(o2.getCategory());
            else return o1.getName().compareTo(o2.getName());
        }));
        List<KeyAndList<ProgramName, ProgramCategory>> categoryList = new ArrayList<>();
        for(Program program : programList) {
            if(categoryList.isEmpty() || categoryList.get(categoryList.size()-1).getKey() != program.getName()) {
                List<ProgramCategory> categories = new ArrayList<>(List.of(program.getCategory()));
                categoryList.add(new KeyAndList<>(program.getName(), categories));
            }
            else {
                KeyAndList<ProgramName, ProgramCategory> prev = categoryList.get(categoryList.size() - 1);
                if(prev.getList().get(prev.getList().size() - 1) != program.getCategory()) {
                    categoryList.get(categoryList.size()-1).getList().add(program.getCategory());
                }
            }
        }
        return categoryList;
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
