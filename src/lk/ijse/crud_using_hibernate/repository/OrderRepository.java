package lk.ijse.crud_using_hibernate.repository;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.crud_using_hibernate.config.SessionFactoryConfig;
import lk.ijse.crud_using_hibernate.entity.Order;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class OrderRepository {
    private final Session session;

    public OrderRepository(){
        session= SessionFactoryConfig.getInstance().getSession();
    }

    public Order getOrder(String id){
        try {
            Order order = session.get(Order.class, id);
            session.close();
            return order;
        }catch (Exception e){
            System.out.println(e);
            session.close();
            throw e;
        }
    }

    public boolean saveOrder(Order order){
        Transaction transaction = session.beginTransaction();
        try {
            session.save(order);
            transaction.commit();
            session.close();
            return true;
        }catch (Exception e){
            transaction.rollback();
            session.close();
            System.out.println(e);
            System.out.println("Order saving process failed");
            return false;
        }
    }

    public boolean updateOrder(Order order){
        Transaction transaction = session.beginTransaction();
        try {
            session.update(order);
            transaction.commit();
            session.close();
            return true;
        }catch (Exception e){
            transaction.rollback();
            session.close();
            System.out.println(e);
            System.out.println("Order updating process failed");
            return false;
        }
    }

    public boolean deleteOrder(Order order){
        Transaction transaction = session.beginTransaction();
        try {
            session.delete(order);
            transaction.commit();
            session.close();
            return true;
        }catch (Exception e){
            transaction.rollback();
            session.close();
            System.out.println(e);
            System.out.println("Order deletion process failed");
            return false;
        }
    }

    public ObservableList<Order> getDetailsToTableView(){
        Transaction transaction = session.beginTransaction();

        List<Order> dataList = session.createQuery("FROM Order ", Order.class).list();

        transaction.commit();
        session.close();

        ObservableList<Order> observableList = FXCollections.observableArrayList(dataList);
        return observableList;
    }
}
