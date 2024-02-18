package org.traffic;

import org.traffic.nio.Server;

import java.io.IOException;

public class Main {
  private final static int PORT=8080;
  public static void main(String[] args) throws IOException {
    System.out.println("Hello world!");
    Server server = new Server();
    server.start();
  }
}