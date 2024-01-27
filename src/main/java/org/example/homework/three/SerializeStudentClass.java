package org.example.homework.three;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.*;

public class SerializeStudentClass {

    public static final String FILE_JSON = "student.json";
    public static final String FILE_BIN = "student.bin";
    public static final String FILE_XML = "student.xml";
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final XmlMapper xmlMapper = new XmlMapper();


    public static void serializeStudent(String fileName, Student student) throws IOException {
        try{
            if(fileName.endsWith(".json")){
                objectMapper.configure(SerializationFeature.INDENT_OUTPUT,true);
                objectMapper.writeValue(new File(fileName),student);
            }else if(fileName.endsWith(".bin")){
                try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))){
                    oos.writeObject(student);
                }
            }else if(fileName.endsWith(".xml")){
                xmlMapper.configure(SerializationFeature.INDENT_OUTPUT,true);
                xmlMapper.writeValue(new File(fileName),student);
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
        }


    }

    public static Student deserializeStudent(String filename){
        File file = new File(filename);
        Student student = new Student();
        //xmlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        if(file.exists()){
            try {
                if(filename.endsWith(".json")){
                    student = objectMapper.readValue(file,Student.class);
                }else if(filename.endsWith(".bin")){
                    try(ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))){
                        student = (Student) objectInputStream.readObject();
                    }
                }else if(filename.endsWith("xml")){
                    student = xmlMapper.readValue(file, Student.class);
                }
            } catch (IOException | ClassNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
        return student;
    }


}
