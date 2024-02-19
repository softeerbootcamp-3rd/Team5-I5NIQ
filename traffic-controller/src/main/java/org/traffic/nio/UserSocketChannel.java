package org.traffic.nio;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
@AllArgsConstructor
@Getter
public class UserSocketChannel{
  private SocketChannel socketChannel;
  private int sequence;
  public static UserSocketChannel of(SocketChannel socketChannel,int sequence){
    return new UserSocketChannel(socketChannel,sequence);
  }
  public void bye(ByteBuffer buffer){
    try {
      int key = (int)(Math.random() * 100);
      buffer.putInt(key);
      socketChannel.write(buffer);
      socketChannel.close();
    }catch (Exception e){
    }
  }
  public void renewPriority(ByteBuffer buffer,int firstSequence){
    try {
      buffer.compact();
      buffer.putInt(sequence-firstSequence);
      buffer.flip();
      socketChannel.write(buffer);
    }catch (Exception e){
    }
  }
}