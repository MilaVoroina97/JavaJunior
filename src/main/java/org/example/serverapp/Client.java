package org.example.serverapp;


public class Client {

    public static void main(String[] args) {
        String ip = "localhost";
        int port = 5500;

        //creating a client connection with a specified address and port number
        new ClientManager(ip,port);
    }

}
