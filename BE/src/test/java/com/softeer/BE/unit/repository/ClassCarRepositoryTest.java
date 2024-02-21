package com.softeer.BE.unit.repository;

import com.softeer.BE.domain.entity.ClassCar;
import com.softeer.BE.domain.entity.DrivingClass;
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

        // When
        executor.submit(() -> {
            // 첫 번째 스레드: 비관적 락을 걸고 일정 시간 대기
            logger.info("첫 번째 스레드가 트랜잭션을 시작합니다.");
            TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
            try {
                logger.info("첫 번째 스레드가 비관적 락을 획득하기 위해 대기합니다.");
                classCarRepository.lockClassCarsRelatedByDrivingClass(testClassCarId); // 락 획득
                classCarRepository.findById(testClassCarId).orElseThrow();
                logger.info("첫 번째 스레드가 비관적 락을 획득하고 2초간 대기합니다.");
                Thread.sleep(2000); // 락 유지 시간
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                transactionManager.commit(status); // 락 해제
                logger.info("첫 번째 스레드의 트랜잭션을 커밋하고 락을 해제합니다.");
            }
        });

        executor.submit(() -> {
            // 두 번째 스레드: 비관적 락을 걸고 일정 시간 대기
            logger.info("두 번째 스레드가 트랜잭션을 시작합니다.");
            TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
            try {
                logger.info("두 번째 스레드가 비관적 락을 획득하기 위해 대기합니다.");
                classCarRepository.lockClassCarsRelatedByDrivingClass(testClassCarId); // 락 획득
                classCarRepository.findById(testClassCarId).orElseThrow();
                logger.info("두 번째 스레드가 비관적 락을 획득하고 2초간 대기합니다.");
                Thread.sleep(2000); // 락 유지 시간
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                transactionManager.commit(status); // 락 해제
                logger.info("두 번째 스레드의 트랜잭션을 커밋하고 락을 해제합니다.");
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
        AtomicBoolean firstReservationResult = new AtomicBoolean(false);
        AtomicBoolean secondReservationResult = new AtomicBoolean(false);

        // When
        executor.submit(() -> {
            // 첫 번째 스레드: 예약 시도
            TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
            logger.info("첫 번째 스레드의 트랜잭션을 시작합니다.");
            try {
                ClassCar classCar = classCarRepository.findByIdForUpdate(testClassCarId).orElseThrow();
                firstReservationResult.set(classCar.canReservation(reservationSize));
                Participation.makeReservation(classCar, user1, reservationSize, participationRepository);
            } finally {
                transactionManager.commit(status);
                logger.info("첫 번째 스레드의 트랜잭션을 커밋하고 락을 해제합니다.");
            }
        });

        executor.submit(() -> {
            // 두 번째 스레드: 예약 시도
            try {
                logger.info("두 번째 스레드가 첫 번째 스레드의 락 해제를 대기합니다.");
                transactionManager.getTransaction(new DefaultTransactionDefinition());
                ClassCar classCar = classCarRepository.findByIdForUpdate(testClassCarId).orElseThrow();
                logger.info("두 번째 스레드의 트랜잭션을 시작합니다.");
                secondReservationResult.set(classCar.canReservation(reservationSize)); // 예약 가능 여부 확인
                logger.info("두 번째 스레드가 비관적 락을 획득했습니다.");
            } finally {
                logger.info("두 번째 스레드의 트랜잭션을 커밋하고 락을 해제합니다.");
            }
        });

        // Then
        executor.shutdown();
        assertTrue(executor.awaitTermination(5, TimeUnit.SECONDS), "테스트가 시간 내에 완료되지 않았습니다.");

        // 첫 번째 또는 두 번째 예약 중 하나만 성공해야 함을 확인
        boolean onlyOneSuccess = firstReservationResult.get() ^ secondReservationResult.get(); // XOR 연산 사용
        assertTrue(onlyOneSuccess, "정확히 하나의 예약만 성공해야 합니다.");

        // 첫 번째 예약과 두 번재 예약 중, 어느 하나가 true인지 확인
        if (firstReservationResult.get()) {
            logger.info("첫 번째 예약이 성공했습니다.");
        } else {
            logger.info("두 번째 예약이 성공했습니다.");
        }
    }

    @Test
    @DisplayName("예약 시나리오2: 서로 다른 ClassCar 인스턴스에 대한 동시 예약 시도 테스트")
    public void testConcurrentReservationForDifferentClassCars() throws InterruptedException {
        // Given
        Long classCarId1 = 11L; // 테스트를 위한 첫 번째 ClassCar ID
        Long classCarId2 = 10L; // 테스트를 위한 두 번째 ClassCar ID
        Long reservationSize = 1L;
        Users user1 = usersRepository.findById("userId1").orElseThrow(() -> new GeneralHandler(ErrorStatus.USER_NOT_FOUND));
        Users user2 = usersRepository.findById("userId2").orElseThrow(() -> new GeneralHandler(ErrorStatus.USER_NOT_FOUND));

        ExecutorService executor = Executors.newFixedThreadPool(2);
        AtomicBoolean firstReservationResult = new AtomicBoolean(false);
        AtomicBoolean secondReservationResult = new AtomicBoolean(false);

        // When
        executor.submit(() -> {
            // 첫 번째 스레드: 첫 번째 ClassCar에 대한 예약 시도
            TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
            logger.info("첫 번째 스레드의 트랜잭션을 시작합니다.");
            try {
                ClassCar classCar1 = classCarRepository.findByIdForUpdate(classCarId1).orElseThrow();
                firstReservationResult.set(classCar1.canReservation(reservationSize));
                if (firstReservationResult.get()) {
                    Participation.makeReservation(classCar1, user1, reservationSize, participationRepository);
                }
            } finally {
                transactionManager.commit(status);
                logger.info("첫 번째 스레드의 트랜잭션을 커밋하고 락을 해제합니다.");
            }
        });

        executor.submit(() -> {
            // 두 번째 스레드: 두 번째 ClassCar에 대한 예약 시도
            TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
            logger.info("두 번째 스레드의 트랜잭션을 시작합니다.");
            try {
                ClassCar classCar2 = classCarRepository.findByIdForUpdate(classCarId2).orElseThrow();
                secondReservationResult.set(classCar2.canReservation(reservationSize));
                if (secondReservationResult.get()) {
                    Participation.makeReservation(classCar2, user2, reservationSize, participationRepository);
                }
            } finally {
                transactionManager.commit(status);
                logger.info("두 번째 스레드의 트랜잭션을 커밋하고 락을 해제합니다.");
            }
        });

        // Then
        executor.shutdown();
        assertTrue(executor.awaitTermination(5, TimeUnit.SECONDS), "테스트가 시간 내에 완료되지 않았습니다.");
        assertTrue(firstReservationResult.get(), "첫 번째 ClassCar에 대한 예약이 실패했습니다."); // 첫 번째 예약은 성공해야 함
        assertTrue(secondReservationResult.get(), "두 번째 ClassCar에 대한 예약이 실패했습니다."); // 두 번째 예약도 성공해야 함
    }

    @Test
    @DisplayName("예약 시나리오3: 동일한 DrivingClass에 속하는 ClassCar 인스턴스에 대한 동시 예약 시도 테스트")
    public void testConcurrentReservationForClassCarsInSameDrivingClass() throws InterruptedException {
        // Given
        Long classCarId1 = 12L; // 테스트를 위한 첫 번째 ClassCar ID
        Long classCarId2 = 13L; // 테스트를 위한 두 번째 ClassCar ID
        Long reservationSize = 3L;
        Users user1 = usersRepository.findById("userId1").orElseThrow(() -> new GeneralHandler(ErrorStatus.USER_NOT_FOUND));
        Users user2 = usersRepository.findById("userId2").orElseThrow(() -> new GeneralHandler(ErrorStatus.USER_NOT_FOUND));

        ExecutorService executor = Executors.newFixedThreadPool(2);
        AtomicBoolean firstReservationResult = new AtomicBoolean(false);
        AtomicBoolean secondReservationResult = new AtomicBoolean(false);

        // When
        executor.submit(() -> {
            // 첫 번째 스레드: 첫 번째 ClassCar에 대한 예약 시도
            TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
            logger.info("첫 번째 스레드의 트랜잭션을 시작합니다.");
            try {
                ClassCar classCar1 = classCarRepository.findByIdForUpdate(classCarId1).orElseThrow();
                firstReservationResult.set(classCar1.canReservation(reservationSize));
                if (firstReservationResult.get()) {
                    Participation.makeReservation(classCar1, user1, reservationSize, participationRepository);
                }
            } finally {
                transactionManager.commit(status);
                logger.info("첫 번째 스레드의 트랜잭션을 커밋하고 락을 해제합니다.");
            }
        });

        executor.submit(() -> {
            // 두 번째 스레드: 두 번째 ClassCar에 대한 예약 시도
            TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
            logger.info("두 번째 스레드의 트랜잭션을 시작합니다.");
            try {
                ClassCar classCar2 = classCarRepository.findByIdForUpdate(classCarId2).orElseThrow();
                secondReservationResult.set(classCar2.canReservation(reservationSize));
                if (secondReservationResult.get()) {
                    Participation.makeReservation(classCar2, user2, reservationSize, participationRepository);
                }
            } finally {
                transactionManager.commit(status);
                logger.info("두 번째 스레드의 트랜잭션을 커밋하고 락을 해제합니다.");
            }
        });

        // Then
        executor.shutdown();
        assertTrue(executor.awaitTermination(5, TimeUnit.SECONDS), "테스트가 시간 내에 완료되지 않았습니다.");
        assertTrue(firstReservationResult.get(), "첫 번째 ClassCar에 대한 예약이 실패했습니다."); // 첫 번째 예약은 성공해야 함
        assertFalse(secondReservationResult.get(), "두 번째 ClassCar에 대한 예약이 실패했습니다."); // 두 번째 예약은 실패해야 함
    }
}
