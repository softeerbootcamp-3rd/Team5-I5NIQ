package org.traffic.nio;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
@AllArgsConstructor
@Getter
public class UserSocketChannel{
  private final Logger logger = LoggerFactory.getLogger(UserSocketChannel.class);
  private SocketChannel socketChannel;
  private int sequence;
  public static UserSocketChannel of(SocketChannel socketChannel,int sequence){
    return new UserSocketChannel(socketChannel,sequence);
  }
  public void bye(ByteBuffer buffer){
    try {
      int key = (int)(Math.random() * 100)+50000;
      buffer.putInt(key);
      buffer.flip();
      socketChannel.write(buffer);
      buffer.clear();
      socketChannel.close();
    }catch (Exception e){
    }
  }
  public void renewPriority(ByteBuffer buffer,int firstSequence){
    try {
      //buffer.compact();
      logger.info("----UserSocketChannel.java's sequence : {}",sequence-firstSequence);
      logger.info("----Buffer remaining : {}",buffer.remaining());
      buffer.putInt(sequence-firstSequence);
      buffer.flip();
      socketChannel.write(buffer);
      buffer.clear();
    }catch (Exception e){
      logger.error("------Error from UserSocketChannel.java : {}",e.getClass());
    }
  }
}