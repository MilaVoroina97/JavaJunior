package org.example.serverapp;

import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ClientManager {

    private Socket socket;
    private BufferedWriter writer; // stream of reading from socket
    private BufferedReader reader; // stream of writing in socket

    private BufferedReader inputFromUser; // steam of reading from console
    private String ip; //client IP - address
    private int port;
    private String nickname;
    private Date time;
    private String dtime;
    private SimpleDateFormat dt1;

    public ClientManager(String ip, int port){

        this.ip = ip;
        this.port = port;

        try {
            this.socket = new Socket(ip,port);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //create stream for reading and writing, and reading from console
        try {

            inputFromUser = new BufferedReader(new InputStreamReader(System.in)); // read from console
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.enterNickname(); //receive name from client
            new ReadingMsg().start(); //thread reading messages from a socket in an infinite loop
            new WritingMsg().start(); //thread writing messages to the socket coming from the console in an endless loop
        }catch (IOException e){

            //The socket must be closed on any error other than a socket constructor error
            ClientManager.this.downService();
        }
    }

    /**
     * Method from getting nickname from user
     * Server will send greeting with client nickname after user texted it.
     */

    private void enterNickname(){

        System.out.println("Enter your nick: ");
        try {

            this.nickname = reader.readLine();
            writer.write("Hello from " + nickname + "\n");
            writer.flush();

        }catch (IOException e){

            e.printStackTrace();
        }

    }

    /**
     * Close socket
     */
    private void downService() {
        try {
            if (!socket.isClosed()) {
                socket.close();
                reader.close();
                writer.close();
            }
        } catch (IOException ignored) {}
    }

    /**
     * Class - thread sending messages coming from the console to the server
     */
    private class ReadingMsg extends Thread{

        @Override
        public void run(){

            String msg;
            try {

                while (true){

                    msg = reader.readLine(); //waiting for the message from server
                    if(msg.equals("stop")){

                        ClientManager.this.downService();
                        break;
                    }
                    System.out.println(msg);
                }
            }catch (IOException e){

                ClientManager.this.downService();
            }

        }

    }

    /**
     * Class - thread to send messages from
     */
    private class WritingMsg extends Thread{

        @Override
        public void run(){

            while (true){

                String msgFromClient;

                try {

                    time = new Date();
                    dt1 = new SimpleDateFormat("HH:mm:ss");
                    dtime = dt1.format(time);
                    msgFromClient = inputFromUser.readLine(); //message from the console
                    if(msgFromClient.equals("stop")){
                        writer.write("stop");
                        ClientManager.this.downService();
                        break;
                    }else {
                        //send message to server
                        writer.write("( " + dtime + " ) " + nickname + " : " + msgFromClient);
                    }
                }catch (IOException e){

                }
            }
        }
    }
}
