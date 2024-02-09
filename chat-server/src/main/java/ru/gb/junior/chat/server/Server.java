package ru.gb.junior.chat.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private final ServerSocket serverSocket;


    public Server(ServerSocket socket) {
        this.serverSocket = socket;
    }


    public void runServer(){

        try {
            while (!serverSocket.isClosed()){

                //make the main thread waiting for client connection
                Socket socket = serverSocket.accept();
                System.out.println("New client has just joined the chat");
                ClientManager clientManager = new ClientManager(socket);
                Thread thread = new Thread(clientManager);
                thread.start();
            }
        } catch (IOException e) {
            closeSocket();
        }

    }

    private void closeSocket(){
        try {

            if(serverSocket != null) serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
