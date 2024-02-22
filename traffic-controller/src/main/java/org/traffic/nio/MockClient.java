package org.traffic.nio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.traffic.nio.Client.ClientMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public class MockClient {
  private static final Logger logger = LoggerFactory.getLogger(MockClient.class);
  public static void main(String[] args){
    ExecutorService executorService = Executors.newFixedThreadPool(100); // 10개의 스레드를 가진 스레드 풀 생성
    int numberOfRequests = 1000; // 테스트 요청 수

    List<Future<ClientMessage>> futures = new ArrayList<>();
    for (int i = 0; i < numberOfRequests; i++) {
      Client mockClient = new Client();
      Future<ClientMessage> future = executorService.submit(mockClient::requestToServer);
      futures.add(future);
    }
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
