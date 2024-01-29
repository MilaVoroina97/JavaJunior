package org.example.seminarFour;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.registry.internal.StandardServiceRegistryImpl;

public class Main {

    public static void main(String[] args) {

        //DB.con();

        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build(); // contains connection mechanism with DB server and request transfer manager
        SessionFactory sessionFactory = new MetadataSources(registry)
                .buildMetadata().buildSessionFactory();
    }
}
