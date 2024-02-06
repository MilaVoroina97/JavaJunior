package org.example.seminarFour.tasksFromSeminar.task2;


import org.example.seminarFour.tasksFromSeminar.models.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class Program {

    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate2.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        try(Session session = sessionFactory.getCurrentSession()){

            session.beginTransaction();

            Student student = Student.create();
            session.save(student); //insert into
            Student retrievedStudent = session.get(Student.class,student.getId());
            System.out.println(retrievedStudent);


            retrievedStudent.updateName();
            retrievedStudent.updateAge();
            session.update(retrievedStudent);

            //session.delete(retrievedStudent);
            session.getTransaction().commit();
        }

    }
}
