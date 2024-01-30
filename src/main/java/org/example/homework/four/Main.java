package org.example.homework.four;

import org.example.homework.four.DB.DBConfiguration;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.example.homework.four.Model.Course;
import org.example.homework.four.UserInterface.View;

public class Main {
    public static void main(String[] args) {
        View view = new View();
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate2.cfg.xml")
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        while (true) {

            try (Session session = sessionFactory.getCurrentSession()) {
                switch (view.menu()) {

                    case 1 -> DBConfiguration.insertData(session);
                    case 2 -> DBConfiguration.showCourses(session);
                    case 3 -> DBConfiguration.updateTitle(session);
                    case 4 -> DBConfiguration.updateDuration(session);
                    case 5 -> DBConfiguration.deleteData(session);
                    case 0 -> System.exit(0);
                    default -> System.out.println("There is not such choice. Please, try again.");
                }
            }
        }
    }

}

