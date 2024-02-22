package org.traffic.nio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class IoClient {
  private static final Logger logger = LoggerFactory.getLogger(IoClient.class);
  public static void main(String[] args) throws InterruptedException{
    int ticket = -1;
    try (Socket socket = new Socket("3.39.238.199", 9000)) {
      logger.info("socket opened : {}",socket.isConnected() ? "true":"false");
      InputStream input = socket.getInputStream();
      //BufferedReader reader = new BufferedReader(new InputStreamReader(input));
      while (true){
        byte[] bytes = new byte[1024];
        int result = input.read(bytes);
        if(result==-1){
          logger.info("server closed");
          break;
        }
        String line = new String(bytes,0,result);
        //String line = reader.readLine();
        logger.info("read value : {}",line);
        //logger.info("socket is open : {}",socket.isClosed());
        try {
          int number = Integer.parseInt(line);
          if(number>1000000){
            ticket=number-1000000;
          }
        }catch (NumberFormatException e){
          logger.error("NumberFormatException : {}",e.getMessage());
        }
      }
    }catch (IOException e){
      logger.error("IOException : {}",e.getMessage());
    }
  }
}
