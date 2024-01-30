package org.example.homework.four.DB;

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
                    // Вставка данных
                    case 1 -> Hibernate.insertData(session);
                    // Чтение данных
                    case 2 -> Hibernate.showCourses(session);
                    // Изменение названия курса
                    case 3 -> Hibernate.updateTitle(session);
                    // Изменение продолжительности курса
                    case 4 -> Hibernate.updateDuration(session);
                    // Удаление данных
                    case 5 -> Hibernate.deleteData(session);
                    // Закрытие приложения
                    case 0 -> System.exit(0);
                    default -> System.out.println("Некорректный ввод");
                }
            }
        }
    }

}

