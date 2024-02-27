package ru.gb.junior.chat.server;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class ClientManager1 implements Runnable{


    private Socket socket;
    private BufferedReader reader;
    private BufferedWriter writer;
    private String name;

    //Flag to show/hide low priority messages
    private boolean showLowPriorityMessages = true;
    protected static final List<ClientManager1> clients = new ArrayList<>();

    public ClientManager1(Socket socket){

        try {

            this.socket = socket;
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            this.name = reader.readLine();

            clients.add(this);
            System.out.println(name + " connected to the chat.");
            broadcastMessage("Server " + name + " connected to chat.");
        } catch (IOException e) {
            closeEverything(socket, reader, writer);
        }

    }

    private void removeClient(){

        clients.remove(this);
        System.out.println(name + " left the chat.");
        broadcastMessage("Server " + name + " left chat.");

    }

    @Override
    public void run() {

        String messageFromClient;
        while (socket.isConnected()){

            try {

                messageFromClient = reader.readLine();
                if(messageFromClient == null){
                    closeEverything(socket, reader, writer);
                    break;
                }
                broadcastMessage(messageFromClient);
            } catch (IOException e) {

                closeEverything(socket, reader, writer);
                break;
            }

        }
    }

    private void broadcastMessage(String message) {
        //realize sending messages with HIGH priority first
        PriorityQueue<String> priorityQueue = new PriorityQueue<>(new Comparator<String>() {
            @Override
            public int compare(String message1, String message2) {

                Priority priority1 = getMessagePriority(message1);
                Priority priority2 = getMessagePriority(message2);
                if(priority1 == priority2){
                    return  0;
                }else if(priority1 == Priority.HIGH){
                    return -1;
                }else {
                    return 1;
                }
            }
        });
        String[] messageFromClientArray = message.split(" ");
        boolean isPrivateMessage = messageFromClientArray[1].trim().startsWith("@");
        for (ClientManager1 client : clients) {

            // Skip broadcasting low priority message if showLowPriority flag is false
            if(!handleCommandFromClient(message)){
                continue;
            }

            // Check if the message priority is low and the flag to show low priority is true
            Priority priority = getMessagePriority(message);
            if(isLowPriority(priority)){
                continue;
            }
            boolean isClientNameExists = messageFromClientArray[1].replace("@", "").equals(client.name);
            try {

                if(isPrivateMessage && isClientNameExists){
                    String option = message.substring(message.indexOf("@")).trim();
                    String newMessage = option.substring(option.indexOf(" ")).trim();
                    String messageToSend = "Message from " + messageFromClientArray[0].replace(":","")
                            + " : " + newMessage;

                    messageSender(messageToSend,client);
                }else if(!client.name.equals(this.name) && message != null && !isPrivateMessage){

                    // Add message to priority queue
                    priorityQueue.offer(message);
                }

            } catch (IOException e) {
               closeEverything(socket, reader, writer);
            }
        }

        // Send messages in priority order
        while (!priorityQueue.isEmpty()){

            String messageToSend = priorityQueue.poll();
            for(ClientManager1 client : clients){
                if(!client.name.equals(this.name)){
                    try {
                        messageSender(messageToSend,client);
                    } catch (IOException e) {
                        closeEverything(socket, reader, writer);
                    }
                }
            }
        }
    }

    private void messageSender(String message, ClientManager1 client) throws IOException {

        client.writer.write(message);
        client.writer.newLine();
        client.writer.flush();

    }

    private void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        removeClient();
        try {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean handleCommandFromClient(String command){

        // Check if message is a command to show/hide low priority messages
        boolean isNeedToChangePriority = command.contains("!setLowPriority");
        // Toggle the flag to show or hide low priority messages
        if(isNeedToChangePriority){
            showLowPriorityMessages = !showLowPriorityMessages;
            return false;
        }

        return true;
    }

    private Priority getMessagePriority(String message){

        if(message.contains("ALERT")){
            return Priority.HIGH;
        }else if(message.contains("LOW")){
            return Priority.LOW;
        }
        return Priority.MEDIUM;

    }
    private boolean isLowPriority(Priority priority) {
        return priority == Priority.LOW;
    }

    private enum Priority{

        HIGH,
        LOW,
        MEDIUM
    }
}
