package org.example.serverapp;


import java.io.*;
import java.net.Socket;

/**
 * Class - wrapper for a server threads to make a list of server connections and wait for next connection
 */

public class ServerManager extends Thread{

    private Socket socket; //socket to connect server with client
    private BufferedReader reader; //stream for reading from socket
    private BufferedWriter writer; // stream for writing in socket


    public ServerManager(Socket socket){

        this.socket = socket;
        try{
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            Server.story.printStory(writer); // send the history of 10 last messages
            start(); //call overridden method run
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run(){
        String messageFromClient;

        try {
            //read name of client
            messageFromClient = reader.readLine();
            try {
                writer.write(messageFromClient + "\n");
                writer.flush();
            }catch (IOException e){
                e.printStackTrace();
            }

            try {
                while (true){
                    messageFromClient = reader.readLine();
                    if(messageFromClient.equals("stop")){
                        break; // if server received stop string - so we left the loop of listening messages from client
                    }

                    System.out.println("Echoing: " + messageFromClient);
                    Server.story.addMessageToStory(messageFromClient);
                    for(ServerManager manager : Server.serverList){

                        // send message from current client to all others clients
                        manager.send(messageFromClient);
                    }
                }

            }catch (NullPointerException e){
                e.printStackTrace();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void send(String message){

        try {
            writer.write(message);
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
