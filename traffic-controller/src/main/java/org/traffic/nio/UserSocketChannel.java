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
  public void bye(ByteBuffer buffer,ListenerSelector listenerSelector){
    try {
      int key = (int)(Math.random() * 100000)+1000000;
      String keyStr = String.valueOf(key)+"\n";
      /*
      buffer.put(keyStr.getBytes());
      buffer.flip();
       */
      //logger.info("listner's port : {}",socketChannel.socket().getPort());
      listenerSelector.register(socketChannel,keyStr);
      //socketChannel.write(buffer);
      //buffer.clear();
    }catch (Exception e){
      logger.error("------Error from bye()---------");
    }finally {
      buffer.clear();
      try {
        socketChannel.close();
      }catch (IOException exception){
        logger.info("----socket channel close failed : {}",exception.getMessage());
      }
    }
  }
  public void renewPriority(ByteBuffer buffer,int sequence,Server server){
    if(isClosed())
      return;
    try {
      String sequenceStr = String.valueOf(sequence)+"\n";
      /*
      buffer.put(sequenceStr.getBytes());
      buffer.flip();
      socketChannel.write(buffer);
      buffer.clear();
       */
      server.register(socketChannel,sequenceStr);
    }catch (Exception e){
      logger.error("------Error from UserSocketChannel.java : {}",e.getClass());
    }
  }
  public boolean isClosed(){
    return !socketChannel.isOpen();
  }
}