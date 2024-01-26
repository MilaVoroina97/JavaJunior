package org.example.seminarThree.taskFromSeminar.taskOne;

import java.io.*;

public class Program {
    public static void main(String[] args) {
        UserData userData = new UserData("Nike",40,"123456789");

        try (FileOutputStream fileOutputStream = new FileOutputStream("userdata.bin")){

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(userData);
            System.out.println("Done");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            FileInputStream fileInputStream = new FileInputStream("userdata.bin");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            UserData newUserData = (UserData) objectInputStream.readObject();
            System.out.println(newUserData);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }



}
