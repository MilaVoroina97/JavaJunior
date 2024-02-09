package org.example.homework.five.server;


import java.io.*;
import java.net.Socket;
import java.util.ArrayList;


public class ClientManager implements Runnable{

    private Socket socket;
    private BufferedReader reader;
    private BufferedWriter writer;
    private String name;

    public static ArrayList<ClientManager> clients = new ArrayList<>();

    public ClientManager(Socket socket) {
        try {
            this.socket = socket;
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            name = reader.readLine();
            clients.add(this);
            System.out.println(name + "just joined the chat");
            broadcastMessage("Server: " + name + " joined the chat");

        } catch (IOException e) {
            closeEverything(socket, writer, reader);
        }
    }

    public void broadcastMessage(String message) {
        for (ClientManager client : clients) {
            try {
                if (!client.name.equals(name) && message != null) {
                    client.writer.write(message);
                    client.writer.newLine();
                    client.writer.flush();
                }

            }
            catch (IOException e) {
                closeEverything(socket, writer, reader);
            }
        }
    }

    public void closeEverything(Socket socket, BufferedWriter writer, BufferedReader reader) {
        removeClient();
        try {
            if (reader != null) {
                reader.close();
            }
            if (writer != null) {
                writer.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void removeClient() {
        clients.remove(this);
        System.out.println(name + " just left the chat");
        broadcastMessage("Server: " + name + " just left the chat");
    }

    @Override
    public void run() {
        String messageFromClient;

        while (socket.isConnected()) {
            try {
                messageFromClient = reader.readLine();
                broadcastMessage(messageFromClient);
            } catch (IOException e) {
                closeEverything(socket, writer, reader);
                break;
            }

        }

    }
}
