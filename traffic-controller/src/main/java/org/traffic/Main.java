package org.traffic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.traffic.nio.Listener;
import org.traffic.nio.Server;
import org.traffic.nio.SocketChannelQueue;

import java.io.*;
import java.net.*;
import java.util.Map;

public class Main {
  private final static int PORT=8080;
  private static Logger logger = LoggerFactory.getLogger(Main.class);
  public static void main(String[] args) throws InterruptedException {
    SocketChannelQueue socketChannelQueue = new SocketChannelQueue();
    System.out.println("Hello world!");
    Thread thread = new Thread(()->{
      try {
      Server server = new Server(socketChannelQueue);
      server.start();
      }catch (Exception e){
      }
    });
    thread.start();
    Listener listener = new Listener(socketChannelQueue);
    logger.info("listener start");
    for(int i=0;i<100000;i++){
      Thread.sleep(1000);
      //listener.accessAllow(i%10==1?1:0);
      listener.accessAllow(100);
    }
  }
}