package org.example.seminarFour.tasksFromSeminar.task1;


import org.example.seminarFour.tasksFromSeminar.models.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

public class Program {

    private final static Random random = new Random();


    public static void main(String[] args)  {

        String URL = "jdbc:mysql://localhost:3306";
        String USER = "root";
        String PASSWORD = "admin";

        try{
            Connection connection = DriverManager.getConnection(URL,USER,PASSWORD);
            createDB(connection);
            System.out.println("Database created successfully");
            useStudentDB(connection);
            System.out.println("Use database successfully");
            createTable(connection);
            System.out.println("Create table successfully");
            int count = random.nextInt(5) + 3;
            for (int i = 0; i < count; i++){
                insertData(connection, Student.create());
            }
            System.out.println("Insert data successfully");

            Collection<Student> students = readData(connection);
            for(var student : students){
                System.out.println(student);
            }
            System.out.println("Read data successfully");

            for(var student : students){
                student.updateName();
                student.updateAge();
                updateData(connection,student);
            }
            System.out.println("Update data successfully");

/*            for(var student : students){
                deleteData(connection,student.getId());
            }
            System.out.println("Delete data successfully");*/

            connection.close();
            System.out.println("Database connection close successfully");

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    private static void createDB(Connection connection) throws SQLException {
        String createDBSQL = "CREATE DATABASE IF NOT EXISTS studentsDB;";
        PreparedStatement preparedStatement = connection.prepareStatement(createDBSQL);
        preparedStatement.execute();
    }

    private static void useStudentDB(Connection connection)throws SQLException{

        String useDBSQL = "USE studentsDB;";
        try(PreparedStatement preparedStatement = connection.prepareStatement(useDBSQL)){

            preparedStatement.execute();
        }
    }

    private static void createTable(Connection connection) throws SQLException{
        String createTableSQl = "CREATE TABLE IF NOT EXISTS students(id INT AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(255), age INT);";

        try(PreparedStatement statement = connection.prepareStatement(createTableSQl)){

            statement.execute();
        }
    }

    /**
     * Insert data in table `students`
     * @param connection Connection to DB
     * @param student Instance of Student class
     * @return count numbers of updated objects in table `students`
     * @throws SQLException exception during query execution
     */

    private static int insertData(Connection connection, Student student) throws SQLException{
        String insertDataSQL = "INSERT INTO students (name, age) VALUES(?, ?);";
        int count = 0;
        try(PreparedStatement preparedStatement = connection.prepareStatement(insertDataSQL)){
            preparedStatement.setString(1, student.getName());
            preparedStatement.setInt(2, student.getAge());
            count = preparedStatement.executeUpdate();
        }
        return count;
    }

    /**
     * Reading data from table in DB `students`
     * @param connection - connection to DB
     * @return collection of students
     * @throws SQLException - exception during query executing
     */

    private static Collection<Student> readData(Connection connection) throws SQLException {

        ArrayList<Student> students = new ArrayList<>();
        String readDataSQL = "SELECT * FROM students";
        try (PreparedStatement statement = connection.prepareStatement(readDataSQL)) {

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                students.add(new Student(id, name, age));
            }

            return students;
        }
    }

    /**
     * Update data in the table `students` by the student ID
     * @param connection Connection to DB
     * @param student Instance of Student class
     * @throws SQLException exception during query execution
     */

    private static void updateData(Connection connection, Student student) throws SQLException{
        String updateDataSQL = "UPDATE students SET name=?, age=? WHERE id=?;";
        try(PreparedStatement statement = connection.prepareStatement(updateDataSQL)){
            statement.setString(1,student.getName());
            statement.setInt(2,student.getAge());
            statement.setInt(3,student.getId());
            statement.executeUpdate();
        }
    }

    private static void deleteData(Connection connection, int id) throws SQLException{

        String deleteDataSQL = "DELETE FROM students WHERE id=?;";
        try(PreparedStatement statement = connection.prepareStatement(deleteDataSQL)){
            statement.setLong(1,id);
            statement.executeUpdate();
        }
    }


}
