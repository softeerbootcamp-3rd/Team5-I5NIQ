package com.softeer.BE.unit.service;

import com.softeer.BE.domain.entity.Users;
import com.softeer.BE.repository.UsersRepository;
import com.softeer.BE.service.ReservationService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ReservationServiceTest {

    @Autowired
    private ReservationService reservationService;
    @Autowired
    private UsersRepository usersRepository;

    private static final Logger logger = LoggerFactory.getLogger(ReservationServiceTest.class);

    @Test
    @DisplayName("예약 생성 서비스 테스트 - 특정 ClassCar에 대한 동시성 검증")
    public void testConcurrentReservationForSpecificClassCar() {
        ExecutorService executorService = Executors.newFixedThreadPool(10); // 10개의 스레드를 가진 스레드 풀 생성
        int numberOfRequests = 100; // 테스트 요청 수
        long classCarId = 9L; // 테스트 클래스 차량 id
        long reservationSize = 1L; // 테스트 예약 크기
        Users user1 = usersRepository.findById("userId1").orElseThrow(() -> new RuntimeException("User not found"));// 테스트 사용자 객체

        List<Future<Boolean>> futures = new ArrayList<>();
        for (int i = 0; i < numberOfRequests; i++) {
            Future<Boolean> future = executorService.submit(() ->
                    reservationService.classCarReservation(classCarId, reservationSize, user1)
            );
            futures.add(future);
        }

        long successfulReservations = futures.stream()
                .filter(future -> {
                    try {
                        return Boolean.TRUE.equals(future.get()); // 성공한 예약(결과가 true인 경우)만 필터링
                    } catch (Exception e) {
                        return false;
                    }
                })
                .count();
        assertEquals(1, successfulReservations);
        logger.info("예약 성공 수: " + successfulReservations);

        executorService.shutdown(); // 스레드 풀 종료
    }

    @Test
    @DisplayName("예약 생성 서비스 테스트 - 동일한 DrivingClass를 가지는 ClassCar들에 대한 동시성 검증 ")
    public void testConcurrentReservationForClassCarsInSameDrivingClass() {
        ExecutorService executorService = Executors.newFixedThreadPool(10); // 10개의 스레드를 가진 스레드 풀 생성
        int numberOfRequests = 100; // 테스트 요청 수
        long reservationSize = 1L; // 테스트 예약 크기

        Users user1 = usersRepository.findById("userId1").orElseThrow(() -> new RuntimeException("User not found"));// 테스트 사용자 객체

        List<Future<Boolean>> futures = new ArrayList<>();
        for (int i = 0; i < numberOfRequests; i++) {
            Future<Boolean> future = executorService.submit(() ->
                    // 테스트 클래스 차량 id 12 ~ 15
                    reservationService.classCarReservation((long) (Math.random() * 4 + 12), reservationSize, user1)
            );
            futures.add(future);
        }

        long successfulReservations = futures.stream()
                .filter(future -> {
                    try {
                        return Boolean.TRUE.equals(future.get()); // 성공한 예약(결과가 true인 경우)만 필터링
                    } catch (Exception e) {
                        return false;
                    }
                })
                .count();
        assertTrue(successfulReservations <= 4);
        logger.info("예약 성공 수: " + successfulReservations);

        executorService.shutdown();
    }

    @Test
    @DisplayName("예약 생성 서비스 테스트 - 서로 다른 DrivingClass를 가지는 ClassCar들에 대한 동시성 검증")
    public void testConcurrentReservationForClassCarsInDifferentDrivingClass() {
        ExecutorService executorService = Executors.newFixedThreadPool(10); // 10개의 스레드를 가진 스레드 풀 생성
        long classCarId1 = 7L;
        long classCarId2 = 8L;
        int numberOfRequests = 50; // 테스트 요청 수
        long reservationSize = 1L; // 테스트 예약 크기

        Users user1 = usersRepository.findById("userId1").orElseThrow(() -> new RuntimeException("User not found"));
        Users user2 = usersRepository.findById("userId2").orElseThrow(() -> new RuntimeException("User not found"));

        List<Future<Boolean>> futures = new ArrayList<>();
        for (int i = 0; i < numberOfRequests; i++) {
            Future<Boolean> future = executorService.submit(() ->
                    reservationService.classCarReservation(classCarId1, reservationSize, user1)
            );
            futures.add(future);

            future = executorService.submit(() ->
                    reservationService.classCarReservation(classCarId2, reservationSize, user2)
            );
            futures.add(future);
        }

        long successfulReservations = futures.stream()
                .filter(future -> {
                    try {
                        return Boolean.TRUE.equals(future.get()); // 성공한 예약(결과가 true인 경우)만 필터링
                    } catch (Exception e) {
                        return false;
                    }
                })
                .count();
        assertEquals(3, successfulReservations);
        logger.info("예약 성공 수: " + successfulReservations);
        executorService.shutdown();
    }
}
