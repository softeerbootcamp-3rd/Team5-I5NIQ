package org.traffic.nio.test.local;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;

@AllArgsConstructor
public class MockClientEventLoop {
  private final static Logger logger = LoggerFactory.getLogger(MockClientEventLoop.class);
  private final static CharsetDecoder decoder = StandardCharsets.UTF_8.newDecoder();
  private Selector clientSelector;
  private int clientCount=0;
  private ByteBuffer buffer;
  private AtomicInteger virtualThread;

  public static void main(String[] args) {
    try(Selector selector = Selector.open()){
      MockClientEventLoop client = new MockClientEventLoop(selector,0,
              ByteBuffer.allocateDirect(1024),new AtomicInteger(0));
      client.start(10);
    }catch (IOException exception){
      logger.error("IOException from main : {}", exception.getMessage());
      exception.printStackTrace();
    }
  }

  public void start(int requestNumber){
    try {
      for(int i=0;i<requestNumber;i++){
        SocketAddress address = new InetSocketAddress("127.0.0.1", 9000);
        try{
          SocketChannel socketChannel = SocketChannel.open(address);
          socketChannel.configureBlocking(false);
          socketChannel.register(clientSelector,SelectionKey.OP_READ,virtualThread.incrementAndGet());
          clientCount+=1;
        }catch (Exception e){
          logger.info("error : {}",e.getMessage());
          e.printStackTrace();
        }
      }
      logger.info("total client : {}",clientCount);
      while (clientSelector.isOpen()){
        logger.info("waiting for select");
        //selector의 select() 메서드로 준비된 이벤트가 존재하는지 확인
        clientSelector.select();
        //준비된 이벤트들을 하나씩 처리한다.
        Iterator<SelectionKey> iterator = clientSelector.selectedKeys().iterator();
        while(iterator.hasNext()){
          SelectionKey key = iterator.next();
          iterator.remove();
          if(key.isReadable())
            readFromServer(key);
        }
      }
    }catch (Exception e){
      logger.error("Server Error : {}",e.getMessage());
      e.printStackTrace();
    }
  }
  private void readFromServer(SelectionKey key){
    SocketChannel channel = (SocketChannel) key.channel();
    int virtualThreadNumber = (int) key.attachment();
    try {
      int res = channel.read(buffer);
      if(res==-1){
        channel.close();
        logger.info("[Thread {}] : 클라이언트가 접속을 해제했습니다.",virtualThreadNumber);
        buffer.clear();
        return;
      }
      String readValue = decoder.decode(buffer).toString();
      readValue=readValue.replace("\n","");
      logger.info("[Thread {}] [port : {}] : {}",virtualThreadNumber,channel.socket().getLocalPort(),readValue);
    }catch (IOException e){
      logger.error("[Thread {}] : {}",virtualThreadNumber,e.getMessage());
      e.printStackTrace();
    }finally {
      buffer.clear();
      try {
        channel.close();
      }catch (IOException e){
        logger.error("[Thread {}] : channel close failed, {}",virtualThread,e.getMessage());
      }
    }
  }
}
