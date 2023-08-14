package lk.ijse.crud_using_hibernate.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @Column(name = "order_id")
    private String id;
    @Column(name = "order_date")
    private LocalDate date;
  /*  @Column(name = "customer_id")
    private String customer_id;*/
    @ManyToOne
    @JoinColumn(name = "cust_id")
    private Customer customer;

    public Order() {
    }

    public Order(String id, LocalDate date, Customer customer) {
        this.id = id;
        this.date = date;
        this.customer = customer;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", date=" + date +
                ", customer=" + customer +
                '}';
    }
}
