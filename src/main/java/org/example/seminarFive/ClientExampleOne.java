package org.example.seminarFive;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientExampleOne {

    public static void main(String[] args) {

        try{
            InetAddress inetAddress = InetAddress.getLocalHost();
            Socket client = new Socket(inetAddress,1300);

        }catch (UnknownHostException ex){
            ex.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
