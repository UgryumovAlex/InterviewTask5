package ru.geekbrains;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateFactory {

    private static SessionFactory factory;

    public static SessionFactory getFactory() {
        if (factory == null) {
            factory = new Configuration()
                         .configure("hibernate.cfg.xml")
                         .buildSessionFactory();
        }
        return factory;
    }

    public static void CloseFactory() {
        if (factory != null) {
            factory.close();
            factory = null;
        }
    }

}
