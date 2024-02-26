package org.traffic.nio.test.local;

import lombok.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Client {
  private static ConcurrentLinkedQueue<Integer> brokenPorts = new ConcurrentLinkedQueue<>();
  private static AtomicInteger cliCnt= new AtomicInteger(0);
  private static AtomicInteger closeCnt = new AtomicInteger(0);
  private static AtomicInteger returnCnt = new AtomicInteger(0);
  private static final Logger logger = LoggerFactory.getLogger(Client.class);
  public ClientMessage requestToServer(){
    String message="empty message";
    int code=404;
    int value=-1;
    int backlog=-1;
    int ticket = -1;
    int myPortNumber = -1;
    try (Socket socket = new Socket("127.0.0.1", 9000)) {
      logger.info("server opened {}",cliCnt.incrementAndGet());
      InputStream input = socket.getInputStream();
      myPortNumber = socket.getLocalPort();
      while (true){
        byte[] bytes = new byte[32];
        int result = input.read(bytes);
        if(result==-1){
          //logger.info("server closed {}",closeCnt.incrementAndGet());
          break;
        }
        String line = new String(bytes,0,result);
        line=line.replace("\n","");
        try {
          int number = Integer.parseInt(line);
          if(number>=1000000){
            ticket=number-1000000;
            code=200;
          }else {
            backlog=number;
          }
        }catch (Exception e){
          code=500;
          logger.error("Number format exception : {}",e.getMessage());
        }
      }
      logger.info("local port number : {}",myPortNumber);
    }catch (IOException e){
      code=500;
      message="IOException : "+e.getMessage();
      logger.error("IOException : {}, local port number : {}",e.getMessage(),myPortNumber);
      e.printStackTrace();
      brokenPorts.add(myPortNumber);
    }catch (Exception e){
      logger.error("Un expected exception class name : {}, message : {}, local port number : {}",
              e.getClass().getName(),e.getMessage(),myPortNumber);
    }
    value=ticket;
    logger.info("return cli {}",returnCnt.incrementAndGet());
    if(code==404)
      logger.info("[code 404] local port number : {}",myPortNumber);
    return new ClientMessage(code,message,value,backlog);
  }
  @AllArgsConstructor
  @Getter
  public static class ClientMessage{
    private int code;
    private String message;
    private int value;
    private int backLog;
  }
}
