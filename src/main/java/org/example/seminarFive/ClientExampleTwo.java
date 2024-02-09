package org.example.seminarFive;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class ClientExampleTwo {

    public static void main(String[] args) throws IOException {
        InetAddress inetAddress = InetAddress.getLocalHost();
        Socket client = new Socket(inetAddress,1300);

        System.out.println(client.getInetAddress());
        System.out.println(client.getLocalPort());

        // return of client socket input and output
        InputStream inputStream = client.getInputStream();
        OutputStream outputStream = client.getOutputStream();

        DataInputStream dataInputStream = new DataInputStream(inputStream);
        PrintStream printStream = new PrintStream(outputStream);

        printStream.println("Hi");
        System.out.println(dataInputStream.readLine());
        client.close();

    }
}
