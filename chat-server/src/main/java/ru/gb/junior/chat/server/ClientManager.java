package ru.gb.junior.chat.server;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Class - wrapper for multiple client connection with server
 */

public class ClientManager implements Runnable{


    private final Socket socket;
    private  String name;

    private BufferedWriter writer;

    private BufferedReader reader;

    //list of all clients connections
    public static ArrayList<ClientManager> clients = new ArrayList<>();


    public ClientManager(Socket socket) {
        this.socket = socket;

        try {

            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            //when client has just connected to the server, it immediately sends to server its name
            name = reader.readLine();

            //add to the client connection list a link to the current client
            clients.add(this);

            System.out.println(name + " connected to the chat.");

            broadcastMessage("Server " + name + " connected to chat.");
        } catch (IOException e) {
            closeEverything(socket,reader,writer);
        }
    }

    /**
     * Delete current client from the list of clients connections if during ClientManager class occurs exception
     */

    private void removeClient(){

        clients.remove(this);
        System.out.println(name + " left the chat.");
        broadcastMessage("Server " + name + " left chat.");

    }

    private void closeEverything(Socket socket, BufferedReader reader, BufferedWriter writer){

        removeClient();
        try {

            //if bufferedReader was initialized - we need to close it
            if(reader != null)
                reader.close();
            if(writer != null)
                writer.close();
            if(socket != null)
                socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * In a separate thread server will read messages from current client
     */

    @Override
    public void run() {

        String messageFromClient;

        while (socket.isConnected()){
            try{

                messageFromClient = reader.readLine();

                if(messageFromClient == null){
                    closeEverything(socket,reader,writer);
                    break;
                }
                broadcastMessage(messageFromClient);
            } catch (IOException e) {
                closeEverything(socket,reader,writer);
                break;
            }
        }
    }

    /**
     * Send message to all clients
     * @param message message from current client
     */

    private void broadcastMessage(String message){

        for(ClientManager client : clients){

            try {

                //work only with such clients, who are not the senders of this message
                if(!client.name.equals(this.name) && message != null){

                    client.writer.write(message);
                    client.writer.newLine();
                    client.writer.flush();

                }
            }catch (IOException ex){

                closeEverything(socket,reader,writer);
            }
        }

    }
}
