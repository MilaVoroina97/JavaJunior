package org.example.seminarFive.server;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private ServerSocket serverSocket;

    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void runServer(){

        try {
            // server is working while server socket will not be closed
            while (!serverSocket.isClosed()){

                Socket socket = serverSocket.accept();
                System.out.println("A new client has just connected to the line");

                //create client object to work with client, it will execute as s separate thread
                ClientManager clientManager = new ClientManager(socket);

                //create the thread
                Thread thread = new Thread(clientManager);
                thread.start();
            }
        } catch (IOException e) {
            closeSocket();
        }

    }

    public void closeSocket(){

        try{
            if(serverSocket != null) serverSocket.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(1300);
        Server server = new Server(socket);
        server.runServer();
    }
}
