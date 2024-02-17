package com.softeer.BE.service;

import com.softeer.BE.domain.entity.Users;
import com.softeer.BE.repository.UsersRepository;
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

    @Test
    @DisplayName("예약 생성 서비스 테스트 V1 - 동시성 검증")
    public void testConcurrentReservationsWithExecutorService() {
        ExecutorService executorService = Executors.newFixedThreadPool(10); // 10개의 스레드를 가진 스레드 풀 생성
        int numberOfRequests = 10;
        long classCarId = 9L; // 예시 클래스 차량 ID
        long reservationSize = 1L; // 예시 예약 크기
        Users user1 = usersRepository.findById("userId1").orElseThrow(() -> new RuntimeException("User not found"));// 예시 사용자 객체

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
        assertEquals(1, successfulReservations); // 단 하나의 예약만 성공했는지 검증

        executorService.shutdown(); // 스레드 풀 종료
    }
}
