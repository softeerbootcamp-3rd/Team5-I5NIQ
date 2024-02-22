package org.traffic.nio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.traffic.nio.Client.ClientMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class MockClientThread {
  private static final Logger logger = LoggerFactory.getLogger(MockClientThread.class);
  public static void main(String[] args) throws InterruptedException{
    ExecutorService executorService = Executors.newFixedThreadPool(100); // 10개의 스레드를 가진 스레드 풀 생성
    int numberOfRequests = 1000; // 테스트 요청 수

    CountDownLatch latch = new CountDownLatch(numberOfRequests);

    List<Thread> threads = new ArrayList<>();
    for(int i=0;i<numberOfRequests;i++){
      Client mockClient = new Client();
      Thread t = new Thread(()-> {
        ClientMessage result = new ClientMessage(-1,"not started",-1);
        try {
          result = mockClient.requestToServer();
        } finally {
          latch.countDown();
        }
      });
      threads.add(t);
    }
    int cnt=0;
    for(Thread t: threads){
      t.start();
      cnt++;
      logger.info("t : {}",cnt);
    }
    logger.info("cnt : {}",cnt);
    Thread.sleep(10000);
  }
}
