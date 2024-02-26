package org.traffic.nio.test.local;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.traffic.nio.test.local.Client.ClientMessage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

public class MockClientThreadPool {
  private static final Logger logger = LoggerFactory.getLogger(MockClientThreadPool.class);
  public static void main(String[] args) throws InterruptedException{
    ExecutorService executorService = Executors.newFixedThreadPool(1000); // 1000개의 스레드를 가진 스레드 풀 생성
    int numberOfRequests = 2000; // 테스트 요청 수, 1500

    CountDownLatch latch = new CountDownLatch(numberOfRequests);

    List<Future<ClientMessage>> futures = new ArrayList<>();
    for (int i = 0; i < numberOfRequests; i++) {
      Client mockClient = new Client();
      Future<ClientMessage> future = executorService.submit(()-> {
        ClientMessage result = new ClientMessage(-1,"not started",-1,-1);
        try {
          result = mockClient.requestToServer();
        } finally {
          latch.countDown();
        }
        return result;
      });
      futures.add(future);
    }
    latch.await();
    executorService.shutdown();
    boolean success = executorService.awaitTermination(1,TimeUnit.MINUTES);
    logger.info("executor Service terminated : {}",success);
    long successfulRequest = futures.stream().filter(MockClientThreadPool::countForSuccessRequest).count();

    logger.info("-----------------test 종료-----------------");
    logger.info("티켓 발급 성공 수: " + successfulRequest);
    for(Map.Entry<Integer,Integer> entry : codeCount.entrySet())
      logger.info("code [{}] : {}",entry.getKey(),entry.getValue());
  }
  private static HashMap<Integer,Integer> codeCount = new HashMap<>();
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
      int code = message.get().getCode();
      codeCount.merge(code, 1, Integer::sum);
      return message.get().getCode()==200; // 성공한 예약(결과가 true인 경우)만 필터링
    } catch (Exception e) {
      return false;
    }
  }
}
