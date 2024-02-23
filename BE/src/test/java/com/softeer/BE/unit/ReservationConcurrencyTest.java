package com.softeer.BE.unit;

import com.softeer.BE.controller.ReservationController;
import com.softeer.BE.domain.entity.*;
import com.softeer.BE.domain.entity.enums.License;
import com.softeer.BE.domain.entity.enums.Role;
import com.softeer.BE.global.apiPayload.ApiResponse;
import com.softeer.BE.repository.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

@SpringBootTest
public class ReservationConcurrencyTest {

    @Autowired
    ReservationController reservationController;
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    ClassCarRepository classCarRepository;
    @Autowired
    DrivingClassRepository drivingClassRepository;
    @Autowired
    ProgramRepository programRepository;
    @Autowired
    CarRepository carRepository;


    @Test
    @DisplayName("예약 api 테스트")
    void reservationTest() throws Exception {

        int memberCount = 1000;
        long ticketAmount = 100L;

        Program program = programRepository.save(Program.builder().maximumOccupancy(ticketAmount).build());
        DrivingClass drivingClass = drivingClassRepository.save(DrivingClass.builder().program(program).build());
        Car car1 = carRepository.save(new Car());
        Car car2 = carRepository.save(new Car());
        Car car3 = carRepository.save(new Car());
        ClassCar classCar1 = classCarRepository.save(new ClassCar(null, ticketAmount, drivingClass, car1, null, 50000L));
        ClassCar classCar2 = classCarRepository.save(new ClassCar(null, ticketAmount, drivingClass, car2, null, 50000L));
        ClassCar classCar3 = classCarRepository.save(new ClassCar(null, ticketAmount, drivingClass, car3, null, 50000L));

        Users use = new Users("userId", "name", "password", Role.USER, License.CLASS1, null, null);
        usersRepository.save(use);

        ExecutorService executorService = Executors.newFixedThreadPool(100);
        CountDownLatch latch = new CountDownLatch(memberCount);
        List<AtomicInteger> successCount = new ArrayList<>();
        for(int i=0; i<3; i++) successCount.add(new AtomicInteger());
        AtomicInteger failCount = new AtomicInteger();
        AtomicInteger exceptionCount = new AtomicInteger();

        // when
        for (int i = 0; i < memberCount; i++) {
            int finalI = i;
            executorService.submit(() -> {
                try {
                    Random random = new Random(System.currentTimeMillis());
                    long rand = random.nextLong(3);
                    ApiResponse<Boolean> check = reservationController.reservation(rand + 12L, 1L, String.valueOf(UUID.randomUUID()));
                    if(check.getResult()) {
                        successCount.get((int)rand).incrementAndGet();
                    }
                    else {
                        failCount.incrementAndGet();
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    exceptionCount.incrementAndGet();
                } finally {
                    latch.countDown();
                }
            });
        }
        latch.await();
        executorService.shutdown();

        for(int i=0; i<3; i++)
            System.out.println("successCount = " + successCount.get(i));
        System.out.println("failCount = " + failCount);
        System.out.println("exceptionCount = " + exceptionCount);
    }
}
