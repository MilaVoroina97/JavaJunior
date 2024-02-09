package org.example.serverapp;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

/**
 * Class for store all server connections
 */

public class Server {

    private final static int PORT = 5500;
    public static LinkedList<ServerManager> serverList = new LinkedList<>(); //list of all servers threads - instance of
    // server class, which is listening its own client

    public static Story story; //message history


    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        story = new Story();
        try{
            while (true){

                //block until new connection
                Socket socket = serverSocket.accept();
                try {

                    //add new connection in list
                    serverList.add(new ServerManager(socket));
                }catch (Exception e){
                    socket.close();
                }
            }
        }finally {
            serverSocket.close();
        }

    }
}
