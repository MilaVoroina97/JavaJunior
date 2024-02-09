package org.example.homework.five.client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;


public class Program {

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter your name:");
            // Set name
            String name = scanner.nextLine();

            InetAddress address = InetAddress.getLocalHost();

            Socket socket = new Socket(address, 7800);
            Client client = new Client(socket, name);
            InetAddress inetAddress = socket.getInetAddress();
            System.out.println("InetAddress: " + inetAddress);
            String remoteIp = inetAddress.getHostAddress();
            System.out.println("RemoteIP: " + remoteIp);
            System.out.println("LocalPort: " + socket.getLocalPort());

            client.listenForMessage();
            client.sendMessage();
        }
        catch (UnknownHostException e) {
            throw new RuntimeException();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
