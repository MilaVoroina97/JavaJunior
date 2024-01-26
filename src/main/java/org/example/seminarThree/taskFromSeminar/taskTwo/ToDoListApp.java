package org.example.seminarThree.taskFromSeminar.taskTwo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ToDoListApp {

    public static final String FILE_JSON = "tasks.json";
    public static final String FILE_BIN = "tasks.bin";
    public static final String FILE_XML = "tasks.xml";

    private final static ObjectMapper objectMapper = new ObjectMapper();
    private final static XmlMapper xmlMapper = new XmlMapper();

    public static void addNewTask(Scanner scanner, List<ToDoV2> tasks){

        System.out.println("Enter the task:");
        String newTask = scanner.nextLine();
        tasks.add(new ToDoV2(newTask));

        saveTaskToFile(FILE_JSON,tasks);
        saveTaskToFile(FILE_BIN,tasks);
        saveTaskToFile(FILE_XML,tasks);
        System.out.println("Task has been added");

    }

    public static void markTaskAsDone(Scanner scanner, List<ToDoV2> tasks){

        System.out.println("Enter the number of task");
        String input = scanner.nextLine();
        try {
            int taskNumber = Integer.parseInt(input) - 1;
            if(taskNumber >= 0 && taskNumber < tasks.size()){
                tasks.get(taskNumber).setDone(true);
                saveTaskToFile(FILE_JSON,tasks);
                saveTaskToFile(FILE_BIN,tasks);
                saveTaskToFile(FILE_XML,tasks);
            }else {
                System.out.println("Uncorrect number of task.");
            }

        }catch (NumberFormatException e){

            System.out.println(e.getMessage());

        }
    }


    public  static List<ToDoV2> loadTaskFromFile(String fileName){

        List<ToDoV2> tasks = new ArrayList<>();
        File file = new File(fileName);
        if(file.exists()){
            try {

                if(fileName.endsWith("json")){
                    tasks = objectMapper.readValue(file, objectMapper.getTypeFactory().constructCollectionType(List.class, ToDoV2.class));

                }else if(fileName.endsWith(".bin")){
                    try(ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))){
                        tasks = (List<ToDoV2>) objectInputStream.readObject();
                    }
                }else if(fileName.endsWith(".xml")){
                    tasks = xmlMapper.readValue(file,xmlMapper.getTypeFactory().constructCollectionType(List.class, ToDoV2.class));
                }

            }catch (IOException | ClassNotFoundException e){

                System.out.println(e.getMessage());

            }
        }

        return tasks;
    }

    public static void saveTaskToFile(String fileName, List<ToDoV2> tasks){
        try {

            if(fileName.endsWith("json")){
                objectMapper.configure(SerializationFeature.INDENT_OUTPUT,true);
                objectMapper.writeValue(new File(fileName),tasks);
            }else if(fileName.endsWith(".bin")){
                try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))){
                    oos.writeObject(tasks);
                }
            }else if(fileName.endsWith(".xml")){
                //String s = xmlMapper.writeValueAsString(tasks);
                xmlMapper.configure(SerializationFeature.INDENT_OUTPUT,true);
                xmlMapper.writeValue(new File(fileName),tasks);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static void displayTasks(List<ToDoV2> tasks){
        System.out.println("The list of tasks: ");
        for(int i = 0; i < tasks.size(); i++){

            ToDoV2 task = tasks.get(i);
            String status = task.isDone() ? "[x]" : "[ ]";
            System.out.println((i + 1) + ". " + status + " " + task.getTitle());
        }
    }
}
