package pl.edu.agh.iet.to2.employees.persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import pl.edu.agh.iet.to2.employees.model.Employee;

import java.io.File;

public class HibernateUtils {

    private static Class[] classes = {Employee.class};

    private static SessionFactory sessionFactory;

    public static synchronized SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = createSessionFactory();
        }

        return sessionFactory;
    }

    private static SessionFactory createSessionFactory() {
        Configuration configuration = new Configuration();
        configuration.configure(new File("employees/hibernate.cfg.xml"));

        // add all classes from an array
        for (Class aClass : classes) {
            configuration.addClass(aClass);
        }

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    public static Session getSession() {
        return getSessionFactory().openSession();
    }

    public static void shutdown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }

}
