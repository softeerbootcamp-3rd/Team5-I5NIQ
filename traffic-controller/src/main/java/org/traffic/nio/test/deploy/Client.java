package org.traffic.nio.test.deploy;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicInteger;

public class Client {
  private static AtomicInteger returnCnt = new AtomicInteger(0);
  private static final Logger logger = LoggerFactory.getLogger(Client.class);
  public ClientMessage requestToServer()throws InterruptedException{
    String message="empty message";
    int code=404;
    int value=-1;
    int ticket = -1;
    try (Socket socket = new Socket("3.39.238.199", 9000)) {
      InputStream input = socket.getInputStream();
      while (true){
        byte[] bytes = new byte[32];
        int result = input.read(bytes);
        if(result==-1){
          break;
        }
        String line = new String(bytes,0,result);
        line=line.replace("\n","");
        try {
          int number = Integer.parseInt(line);
          if(number>1000000){
            ticket=number-1000000;
            code=200;
          }
        }catch (NumberFormatException e){
          code=500;
          message="NumberFormatException : "+e.getMessage();
        }
      }
    }catch (IOException e){
      code=500;
      message="IOException : "+e.getMessage();
      logger.error("IOException : {}",e.getMessage());
      e.printStackTrace();
    }
    value=ticket;
    logger.info("return cli {}",returnCnt.incrementAndGet());
    return new ClientMessage(code,message,value);
  }
  @AllArgsConstructor
  @Getter
  public static class ClientMessage{
    private int code;
    private String message;
    private int value;
  }
}
