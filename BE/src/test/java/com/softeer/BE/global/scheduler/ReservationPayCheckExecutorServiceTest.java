package com.softeer.BE.global.scheduler;

import com.softeer.BE.domain.entity.Users;
import com.softeer.BE.repository.UsersRepository;
import com.softeer.BE.service.ReservationService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReservationPayCheckExecutorServiceTest {
  @Autowired
  private ReservationService reservationService;
  @Autowired
  private UsersRepository usersRepository;
  private final Logger logger = LoggerFactory.getLogger(ReservationPayCheckExecutorServiceTest.class);
  @Test
  public void testScheduler() throws InterruptedException {
    Users users = usersRepository.findById("userId1").get();
    reservationService.classCarReservation(11,1,users, "1234");
    logger.info("end of test");
    Thread.sleep(20000);
  }
}