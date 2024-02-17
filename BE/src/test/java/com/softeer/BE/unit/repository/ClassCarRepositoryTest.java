package com.softeer.BE.unit.repository;

import com.softeer.BE.domain.entity.ClassCar;
import com.softeer.BE.repository.ClassCarRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ClassCarRepositoryTest {

    @Autowired
    private ClassCarRepository classCarRepository;

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
}
