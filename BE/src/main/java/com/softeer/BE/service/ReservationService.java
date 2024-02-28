package com.softeer.BE.service;

import com.softeer.BE.domain.dto.*;
import com.softeer.BE.domain.dto.ReservationStep3Response.ProgramSelectMenuStep3;
import com.softeer.BE.domain.entity.*;
import com.softeer.BE.domain.entity.enums.ProgramCategory;
import com.softeer.BE.domain.entity.enums.ProgramName;
import com.softeer.BE.domain.entity.enums.ReservationStatus;
import com.softeer.BE.global.apiPayload.code.statusEnums.ErrorStatus;
import com.softeer.BE.global.exception.GeneralHandler;
import com.softeer.BE.global.scheduler.ReservationPayCheckExecutorService;
import com.softeer.BE.repository.CarRepository;
import com.softeer.BE.repository.ClassCarRepository;
import com.softeer.BE.repository.ParticipationRepository;
import com.softeer.BE.repository.ProgramRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import com.softeer.BE.domain.entity.ClassCar;
import com.softeer.BE.domain.entity.DrivingClass;
import com.softeer.BE.domain.entity.Participation;
import com.softeer.BE.domain.entity.Program;
import com.softeer.BE.repository.DrivingClassRepository;
import java.time.format.DateTimeFormatter;
import java.util.AbstractMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReservationService {
    private final ClassCarRepository classCarRepository;
    private final ProgramRepository programRepository;
    private final CarRepository carRepository;
    private final ParticipationRepository participationRepository;
    private final ReservationPayCheckExecutorService payCheckScheduler;
    private final DrivingClassRepository drivingClassRepository;

    public ProgramSelectMenuStep3 searchForStep3AvailableClassCar(LocalDate reservationDate, long programId, long carId) {
        Program program = programRepository.findById(programId)
                .orElseThrow(() -> new GeneralHandler(ErrorStatus.PROGRAM_NOT_FOUND));
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new GeneralHandler(ErrorStatus.CAR_NOT_FOUND));
        List<ClassCar> classes = classCarRepository.findAllByStep2(reservationDate, programId, carId, LocalDateTime.now());
        List<ClassCarValidation> validationClasses = classes.stream().map(ClassCarValidation::of).toList();
        return ProgramSelectMenuStep3.of(validationClasses, program, car, reservationDate);
    }

    @AllArgsConstructor
    @Getter
    public static class ClassCarValidation {
        private ClassCar classCar;
        private boolean reservationAvailable;
        private long participationCount;
        private long participationOccupancy;

        public static ClassCarValidation of(ClassCar classCar) {
            long participationCount = classCar.getParticipationList().size();
            long occupancy = classCar.getMaximumOccupancy();
            boolean reservationAvailable = participationCount < occupancy;
            return new ClassCarValidation(classCar, reservationAvailable, participationCount, occupancy);
        }

        public LocalDateTime getClassStartDateTime() {
            return classCar.getDrivingClass().getStartDateTime();
        }

        public long getClassCarId() {
            return classCar.getId();
        }
    }

    private Logger logger = LoggerFactory.getLogger(ReservationService.class);

    @Transactional
    public boolean classCarReservation(long classCarId, long reservationSize, Users user) {
        // 현재 ClassCar가 속한 DrivingClass ID를 기반으로 모든 관련 ClassCar 인스턴스 락 적용
        List<ClassCar> classCarList = classCarRepository.lockClassCarsRelatedByDrivingClass(classCarId);
        ClassCar classCar = classCarRepository.findById(classCarId)
                .orElseThrow(() -> new GeneralHandler(ErrorStatus.CLASS_CAR_NOT_FOUND));
        // 예약하려는 classCar의 총 예약자 수
        Long sumParticipants = participationRepository.sumParticipantsByClassCarId(classCarId);
        long totalAmountOfClassCar = Optional.ofNullable(sumParticipants).orElse(0L);
        // 예약하려는 DrivingClass의 총 예약자 수
        long totalAmountOfClassCarList = calculateTotalParticipants(classCarList);
        if (classCar.canReservation(reservationSize, totalAmountOfClassCar, totalAmountOfClassCarList)) {
            long participationId = Participation.makeReservation(classCar, user, reservationSize, participationRepository);
            logger.info("insert into participation table");
            payCheckScheduler.executeTimer(participationId);
            return true;
        }
        throw new GeneralHandler(ErrorStatus.RESERVATION_FULL);
    }

    public List<ReservationResponse.Step1CarStatus> getStep1CarStatusList() {
        // 현재 일시가 예약 시작 시간과 예약 마감 시간 사이인 ClassCar 목록을 조회한다.
        LocalDateTime currentDateTime = LocalDateTime.now();
        List<ClassCar> availableClassCars = classCarRepository.findAvailableClassCars(currentDateTime);

        // Car 객체별로 최종 Step1CarStatus를 저장할 Map을 생성한다.
        Map<Long, ReservationResponse.Step1CarStatus> carStatusMap = new HashMap<>();

        availableClassCars.forEach(classCar -> {
            long totalParticipants = classCar.getParticipationList().stream()
                    .mapToLong(Participation::getParticipants)
                    .sum();
            long classMaxOccupancy = classCar.getMaximumOccupancy();
            long programMaxOccupancy = classCar.getDrivingClass().getProgram().getMaximumOccupancy();
            boolean isAvailable = totalParticipants < Math.min(classMaxOccupancy, programMaxOccupancy);

            // 현재 Car의 ID
            Long carId = classCar.getCar().getId();
            // 동일한 Car에 대해 이전에 저장된 상태가 있고, 해당 상태가 'available'이 true인 경우, 상태를 업데이트하지 않는다.
            if (!carStatusMap.containsKey(carId) || !carStatusMap.get(carId).getIsAvailable()) {
                carStatusMap.put(carId, ReservationResponse.Step1CarStatus.of(classCar.getCar(), isAvailable));
            }
        });
        // Map의 값들을 리스트로 변환하여 반환한다.
        return new ArrayList<>(carStatusMap.values());
    }

    public List<ReservationResponse.Step2ProgramStatus> getStep2ProgramStatusList(Long carId) {
        // carId를 기준으로 reservation_deadline 전인 DrivingClass 목록을 조회한다.
        List<DrivingClass> drivingClassList = drivingClassRepository.findDrivingClassesByCarIdAndBeforeReservationDeadline(carId);
        // 조회된 DrivingClass 목록을 Program의 이름별로 그룹화한다.
        Map<String, List<DrivingClass>> nameToClassMap = drivingClassList.stream()
                .collect(Collectors.groupingBy(drivingClass -> drivingClass.getProgram().getName().name()));

        // 각 Program 이름별로 Step2ProgramStatus DTO를 생성한다.
        return nameToClassMap.entrySet().stream().map(entry -> {
            String programName = entry.getKey();
            // ProgramDetailList를 생성한다.
            List<ReservationResponse.ProgramDetail> programDetails = createProgramDetailList(entry.getValue());
            return ReservationResponse.Step2ProgramStatus.of(programName, programDetails);
        }).collect(Collectors.toList());
    }

    private List<ReservationResponse.ProgramDetail> createProgramDetailList(List<DrivingClass> drivingClassList) {
        // Category-Level 별로 DrivingClass를 그룹화하여 ProgramDetail 리스트를 생성한다.
        return drivingClassList.stream()
                // category와 level을 조합한 복합 키로 그룹화한다.
                .collect(Collectors.groupingBy(drivingClass -> new AbstractMap.SimpleEntry<>(drivingClass.getProgram().getCategory(), drivingClass.getProgram().getLevel())))
                .entrySet().stream().map(categoryLevelEntry -> {
                    // 각 category-level 조합에 해당하는 DrivingClass 목록에 대해 ClassDetailList를 생성한다.
                    List<ReservationResponse.ClassDetail> classDetailList = categoryLevelEntry.getValue().stream().map(this::createClassDetail).collect(Collectors.toList());
                    Program program = categoryLevelEntry.getValue().get(0).getProgram();
                    return ReservationResponse.ProgramDetail.of(program, classDetailList);
                }).collect(Collectors.toList());
    }

    private ReservationResponse.ClassDetail createClassDetail(DrivingClass drivingClass) {
        String status = determineClassStatus(drivingClass);
        String monthDate = formatMonthDate(drivingClass.getStartDateTime());
        return ReservationResponse.ClassDetail.of(monthDate, status);
    }

    private String determineClassStatus(DrivingClass drivingClass) {
        LocalDateTime now = LocalDateTime.now();

        // 모든 ClassCar에 대해 매진 여부를 확인한다.
        boolean isSoldOut = drivingClass.getCarList().stream().anyMatch(car -> {
            // 각 ClassCar와 관련된 총 참여 인원을 계산한다.
            long totalParticipants = car.getParticipationList().stream()
                    .mapToLong(Participation::getParticipants)
                    .sum();

            // ClassCar의 maximumOccupancy와 Program의 maximumOccupancy 중 작은 값을 기준으로 한다.
            long maxOccupancyForCar = car.getMaximumOccupancy();
            long maxOccupancyForProgram = car.getDrivingClass().getProgram().getMaximumOccupancy();
            // 참여 인원 합이 effectiveMaxOccupancy 이상이면 매진으로 간주한다.
            return totalParticipants >= Math.min(maxOccupancyForCar, maxOccupancyForProgram);
        });

        if (now.isBefore(drivingClass.getReservationStartTime())) {
            return "예정"; // 예약 시작 기간 전
        } else if (isSoldOut) {
            return "매진"; // 예약 시작 기간 후 + 참여 인원 합 >= 최대 인원
        } else {
            return "가능"; // 예약 시작 기간 후 + 참여 인원 합 < 최대 인원
        }
    }

    private String formatMonthDate(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM.dd");
        return dateTime.format(formatter);
    }

    private long calculateTotalParticipants(List<ClassCar> classCarList){
        long totalAmount = 0;
        for(ClassCar classCar: classCarList){
            Long sumParticipants = participationRepository.sumParticipantsByClassCarId(classCar.getId());
            totalAmount += Optional.ofNullable(sumParticipants).orElse(0L);
        }
        return totalAmount;
    }

    public List<KeyAndValue<LocalDate, ReservationStatus>> getDateAndStatusList() {
        List<DrivingClass> drivingClassList = drivingClassRepository.findAvailableClass();
        List<KeyAndValue<LocalDate, ReservationStatus>> dateStatusList = new ArrayList<>();
        if(drivingClassList.isEmpty()) return dateStatusList;
        for(DrivingClass drivingClass : drivingClassList) {
            LocalDate localDate = drivingClass.getStartDateTime().toLocalDate();
            ReservationStatus status;
            Long maxOccupancy = drivingClass.getProgram().getMaximumOccupancy();
            Long nowCount = 0L;
            for(ClassCar classCar : drivingClass.getCarList()) {
                for(Participation participation : classCar.getParticipationList()) {
                    nowCount += participation.getParticipants();
                }
            }
            if(drivingClass.getReservationStartTime().isAfter(LocalDateTime.now()))
                status = ReservationStatus.IMPOSSIBLE_YET;
            else if(maxOccupancy > nowCount)
                status = ReservationStatus.POSSIBLE;
            else
                status = ReservationStatus.FULL;
            if(dateStatusList.isEmpty()) dateStatusList.add(new KeyAndValue<>(localDate, status));
            else if(!dateStatusList.get(dateStatusList.size()-1).getKey().isEqual(localDate))
                dateStatusList.add(new KeyAndValue<>(localDate, status));
            else if(status == ReservationStatus.IMPOSSIBLE_YET) continue;
            else if(status == ReservationStatus.FULL) {
                if(dateStatusList.get(dateStatusList.size()-1).getValue() == ReservationStatus.IMPOSSIBLE_YET)
                    dateStatusList.get(dateStatusList.size()-1).setValue(ReservationStatus.FULL);
            }
            else dateStatusList.get(dateStatusList.size()-1).setValue(ReservationStatus.POSSIBLE);
        }
        return dateStatusList;
    }

    public List<KeyAndList<ProgramName, KeyAndList<ProgramCategory, ProgramResponse.ProgramReservationInfo>>>
    getProgramAndStatusList(LocalDate date) {
        List<Program> programList = programRepository.findAll();
        List<KeyAndList<ProgramName, KeyAndList<ProgramCategory, ProgramResponse.ProgramReservationInfo>>>
                programStatusList = new ArrayList<>();
        programList.sort(((o1, o2) -> {
            if(o1.getName() == o2.getName()) {
                if(o1.getCategory() == o2.getCategory()) return o1.getLevel().compareTo(o2.getLevel());
                else return o1.getCategory().compareTo(o2.getCategory());
            }
            else return o1.getName().compareTo(o2.getName());
        }));

        int i = 0;
        for(ProgramName name : ProgramName.values()) {
            KeyAndList<ProgramName, KeyAndList<ProgramCategory, ProgramResponse.ProgramReservationInfo>> tmp1 = new KeyAndList<>(name, new ArrayList<>());
            for(ProgramCategory category : ProgramCategory.values()) {
                KeyAndList<ProgramCategory, ProgramResponse.ProgramReservationInfo> tmp2 = new KeyAndList<>(category, new ArrayList<>());
                while(i < programList.size() && programList.get(i).getName() == name && programList.get(i).getCategory() == category) {
                    Program program = programList.get(i);
                    List<DrivingClass> classList = drivingClassRepository.findByProgramAndStartDateTime(program, date);
                    boolean possible = false;
                    for(DrivingClass drivingClass : classList) {
                        Long currentNumber = 0L;
                        for(ClassCar car : drivingClass.getCarList())
                            for(Participation participation : car.getParticipationList())
                                currentNumber += participation.getParticipants();
                        if(currentNumber < program.getMaximumOccupancy()) {
                            possible = true;
                            break;
                        }
                    }
                    tmp2.getList().add(ProgramResponse.ProgramReservationInfo.of(programList.get(i), possible ? ReservationStatus.POSSIBLE : ReservationStatus.IMPOSSIBLE));
                    i++;
                }
                if(!tmp2.getList().isEmpty()) tmp1.getList().add(tmp2);
            }
            if(!tmp1.getList().isEmpty()) programStatusList.add(tmp1);
        }
        return programStatusList;
    }

    public ProgramResponse.ProgramCarStatusList getCarAndStatusList(LocalDate date, Long programId) {
        Program program = this.programRepository.findById(programId)
                .orElseThrow(() -> new GeneralHandler(ErrorStatus._BAD_REQUEST));
        List<DrivingClass> classList = drivingClassRepository.findByProgramAndStartDateTime(program, date);

        List<CarResponse.CarStatus> carAndStatus = new ArrayList<>();
        Map<Car, Long> availableCars = new HashMap<>();

        if(classList.isEmpty()) return ProgramResponse.ProgramCarStatusList.of(program, date, carAndStatus);

        for(DrivingClass drivingClass : classList) {
            Long numberOfClass = 0L;
            Long maxOccupancyOfProgram = drivingClass.getProgram().getMaximumOccupancy();

            Map<Car, Long> tmp = new HashMap<>();
            for(ClassCar car : drivingClass.getCarList()) {
                Long currentNumber = 0L;
                for(Participation participation : car.getParticipationList()) {
                    currentNumber += participation.getParticipants();
                    numberOfClass += participation.getParticipants();
                }
                if(currentNumber >= car.getMaximumOccupancy()) tmp.put(car.getCar(), 0L);
                else tmp.put(car.getCar(), car.getMaximumOccupancy() - currentNumber);
            }
            if(maxOccupancyOfProgram > numberOfClass) {
                for(Car car : tmp.keySet())
                    availableCars.put(car, availableCars.getOrDefault(car, 0L) + tmp.get(car));
            }
            else {
                for (Car car : tmp.keySet())
                    availableCars.put(car, availableCars.getOrDefault(car, 0L));
            }
        }

        for(Car car : availableCars.keySet()) {
            if(availableCars.get(car) == 0L) carAndStatus.add(CarResponse.CarStatus.of(car, ReservationStatus.IMPOSSIBLE));
            else carAndStatus.add(CarResponse.CarStatus.of(car, ReservationStatus.POSSIBLE));
        }
        return ProgramResponse.ProgramCarStatusList.of(program, date, carAndStatus);
    }
}
