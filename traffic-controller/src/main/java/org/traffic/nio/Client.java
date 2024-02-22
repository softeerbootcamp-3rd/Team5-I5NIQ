package org.traffic.nio;

import lombok.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class Client {
  private static final Logger logger = LoggerFactory.getLogger(Client.class);
  public ClientMessage requestToServer(){
    String message="empty message";
    int code=404;
    int value=-1;

    int ticket = -1;
    try (Socket socket = new Socket("127.0.0.1", 9000)) {
      logger.info("server opened");
      InputStream input = socket.getInputStream();
      while (true){
        byte[] bytes = new byte[1024];
        int result = input.read(bytes);
        if(result==-1){
          logger.info("server closed");
          break;
        }
        String line = new String(bytes,0,result);
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
    }
    value=ticket;
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
