package lk.ijse.crud_using_hibernate.repository;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.crud_using_hibernate.config.SessionFactoryConfig;
import lk.ijse.crud_using_hibernate.entity.Customer;
import lk.ijse.crud_using_hibernate.entity.Item;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class ItemRepository {
    private final Session session;

    public ItemRepository(){
        session= SessionFactoryConfig.getInstance().getSession();
    }

    public Item getItem(String code){
        try {
            Item item = session.get(Item.class, code);
            session.close();
            return item;
        }catch (Exception e){
            System.out.println(e);
            session.close();
            throw e;
        }
    }

    public boolean saveItem(Item item){
        Transaction transaction = session.beginTransaction();
        try {
            session.save(item);
            transaction.commit();
            session.close();
            return true;
        }catch (Exception e){
            transaction.rollback();
            session.close();
            System.out.println(e);
            System.out.println("item saving process failed");
            return false;
        }
    }

    public boolean updateItem(Item item){
        Transaction transaction = session.beginTransaction();
        try {
            session.update(item);
            transaction.commit();
            session.close();
            return true;
        }catch (Exception e){
            transaction.rollback();
            session.close();
            System.out.println(e);
            System.out.println("item update process failed");
            return false;
        }
    }

    public boolean deleteItem(Item item){
        Transaction transaction = session.beginTransaction();
        try {
            session.delete(item);
            transaction.commit();
            session.close();
            return true;
        }catch (Exception e){
            transaction.rollback();
            session.close();
            System.out.println(e);
            System.out.println("item deleting process failed");
            return false;
        }
    }

    public ObservableList<Item> getDetailsToTableView(){
        Transaction transaction = session.beginTransaction();

        List<Item> dataList = session.createQuery("FROM Item ", Item.class).list();

        transaction.commit();
        session.close();

        ObservableList<Item> observableList = FXCollections.observableArrayList(dataList);
        return observableList;
    }
}
