package org.traffic.nio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Vector;

public class Server {
  private static Logger logger = LoggerFactory.getLogger(Server.class);
  private final int PORT = 8080;
  private Selector selector;
  private ServerSocketChannel serverSocketChannel;
  private ServerSocket socket;
  private SocketAddress address;
  private Vector<SocketChannel> room = new Vector();
  public Server() throws IOException {
    this.selector = Selector.open();
    this.serverSocketChannel = ServerSocketChannel.open();
    serverSocketChannel.configureBlocking(false); //non blocking 모드로 실행

    this.socket = serverSocketChannel.socket();
    this.address = new InetSocketAddress(PORT);
    socket.bind(address);
    // ServerSocketChannel 과 Accept 이벤트 등록
    serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
  }

  public void start(){
    logger.info("Server is started at {}",System.currentTimeMillis());
    int selectorCallCount=0;
    try {
      while (this.serverSocketChannel.isOpen()){
        //selector 테스트 위한 스레드 지연 로직
        for(int i=0;i<10;i++){
          Thread.sleep(1000);
          logger.info("main server sleep for {}s....",i+1);
        }
        logger.info("요청을 기다리는 중.....[{}th]",++selectorCallCount);
        //selector의 select() 메서드로 준비된 이벤트가 존재하는지 확인
        selector.select();
        //준비된 이벤트들을 하나씩 처리한다.
        Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
        int iteratorCount=0;
        while(iterator.hasNext()){
          iteratorCount+=1;
          SelectionKey key = iterator.next();
          iterator.remove();

          if(key.isAcceptable())
            accept(key);
          if(key.isReadable())
            read(key);
        }
        logger.info("총 selector 개수 : {}",iteratorCount);
      }
    }catch (Exception e){

    }
  }

  private void accept(SelectionKey key) {
    ServerSocketChannel server = (ServerSocketChannel) key.channel();
    SocketChannel sc;
    try {
      // 서버소켓채널의 accept() 메서드로 서버소켓을 생성한다.
      sc = server.accept();

      registerChannel(selector, sc, SelectionKey.OP_READ);
      logger.info( "{} 클라이언트가 접속했습니다.",sc.toString());

    } catch (IOException e) {
      logger.warn("Server.accept()", e);
    }
  }

  private void registerChannel(Selector selector, SocketChannel sc, int opCode)
          throws IOException {
    if (sc == null) {
      logger.info("Invalid Connection");
      return;
    }
    sc.configureBlocking(false);
    sc.register(selector, opCode);
    addUser(sc);
  }

  private void read(SelectionKey key) {
    // SelectionKey로부터 소켓채널을 얻어온다.
    SocketChannel sc = (SocketChannel) key.channel();
    // ByteBuffer를 생성한다.
    ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
    try {
      int read = sc.read(buffer);
      if(read == -1) {
        sc.socket().close();
        sc.close();
        removeUser(sc);
        logger.info("{} 클라이언트가 접속을 해제하였습니다.",sc.toString());
      }
    } catch (IOException e) {
      try {
        sc.close();
      } catch (IOException ex) {
      }
      removeUser(sc);
      logger.info("{} 클라이언트가 접속을 해제하였습니다.",sc.toString());
    }
    try {
      broadcast(buffer);
    } catch (IOException e) {
      logger.warn("Server.broadcast()", e);
    }

    clearBuffer(buffer);
  }

  private void broadcast(ByteBuffer buffer) throws IOException {
    buffer.flip();
    for (SocketChannel sc : room) {
      if (sc != null) {
        sc.write(buffer);
        buffer.rewind();
      }
    }
  }

  private void clearBuffer(ByteBuffer buffer) {
    if (buffer != null) {
      buffer.clear();
      buffer = null;
    }
  }

  private void addUser(SocketChannel sc) {
    room.add(sc);
  }

  private void removeUser(SocketChannel sc) {
    room.remove(sc);
  }
}
