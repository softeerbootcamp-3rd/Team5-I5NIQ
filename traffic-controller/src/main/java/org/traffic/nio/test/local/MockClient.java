package org.traffic.nio.test.local;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.traffic.nio.test.local.Client.ClientMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class MockClient {
  private static final Logger logger = LoggerFactory.getLogger(MockClient.class);
  public static void main(String[] args) throws InterruptedException{
    ExecutorService executorService = Executors.newFixedThreadPool(1); // 10개의 스레드를 가진 스레드 풀 생성
    int numberOfRequests = 1; // 테스트 요청 수

    CountDownLatch latch = new CountDownLatch(numberOfRequests);

    List<Thread> threads = new ArrayList<>();
    for(int i=0;i<numberOfRequests;i++){
      Client mockClient = new Client();
      Thread t = new Thread(mockClient::requestToServer);
      threads.add(t);
    }
    int cnt=0;
    for(Thread t: threads){
      t.start();
      cnt++;
    }
    logger.info("cnt : {}",cnt);
  }
}
