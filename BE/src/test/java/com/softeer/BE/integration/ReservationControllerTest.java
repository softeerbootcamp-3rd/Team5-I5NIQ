package com.softeer.BE.integration;

import com.softeer.BE.controller.ReservationController;
import com.softeer.BE.domain.entity.Users;
import com.softeer.BE.domain.entity.enums.License;
import com.softeer.BE.domain.entity.enums.Role;
import com.softeer.BE.global.apiPayload.ApiResponse;
import com.softeer.BE.repository.UsersRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

@SpringBootTest
public class ReservationControllerTest {

    @Autowired
    ReservationController reservationController;
    @Autowired
    UsersRepository usersRepository;

    @Test
    @DisplayName("예약 api 테스트")
    void reservationTest() throws Exception {

        Long classCar = 12L;
        Long reservationSize = 1L;
        int memberCount = 1000;
        int ticketAmount = 100;

        Users use = new Users("userId", "name", "password", Role.USER, License.CLASS1, null, null);
        usersRepository.save(use);

        ExecutorService executorService = Executors.newFixedThreadPool(100);
        CountDownLatch latch = new CountDownLatch(memberCount);
        AtomicInteger successCount = new AtomicInteger();
        AtomicInteger failCount = new AtomicInteger();
        AtomicInteger exceptionCount = new AtomicInteger();


        // when
        for (int i = 0; i < memberCount; i++) {
            executorService.submit(() -> {
                try {
                    ApiResponse<Boolean> check = reservationController.reservation(12, 1);
                    if(check.getResult())
                        successCount.incrementAndGet(); // 성공한 경우에만 successCount 증가
                    else
                        failCount.incrementAndGet(); // 실패한 경우 failCount 증가
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    exceptionCount.incrementAndGet(); // 예외가 발생하면 exceptionCount 증가
                } finally {
                    latch.countDown();
                }
            });
        }
        latch.await();
        executorService.shutdown();

        System.out.println("successCount = " + successCount);
        System.out.println("failCount = " + failCount);
        System.out.println("exceptionCount = " + exceptionCount);
    }
}
