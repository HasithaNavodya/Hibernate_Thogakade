package lk.ijse.crud_using_hibernate.config;

import lk.ijse.crud_using_hibernate.entity.Customer;
import lk.ijse.crud_using_hibernate.entity.Item;
import lk.ijse.crud_using_hibernate.entity.Order;
import lk.ijse.crud_using_hibernate.entity.OrderDetails;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFactoryConfig {
    private static SessionFactoryConfig factoryConfig;
    private final SessionFactory sessionFactory;

    private SessionFactoryConfig(){
        sessionFactory = new Configuration().configure()
                .addAnnotatedClass(Customer.class)
                .addAnnotatedClass(Item.class)
                .addAnnotatedClass(Order.class)
                .addAnnotatedClass(OrderDetails.class)
                .buildSessionFactory();
    }

    public static SessionFactoryConfig getInstance(){
        return (null==factoryConfig) ? factoryConfig = new SessionFactoryConfig() : factoryConfig;
    }

    public Session getSession(){
        return sessionFactory.openSession();
    }

}
