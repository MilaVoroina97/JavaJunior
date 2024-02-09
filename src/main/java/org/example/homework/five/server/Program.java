package org.example.homework.five.server;

import java.io.IOException;
import java.net.ServerSocket;


public class Program {

    public static void main(String[] args) {
        try{
            ServerSocket socket = new ServerSocket(7800);
            Server server = new Server(socket);
            server.runServer();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
