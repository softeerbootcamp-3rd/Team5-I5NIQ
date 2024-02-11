package com.softeer.BE.service;

import com.softeer.BE.repository.ProgramRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProgramService {
    private ProgramRepository programRepository;

//    public List<NameAndList<ProgramCategory>> getCategoryListAt(LocalDate localDate) {
//        List<Program> programList = programRepository.findAllByDate(localDate);
//        programList.sort(((o1, o2) -> {
//            if (o1.getName() == o2.getName()) return o1.getCategory().compareTo(o2.getCategory());
//            else return o1.getName().compareTo(o2.getName());
//        }));
//        List<NameAndList<ProgramCategory>> categoryList = new ArrayList<>();
//        for(Program program : programList) {
//
//        }
//        return categoryList;
//    }
}
