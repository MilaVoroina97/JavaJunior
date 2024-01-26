package org.example.homework.three;


import java.io.IOException;
public class Main {

    public static void main(String[] args) throws IOException {
        Student student = new Student("Nike",15,5.0);
        System.out.println("New student:" + student);

        System.out.println("Start serialization: ");
        SerializeStudentClass.serializeStudent(SerializeStudentClass.FILE_BIN,student);
        SerializeStudentClass.serializeStudent(SerializeStudentClass.FILE_XML,student);
        SerializeStudentClass.serializeStudent(SerializeStudentClass.FILE_JSON,student);

        System.out.println("Start deserialization");
        SerializeStudentClass.deserializeStudent(SerializeStudentClass.FILE_BIN);
        SerializeStudentClass.deserializeStudent(SerializeStudentClass.FILE_XML);
        Student student1 = SerializeStudentClass.deserializeStudent(SerializeStudentClass.FILE_JSON);
        System.out.println(student1);
    }
}
