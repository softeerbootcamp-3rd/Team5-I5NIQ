package com.softeer.BE.unit.repository;

import com.softeer.BE.domain.entity.ClassCar;
import com.softeer.BE.domain.entity.Participation;
import com.softeer.BE.domain.entity.Users;
import com.softeer.BE.global.apiPayload.code.statusEnums.ErrorStatus;
import com.softeer.BE.global.exception.GeneralHandler;
import com.softeer.BE.repository.ClassCarRepository;
import com.softeer.BE.repository.ParticipationRepository;
import com.softeer.BE.repository.UsersRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ClassCarRepositoryTest {

    @Autowired
    private ClassCarRepository classCarRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private ParticipationRepository participationRepository;

    @Autowired
    private PlatformTransactionManager transactionManager;

    private static final Logger logger = LoggerFactory.getLogger(ClassCarRepositoryTest.class);

    @Test
    @DisplayName("ClassCar 비관적 락 동작 확인을 위한 대기 상태 테스트")
    public void testLockWaiting() throws InterruptedException {
        // Given
        Long testClassCarId = 1L;
        ExecutorService executor = Executors.newFixedThreadPool(2);
        CountDownLatch latch = new CountDownLatch(1);

        // When
        executor.submit(() -> {
            // 첫 번째 스레드: 비관적 락을 걸고 일정 시간 대기
            TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
            try {
                ClassCar lockedClassCar = classCarRepository.findByIdForUpdate(testClassCarId).orElseThrow();
                latch.countDown(); // 락 획득 신호
                Thread.sleep(5000); // 락 유지 시간
                logger.info("첫 번째 스레드가 비관적 락을 획득하고 5초간 대기합니다.");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                transactionManager.commit(status); // 락 해제
                logger.info("첫 번째 스레드의 트랜잭션을 커밋하고 락을 해제합니다.");
            }
        });

        executor.submit(() -> {
            // 두 번째 스레드: 첫 번째 스레드의 락 해제를 대기
            try {
                latch.await(); // 첫 번째 스레드의 락 획득 대기
                logger.info("두 번째 스레드가 첫 번째 스레드의 락 해제를 대기합니다.");
                transactionManager.getTransaction(new DefaultTransactionDefinition());
                classCarRepository.findByIdForUpdate(testClassCarId); // 락 대기
                logger.info("두 번째 스레드가 비관적 락을 획득했습니다.");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // Then
        executor.shutdown();
        boolean finished = executor.awaitTermination(10, TimeUnit.SECONDS);
        assertTrue(finished, "락으로 인한 대기 상태 테스트가 시간 내에 완료되지 않았습니다.");
    }

    @Test
    @DisplayName("예약 시나리오1: 동일한 ClassCar에 대한 동시 예약 시도 테스트")
    public void testConcurrentReservationForSameClassCar() throws InterruptedException {
        // Given
        Long testClassCarId = 11L;
        Long reservationSize = 1L;
        Users user1 = usersRepository.findById("userId1").orElseThrow(() -> new GeneralHandler(ErrorStatus.USER_NOT_FOUND));

        ExecutorService executor = Executors.newFixedThreadPool(2);
        CountDownLatch latch = new CountDownLatch(1);
        AtomicBoolean firstReservationResult = new AtomicBoolean(false);
        AtomicBoolean secondReservationResult = new AtomicBoolean(false);

        // When
        executor.submit(() -> {
            // 첫 번째 스레드: 예약 시도
            TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
            try {
                ClassCar classCar = classCarRepository.findByIdForUpdate(testClassCarId).orElseThrow();
                firstReservationResult.set(classCar.canReservation(reservationSize));
                Participation.makeReservation(classCar, user1, reservationSize, participationRepository);
            } finally {
                transactionManager.commit(status);
                logger.info("첫 번째 스레드의 트랜잭션을 커밋하고 락을 해제합니다.");
                latch.countDown(); // 첫 번째 예약 완료 신호
            }
        });

        executor.submit(() -> {
            // 두 번째 스레드: 예약 시도
            try {
                latch.await(); // 첫 번째 스레드의 예약 완료를 대기
                logger.info("두 번째 스레드가 첫 번째 스레드의 락 해제를 대기합니다.");
                transactionManager.getTransaction(new DefaultTransactionDefinition());
                ClassCar classCar = classCarRepository.findByIdForUpdate(testClassCarId).orElseThrow();
                secondReservationResult.set(classCar.canReservation(reservationSize)); // 예약 가능 여부 확인
                logger.info("두 번째 스레드가 비관적 락을 획득했습니다.");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                logger.info("두 번째 스레드의 트랜잭션을 커밋하고 락을 해제합니다.");
            }
        });

        // Then
        executor.shutdown();
        assertTrue(executor.awaitTermination(5, TimeUnit.SECONDS), "테스트가 시간 내에 완료되지 않았습니다.");
        assertTrue(firstReservationResult.get(), "첫 번째 예약이 실패했습니다."); // 첫 번째 예약은 성공해야 함
        assertFalse(secondReservationResult.get(), "두 번째 예약이 성공했습니다."); // 두 번째 예약은 실패해야 함 - 최대인원 충족됨
    }
}
