package org.traffic.nio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.UUID;

public class Listener {
  private static Logger logger = LoggerFactory.getLogger(Listener.class);
  private static int firstSequence=0;
  private Queue<ClientInfo> listenerQueue = new LinkedList<>();
  //잔여 접속 좌석이 남는 순간에 해당 메서드가 호출되어서 갱신된 순서를 보여주고
  //접속이 가능한 client는 접속할 수 있도록 처리를 해주어야 한다.
  public void accessAllow(int clientNumber){
    for(int i=0;i<clientNumber;i++){
      if(listenerQueue.isEmpty())
        break;
      //server로 넘어갈 수 있는 client는 queue에서 빼내기
      ClientInfo clientInfo = listenerQueue.poll();
      //마지막 작별인사 (server로 넘어갈 수 있는 인증키 전달) 후 socket 연결 종료
      //***연결 종료는 socket으로 인증키를 완전히 write한 이후에 전달 되어야 하므로 call back이 필요함.
      //***따라서 selector를 써야할 것으로 예상됨.
      clientInfo.bye();
    }
    //최신값 갱신
    firstSequence+=clientNumber;
    //접속 대기중인 모두에게 최신화된 대기열 공지, 이 부분은 call back이 필요하지 않지만 비동기로 실행되어야 함.
    broadCast();
  }
  private void broadCast(){
    for (ClientInfo clientInfo : listenerQueue) {
      //비동기로 socket에 write할 수 있어야 함.
      clientInfo.printNowSequence();
    }
  }

  private static class ClientInfo{
    //client를 식별할 수 있는 고유 정보
    private String clientNumber;
    //client의 접속 sequence
    private int sequence;
    public void printNowSequence(){
      //현재 대기 순서 socket에 write해서 전송
      logger.info("client[{}]'s sequence : {}",clientNumber,sequence-firstSequence);
    }
    public void bye(){
      UUID key = UUID.randomUUID();
      logger.info("bye client[{}], your sequence is {}",clientNumber,key);
    }
  }
}
