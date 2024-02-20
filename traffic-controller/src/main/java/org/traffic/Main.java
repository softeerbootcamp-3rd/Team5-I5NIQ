package org.traffic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.traffic.nio.Listener;
import org.traffic.nio.Server;
import org.traffic.nio.SocketChannelQueue;

import java.io.*;
import java.net.*;
import java.util.Map;

public class Main {
  private final static int PORT=8080;
  private static Logger logger = LoggerFactory.getLogger(Main.class);
  public static void main(String[] args) throws InterruptedException {
    SocketChannelQueue socketChannelQueue = new SocketChannelQueue();
    System.out.println("Hello world!");
    Thread thread = new Thread(()->{
      try {
      Server server = new Server(socketChannelQueue);
      server.start();
      }catch (Exception e){
      }
    });
    thread.start();
    Listener listener = new Listener(socketChannelQueue);
    logger.info("listener start");
    for(int i=0;i<100000;i++){
      Thread.sleep(10000);
      listener.accessAllow(i>=5 ? 2 : 0);
    }
  }

  public static void listenServiceServer(SocketChannelQueue socketChannelQueue) throws IOException {
    Listener listener = new Listener(socketChannelQueue);
    int port = 8080;
    URL url = new URL("http://127.0.0.1:8080/i5niq");
    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    connection.setRequestMethod("GET");

    int status = connection.getResponseCode();
    BufferedReader in = new BufferedReader(
            new InputStreamReader(connection.getInputStream()));
    String inputLine;
    StringBuffer content = new StringBuffer();
    while ((inputLine = in.readLine()) != null) {
      content.append(inputLine);
    }
    logger.info("served value : {}",content);
    in.close();
    connection.disconnect();
  }

  private static class ParameterStringBuilder {
    public static String getParamsString(Map<String, String> params)
            throws UnsupportedEncodingException {
      StringBuilder result = new StringBuilder();

      for (Map.Entry<String, String> entry : params.entrySet()) {
        result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
        result.append("=");
        result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        result.append("&");
      }

      String resultString = result.toString();
      return resultString.length() > 0
              ? resultString.substring(0, resultString.length() - 1)
              : resultString;
    }
  }
}