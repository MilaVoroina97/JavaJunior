package ru.gb.junior.chat.server;

import java.io.IOException;
import java.net.ServerSocket;

public class Program {

    public static void main(String[] args) {

        try {
            System.out.println("Server...");
            ServerSocket serverSocket = new ServerSocket(4500);
            Server server = new Server(serverSocket);
            server.runServer();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
