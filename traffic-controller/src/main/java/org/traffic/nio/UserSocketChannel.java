package org.traffic.nio;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
@AllArgsConstructor
@Getter
public class UserSocketChannel{
  private final Logger logger = LoggerFactory.getLogger(UserSocketChannel.class);
  private SocketChannel socketChannel;
  public static UserSocketChannel of(SocketChannel socketChannel){
    return new UserSocketChannel(socketChannel);
  }
  public void bye(ByteBuffer buffer){
    try {
      logger.info("----bye process by");
      logger.info("----buffer remaining : {}",buffer.remaining());
      int key = (int)(Math.random() * 100000)+1000000;
      buffer.putInt(key);
      buffer.flip();
      socketChannel.write(buffer);
      buffer.clear();
      logger.info("----channel close");

      // 문제 상황, socketChannel.write하는 도중 channel의 close가 발생하는 것으로 보임.
      // curl --location 'http://127.0.0.1:9000' command 시 curl: (56) Recv failure: Connection was reset 발생
      /*
      try {
        Thread.sleep(10000);
      }catch (Exception e){
        logger.error("sleep failed");
      }
      */
      socketChannel.close();
      logger.info("----close success");
    }catch (IOException e){
      logger.error("------Error from bye()---------");
    }
  }
  public void renewPriority(ByteBuffer buffer,int sequence){
    if(isClosed())
      return;
    try {
      //buffer.compact();
      logger.info("----UserSocketChannel.java's sequence : {}",sequence);
      logger.info("----Buffer remaining : {}",buffer.remaining());
      buffer.putInt(sequence);
      buffer.flip();
      socketChannel.write(buffer);
      buffer.clear();
    }catch (Exception e){
      logger.error("------Error from UserSocketChannel.java : {}",e.getClass());
    }
  }
  public boolean isClosed(){
    return !socketChannel.isOpen();
  }
}