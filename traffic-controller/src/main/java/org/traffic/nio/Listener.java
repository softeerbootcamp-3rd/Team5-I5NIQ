package org.traffic.nio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.UUID;

public class Listener {
  private static Logger logger = LoggerFactory.getLogger(Listener.class);
  private final SocketChannelQueue socketChannelQueue;
  //잔여 접속 좌석이 남는 순간에 해당 메서드가 호출되어서 갱신된 순서를 보여주고
  //접속이 가능한 client는 접속할 수 있도록 처리를 해주어야 한다.
  public void accessAllow(int clientNumber){
    logger.info("--------------access allow from Listener------------------");
    logger.info("--access allow for {}",clientNumber);
    ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
    for(int i=0;i<clientNumber;i++){
      if(socketChannelQueue.isEmpty())
        break;
      //server로 넘어갈 수 있는 client는 queue에서 빼내기
      UserSocketChannel clientSocket = socketChannelQueue.front();
      //마지막 작별인사 (server로 넘어갈 수 있는 인증키 전달) 후 socket 연결 종료
      //***연결 종료는 socket으로 인증키를 완전히 write한 이후에 전달 되어야 하므로 call back이 필요함.
      //***따라서 selector를 써야할 것으로 예상됨.
      if(clientSocket.isClosed()) {
        i--;
        continue;
      }
      clientSocket.bye(buffer);
    }
    logger.info("--------------end of access allow------------------");
    //최신값 갱신
    //접속 대기중인 모두에게 최신화된 대기열 공지, 이 부분은 call back이 필요하지 않지만 비동기로 실행되어야 함.
    broadCast(buffer);
  }
  private void broadCast(ByteBuffer buffer){
    //비동기로 socket에 write할 수 있어야 함.
    socketChannelQueue.broadCast(buffer);
  }
  public Listener(SocketChannelQueue socketChannelQueue){
    this.socketChannelQueue=socketChannelQueue;
  }
}
