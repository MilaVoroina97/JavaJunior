package ru.gb.junior.chat.client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) {

        try {
            System.out.println("Client...");
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter your name: ");
            String name = scanner.nextLine();
            InetAddress inetAddress = InetAddress.getLocalHost();

            //initialize client socket
            Socket socket = new Socket(inetAddress,4500);
            Client client = new Client(socket,name);

            System.out.println("InetAddress:" + inetAddress);
            String remoteIp =inetAddress.getHostAddress();
            System.out.println("Remote IP: " + remoteIp);
            System.out.println("LocalPort: " + socket.getLocalPort());

            client.listenForMessage();
            client.sendMessage();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
