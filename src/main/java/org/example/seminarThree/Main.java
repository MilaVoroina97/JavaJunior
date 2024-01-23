package org.example.seminarThree;

import java.io.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        /**
         * Example of serialization
         */

        String str = "Hello";
        //create thread for writing in file
        FileOutputStream fileOutputStream = new FileOutputStream("ser.txt");
        //create thread for writing object of file
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        //make write of object
        objectOutputStream.writeObject(str);
        //close the thread
        objectOutputStream.close();

        /**
         * Example of deserialization
         */

        //create thread of byting reading from file
        FileInputStream fileInputStream = new FileInputStream("ser.txt");
        //create thread for byting loading of object from file
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        // load the object from thread
        String hello = (String) objectInputStream.readObject();
        System.out.println(hello);


        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            arrayList.add(Character.getName(i));
        }

        serialObj(arrayList, "ser.txt");

        ArrayList<String> list = null;
        list = (ArrayList<String>) deSerialObj("ser.txt");
        System.out.println(list);

        //Third example:

        MyFCs myFCs = new MyFCs("Ivanov", "Ivan", "Ivanovich");
        serialObj(myFCs, "file.txt");

        MyFCs myFCsAfterDeSerial = (MyFCs) deSerialObj("file.txt");
        System.out.println(myFCsAfterDeSerial);
    }

    public static void serialObj(Object object, String file) throws IOException {

        FileOutputStream fileOutputStream = new FileOutputStream(file);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(object);
        objectOutputStream.close();
    }

    public static Object deSerialObj(String file) throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(file);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        return objectInputStream.readObject();
    }

    static class MyFCs implements Externalizable {
        private static final long serialVersionUID = 1L;
        private static String lName;
        private String fName;
        private String patronymic;
        private transient DOB dob;

        public MyFCs(String lName, String fName, String patronymic) {
            this.lName = lName;
            this.fName = fName;
            this.patronymic = patronymic;
            this.dob = new DOB(01, 02, 03);
        }

        public MyFCs(){}
        @Override
        public String toString() {
            return String.format("%s %s.%s. %s",
                    fName, lName.toUpperCase().charAt(0),
                    patronymic.toUpperCase().charAt(0),
                    dob.day+"/"+dob.month+"/"+dob.year);
        }

        @Override
        public void writeExternal(ObjectOutput out) throws IOException {
            out.writeObject(this.dob);
            out.writeObject(this.fName);
            out.writeObject(this.patronymic);
            String tmp = lName;
            out.writeObject(tmp);

        }

        @Override
        public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
            this.dob = (DOB) in.readObject();
            this.fName = (String) in.readObject();
            this.patronymic = (String) in.readObject();
            lName = (String) in.readObject();

        }
    }

    static class DOB implements Serializable{
        int day;
        int month;
        int year;

        public DOB(int day, int month, int year) {
            this.day = day;
            this.month = month;
            this.year = year;

        }
    }
}