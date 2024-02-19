package org.traffic.nio;

import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.LinkedList;
import java.util.Queue;

@NoArgsConstructor
public class SocketChannelQueue {
  private final Logger logger = LoggerFactory.getLogger(SocketChannelQueue.class);
  private final Queue<UserSocketChannel> socketChannels = new LinkedList<>();
  public void addChannel(SocketChannel socketChannel,int sequence){
    socketChannels.add(UserSocketChannel.of(socketChannel,sequence));
  }
  public boolean isEmpty(){
    return socketChannels.isEmpty();
  }
  public UserSocketChannel front(){
    return socketChannels.poll();
  }
  public int size(){return socketChannels.size();}

  public void broadCast(ByteBuffer buffer,int firstSequence){
    logger.info("--------------broadCast from Listener------------------");
    logger.info("--broadCast from Listener cnt : {}",socketChannels.size());
    for (UserSocketChannel socketChannel : socketChannels) {
      //비동기로 socket에 write할 수 있어야 함.
      socketChannel.renewPriority(buffer,firstSequence);
    }
    logger.info("--------------end of Listener broadcast------------------");
  }
}
