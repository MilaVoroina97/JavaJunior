package org.example.seminarFive.chat;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientManager implements Runnable{

    //channel for connection
    private Socket socket;
    //streams for to accept and send data
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;

    //name of the client
    private String name;

    //list of all clients
    public static ArrayList<ClientManager> clients = new ArrayList<>();

    public ClientManager(Socket socket){

        try {

            this.socket = socket;
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            name = bufferedReader.readLine();
            clients.add(this);

        }catch (IOException ex){

            closeEverything(socket,bufferedReader,bufferedWriter);
        }
    }
    @Override
    public void run() {

        String messageFromClient;

        //while client is connected, the loop will be continued
        while (socket.isConnected()){
            try {

                //waiting message from the client
                messageFromClient = bufferedReader.readLine();
                broadcastMessage(messageFromClient);

            }catch (IOException ex){

                closeEverything(socket,bufferedReader,bufferedWriter);
                break;
            }
        }
    }

    /**
     * the method of sending messages
     * @param messageToSend is a message which would be sent from current client
     */
    private void broadcastMessage(String messageToSend){

        for (ClientManager client : clients){
            try {

                //if the name of client is not the same as the current client's name, then we use writer to write a line from chat
                if(!client.name.equals(name)){
                    client.bufferedWriter.write(messageToSend);
                    client.bufferedWriter.newLine();
                    client.bufferedWriter.flush();
                }
            }catch (IOException e){

                closeEverything(socket,bufferedReader,bufferedWriter);
            }
        }
    }

    /**
     * Method for closing of all using resources and removing current client from list of all clients
     */

    private void closeEverything(Socket socket, BufferedReader bufferedReader,
                                 BufferedWriter bufferedWriter){

        removeClient();

        try {

            if(bufferedReader != null){
                bufferedReader.close();
            }if(bufferedWriter != null){
                bufferedWriter.close();
            }if(socket != null){
                socket.close();
            }
        }catch (IOException ex){

            ex.printStackTrace();
        }
    }

    /**
     *  Remove current client from chat and send to others message about his leaving
     */

    public void removeClient(){
        clients.remove(this);
        broadcastMessage("SERVER: "+name+" покинул чат.");

    }
}
