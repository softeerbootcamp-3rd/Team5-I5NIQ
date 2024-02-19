package org.traffic.nio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

public class Client {

  private static final String HOST = "localhost";
  private static final int PORT = 9000;
  private static final Logger log = LoggerFactory.getLogger(Client.class);
  private Selector selector = null;
  private SocketChannel sc = null;
  private final CharsetDecoder decoder = StandardCharsets.UTF_8.newDecoder();

  public void init() {
    try {
      // 셀렉터를 연다
      selector = Selector.open();
      // 소켓채널을 생성한다.
      sc = SocketChannel.open(new InetSocketAddress(HOST, PORT));

      sc.configureBlocking(false);
      sc.register(selector, SelectionKey.OP_READ);
    } catch (IOException e) {
      log.warn("init()", e);
    }
  }

  public void start() {
    startWriter();
    startReader();
  }

  private void startWriter() {
    System.out.println("Writer is started..");
    Thread thread = new ChatThread(sc);
    thread.start();
  }

  private void startReader() {
    System.out.println("Reader is started..");
    try {
      while (true) {
        // 셀렉터의 select() 메서드로 준비된 이벤트가 있는지 확인한다.
        selector.select();

        Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();

        while (iterator.hasNext()) {
          SelectionKey key = iterator.next();
          if (key.isReadable()) {
            read(key);
          }
          iterator.remove();
        }
      }
    } catch (Exception e) {
      log.warn("start()", e);
    }
  }

  private void read(SelectionKey key) {
    SocketChannel sc = (SocketChannel) key.channel();
    ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
    int read = 0;
    try {
      read = sc.read(buffer);
      log.info(read + " byte를 읽었습니다.");
    } catch (IOException e) {
      try {
        sc.close();
      } catch (IOException ex) {
      }
    }

    buffer.flip();

    //String data = "";
    int data = buffer.getInt();
    log.info("Message : " + data);
    /*
    try {
      data = decoder.decode(buffer).toString();
    } catch (CharacterCodingException e) {
      log.warn("read()", e);
    }
     */
    clearBuffer(buffer);
  }

  private void clearBuffer(ByteBuffer buffer) {
    if (buffer != null) {
      buffer.clear();
      buffer = null;
    }
  }

  public static void main(String[] args) {
    Client client = new Client();
    client.init();
    client.start();
  }

}
