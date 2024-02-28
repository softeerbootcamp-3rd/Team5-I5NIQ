package org.traffic.nio;

import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

@NoArgsConstructor
public class SocketChannelQueue {
  private final Logger logger = LoggerFactory.getLogger(SocketChannelQueue.class);
  private final Queue<UserSocketChannel> socketChannels = new LinkedList<>();
  private final AtomicInteger broadcastingSizeIdx = new AtomicInteger(0);
  private final int[] broadCastingSize = {200,400,800,1600,3200,6400,12800,25600,51200};
  public UserSocketChannel addChannel(SocketChannel socketChannel){
    UserSocketChannel channel = UserSocketChannel.of(socketChannel);
    socketChannels.add(channel);
    return channel;
  }
  public boolean isEmpty(){
    return socketChannels.isEmpty();
  }
  public UserSocketChannel front(){
    return socketChannels.poll();
  }
  public int size(){return socketChannels.size();}

  public void broadCast(ByteBuffer buffer){
    int broadcastingSizeLength = broadCastingSize.length;
    int loopCnt = broadCastingSize[broadcastingSizeIdx.incrementAndGet()%broadcastingSizeLength];
    logger.info("--------------broadCast from Listener------------------");
    logger.info("--SocketChannelQueue size : {}",socketChannels.size());
    logger.info("--broadCast size : {}",loopCnt);
    int sequence=0;
    for (UserSocketChannel socketChannel : socketChannels) {
      //비동기로 socket에 write할 수 있어야 함.
      socketChannel.renewPriority(buffer,++sequence);
      if(sequence>=loopCnt)
        break;
    }
    logger.info("--------------end of Listener broadcast------------------");
  }
  public void removeInvalidSocketChannel(UserSocketChannel socketChannel){
    socketChannels.remove(socketChannel);
  }
}
