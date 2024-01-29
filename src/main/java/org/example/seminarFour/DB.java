package org.example.seminarFour;

import org.hibernate.Session;
import java.util.List;

public class DB {

    private final static String URL = "jdbc:mysql://localhost:3306/test?useSSL=false&allowPublicKeyRetrieval=true";
    private final static String USER = "root";
    private final static String PASSWORD = "admin";


    public static void con(){
        Connector connector = new Connector();
        try(Session session = connector.getSession()){
/*            List<Magic> books = session.createQuery("FROM Magic",Magic.class).getResultList();
            books.forEach(b -> System.out.println(b));*/

            String hql = "from Magic where id = :id";

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
/*    public static void con(){

        Connector connector = new Connector();
        Session session = connector.getSession();
        Magic magic = new Magic("Magic arrow",50,40,0);
        session.beginTransaction();
        session.save(magic);
        magic = new Magic("Молния", 25, 0, 0);
        session.save(magic);
        magic = new Magic("Каменная кожа", 0, 0, 6);
        session.save(magic);
        magic = new Magic("Жажда крови", 0, 6, 0);
        session.save(magic);
        magic = new Magic("Жажда крови", 0, 6, 0);
        session.save(magic);
        magic = new Magic("Проклятие", 0, -3, 0);
        session.save(magic);
        magic = new Magic("Лечение", -30, 0, 0);
        session.save(magic);
        session.getTransaction().commit();
        session.close();
    }*/


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
