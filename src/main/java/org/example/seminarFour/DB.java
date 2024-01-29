package org.example.seminarFour;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


import java.sql.*;

public class DB {

    private final static String URL = "jdbc:mysql://localhost:3306";
    private final static String USER = "root";
    private final static String PASSWORD = "admin";

    public static void con(){
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();

        SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Magic magic = new Magic("Magic arrow",50,40);
        session.beginTransaction();
        session.save(magic);
        session.getTransaction().commit();
        session.close();
    }


    /*public static void con(){
        try(Connection connection = DriverManager.getConnection(URL,USER,PASSWORD)){

            Statement statement = connection.createStatement();
            //create new DB
            statement.execute("DROP SCHEMA `test`");
            statement.execute("CREATE SCHEMA `test`");
            statement.execute("CREATE TABLE `test`.`table` (`id` INT NOT NULL, `firstName` VARCHAR(45) NULL," +
                    " `lastName` VARCHAR(45) NULL, PRIMARY KEY (`id`));");
            statement.execute("INSERT INTO `test`.`table` (`id`,`firstName`,`lastName`)\n" +
                    "VALUES (1,'Ivan','Ivanov')");
            statement.execute("INSERT INTO `test`.`table` (`id`,`firstName`,`lastName`)\n" +
                    "VALUES (2,'Petr','Petrov')");

            ResultSet set = statement.executeQuery("SELECT * FROM `test`.`table`;");
            while (set.next()){
                System.out.println(set.getString(3) + " " + set.getString(2) + " "
                        + set.getInt(1));
            }
        }

         catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }*/



}
