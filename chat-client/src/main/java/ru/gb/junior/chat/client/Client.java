package ru.gb.junior.chat.client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private final Socket socket;

    private final String name;

    private BufferedWriter writer;

    private BufferedReader reader;

    public Client(Socket socket, String name) {
        this.socket = socket;
        this.name = name;
        try {

            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            closeEverything(socket,reader,writer);
        }
    }




    /**
     * Listening for message from server
     * In this method we create a separate new thread for receiving message from server
     */
    public void listenForMessage(){

        //create a separate thread
        new Thread(new Runnable() {
            @Override
            public void run() {

                //if socket is active
                String message;
                while (socket.isConnected()){

                    try {
                        message = reader.readLine();
                        System.out.println(message);
                    } catch (IOException e) {
                        closeEverything(socket,reader,writer);
                    }
                }
            }
        }).start();
    }

    /**
     * Send message to the server from the main thread
     */
    public void sendMessage(){

        try {
            writer.write(name);
            writer.newLine();
            writer.flush();

            Scanner scanner = new Scanner(System.in);

            while (socket.isConnected()){
                String message = scanner.nextLine();
                writer.write(name + ": " + message);
                writer.newLine();
                writer.flush();
            }
        } catch (IOException e) {
            closeEverything(socket,reader,writer);
        }
    }

    public void sendTypingMessage() {
        try {
            writer.write(name + " is typing...");
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            closeEverything(socket, reader, writer);
        }
    }


    /**
     * If occurs exceptions during processes of input or output stream or socket connection,
     * then it's necessary to close all open resources
     */
    private void closeEverything(Socket socket, BufferedReader reader, BufferedWriter writer){

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
}
