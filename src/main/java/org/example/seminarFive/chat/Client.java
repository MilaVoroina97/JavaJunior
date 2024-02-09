package org.example.seminarFive.chat;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String name;

    public Client(Socket socket, String clientName){

        this.socket = socket;
        this.name = clientName;

        try {

            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        }catch (IOException ex){

            closeEverything(socket,bufferedReader,bufferedWriter);
        }
    }

    /**
     * Method for sending message from a client
     */

    public void sendMessage(){

        try {

            //send to the server the name of client
            bufferedWriter.write(name);
            bufferedWriter.newLine();
            bufferedWriter.flush();

            //initialize scanner for reading text from console
            Scanner scanner = new Scanner(System.in);

            //while there is a connection, read the line and send message to server
            while (socket.isConnected()){

                String message = scanner.nextLine();
                bufferedWriter.write(message);
                bufferedWriter.newLine();
                bufferedWriter.flush();

            }
        }catch (IOException ex){
            closeEverything(socket, bufferedReader,bufferedWriter);
        }
    }

    /**
     * Listening from the server
     */

    public void listenForMessage(){

        new Thread(() -> {

            String messageFromGroup;
            while (socket.isConnected()){
                try {
                    messageFromGroup = bufferedReader.readLine();
                    System.out.println(messageFromGroup);
                }catch (IOException e){
                    closeEverything(socket,bufferedReader,bufferedWriter);
                }
            }

        }).start();
    }

    /**
     * Method for closing of all using resources and removing current client from list of all clients
     */

    private void closeEverything(Socket socket, BufferedReader bufferedReader,
                                 BufferedWriter bufferedWriter){

        try{

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

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please,write your name: ");
        String name = scanner.nextLine();
        Socket newSocket = new Socket("localhost",1300);
        Client client = new Client(newSocket,name);
        client.listenForMessage();
        client.sendMessage();

    }
}
