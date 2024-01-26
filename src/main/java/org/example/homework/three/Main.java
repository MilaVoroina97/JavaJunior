package org.example.homework.three;


import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Student student = new Student("Nike",15,5.0);
        System.out.println("New student:" + student);

        System.out.println("Start serialization: ");
        SerializeStudentClass.serializeStudent(SerializeStudentClass.FILE_BIN,student);
        SerializeStudentClass.serializeStudent(SerializeStudentClass.FILE_XML,student);
        SerializeStudentClass.serializeStudent(SerializeStudentClass.FILE_JSON,student);

        System.out.println("Start deserialization");
        SerializeStudentClass.deserializeStudent(SerializeStudentClass.FILE_BIN,student);
        SerializeStudentClass.deserializeStudent(SerializeStudentClass.FILE_XML,student);
        SerializeStudentClass.deserializeStudent(SerializeStudentClass.FILE_JSON,student);
    }
}
