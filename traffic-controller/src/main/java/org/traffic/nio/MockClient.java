package org.traffic.nio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.traffic.nio.Client.ClientMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class MockClient {
  private static final Logger logger = LoggerFactory.getLogger(MockClient.class);
  public static void main(String[] args) throws InterruptedException{
    ExecutorService executorService = Executors.newFixedThreadPool(100); // 10개의 스레드를 가진 스레드 풀 생성
    int numberOfRequests = 1000; // 테스트 요청 수

    CountDownLatch latch = new CountDownLatch(numberOfRequests);

    List<Future<ClientMessage>> futures = new ArrayList<>();
    for (int i = 0; i < numberOfRequests; i++) {
      Client mockClient = new Client();
      Future<ClientMessage> future = executorService.submit(()-> {
        ClientMessage result = new ClientMessage(-1,"not started",-1);
        try {
          result = mockClient.requestToServer();
        } finally {
          latch.countDown();
        }
        return result;
      });
      futures.add(future);
    }
    //latch.await();
    executorService.shutdown();
    while(!executorService.isTerminated()){
      Thread.sleep(100);
      logger.info("...................");
    }
    logger.info("executor Service terminated");
    long successfulRequest = futures.stream().filter(MockClient::countForSuccessRequest).count();
    List<Integer> tickets = futures.stream().map(MockClient::getTicket).collect(Collectors.toList());

    logger.info("-----------------test 종료-----------------");
    logger.info("티켓 발급 성공 수: " + successfulRequest);

    executorService.shutdown(); // 스레드 풀 종료
  }
  private static int getTicket(Future<ClientMessage> message){
    int value=-1;
    try {
      value = message.get().getValue();
    }catch (InterruptedException | ExecutionException e){
      value=-10;
    }
    return value;
  }

  private static boolean countForSuccessRequest(Future<ClientMessage> message){
    try {
      return message.get().getCode()==200; // 성공한 예약(결과가 true인 경우)만 필터링
    } catch (Exception e) {
      return false;
    }
  }
}
