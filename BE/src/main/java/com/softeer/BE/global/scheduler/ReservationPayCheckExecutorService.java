package com.softeer.BE.global.scheduler;

import com.softeer.BE.domain.entity.Participation;
import com.softeer.BE.repository.ParticipationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Component
public class ReservationPayCheckExecutorService extends ScheduledThreadPoolExecutor {
  private static final int corePoolSize=10;
  @Value("${pay.timer.duration:10}")
  private int timerDuration;
  private final ParticipationRepository participationRepository;
  private final Logger logger;
  @Autowired
  public ReservationPayCheckExecutorService(ParticipationRepository participationRepository) {
    super(corePoolSize);
    this.participationRepository=participationRepository;
    this.logger= LoggerFactory.getLogger(ReservationPayCheckExecutorService.class);
  }
  public void executeTimer(long participationId){
    super.schedule(() -> payCheck(participationId),
            timerDuration, TimeUnit.SECONDS);
  }
  private void payCheck(long participationId){
    logger.info("pay check timer executed : {}",participationId);
    Participation participation = participationRepository
            .findById(participationId).orElseThrow(()->new RuntimeException("invalid participation id"));
    if(!participation.hasPaid()){
      logger.info("participation is not paid : {}",participation.getId());
      participationRepository.delete(participation);
      logger.info("participation deleted");
    }
  }
}
