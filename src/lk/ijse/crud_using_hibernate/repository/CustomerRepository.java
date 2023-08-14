package lk.ijse.crud_using_hibernate.repository;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.crud_using_hibernate.config.SessionFactoryConfig;
import lk.ijse.crud_using_hibernate.entity.Customer;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CustomerRepository {
    private final Session session;

    public CustomerRepository(){
        session= SessionFactoryConfig.getInstance().getSession();
    }

    public Customer getCustomer(String id){
        try {
            Customer customer = session.get(Customer.class, id);
            session.close();
            return customer;
        }catch (Exception e){
            // e.printStackTrace();
            System.out.println(e);
            throw e;
        }
    }

    public String saveCustomer(Customer customer){
        Transaction transaction = session.beginTransaction();
        try {
            String cust_id = (String) session.save(customer);
            transaction.commit();
            session.close();
            return cust_id;
        }catch (Exception e){
            transaction.rollback();
            session.close();
            // e.printStackTrace();
            System.out.println(e);
            System.out.println("customer save failed");
            return "failed";
        }
    }

    public boolean updateCustomer(Customer customer){
        Transaction transaction = session.beginTransaction();
        try {
            session.update(customer);
            transaction.commit();
            session.close();
            return true;
        }catch (Exception e){
            transaction.rollback();
            session.close();
            // e.printStackTrace();
            System.out.println(e);
            System.out.println("failed");
            return false;
        }
    }

    public boolean deleteCustomer(Customer customer){
        Transaction transaction = session.beginTransaction();
        try {
            session.delete(customer);
            transaction.commit();
            session.close();
            return true;
        }catch (Exception e){
            transaction.rollback();
            session.close();
            // e.printStackTrace();
            System.out.println(e);
            System.out.println("failed");
            return false;
        }
    }

    public ObservableList<Customer> getDetailsToTableView(){
        Transaction transaction = session.beginTransaction();

        List<Customer> dataList = session.createQuery("FROM Customer ", Customer.class).list();

        transaction.commit();
        session.close();

        ObservableList<Customer> observableList = FXCollections.observableArrayList(dataList);
        return observableList;
    }

}
