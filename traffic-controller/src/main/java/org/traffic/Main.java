package org.traffic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.traffic.nio.Listener;
import org.traffic.nio.ListenerSelector;
import org.traffic.nio.Server;
import org.traffic.nio.SocketChannelQueue;

import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.Selector;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
  private static Logger logger = LoggerFactory.getLogger(Main.class);
  public static void main(String[] args) throws InterruptedException, IOException {
    SocketChannelQueue socketChannelQueue = new SocketChannelQueue();
    Server server = new Server(socketChannelQueue);
    System.out.println("Hello world!");
    Thread thread = new Thread(()->{
      try {
      server.start();
      }catch (Exception e){
        e.printStackTrace();
      }
    });
    thread.start();
    Listener listener = new Listener(socketChannelQueue,server);
    logger.info("listener start");
    for(int i=0;i<10000000;i++){
      Thread.sleep(200);
      listener.accessAllow(2);
    }
  }
}