package lk.ijse.crud_using_hibernate.entity;

import lk.ijse.crud_using_hibernate.embedded.OrderDetailsId;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "order_details")
public class OrderDetails{
    @EmbeddedId
    private OrderDetailsId id = new OrderDetailsId();

    @ManyToOne
    @MapsId("order_id")
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @MapsId("item_code")
    @JoinColumn(name = "item_code")
    private Item item;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "unit_price")
    private double unit_price;

    public OrderDetails() {
    }

    public OrderDetails(OrderDetailsId id, Order order, Item item, int quantity, double unit_price) {
        this.id = id;
        this.order = order;
        this.item = item;
        this.quantity = quantity;
        this.unit_price = unit_price;
    }

    public OrderDetailsId getId() {
        return id;
    }

    public void setId(OrderDetailsId id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(double unit_price) {
        this.unit_price = unit_price;
    }
}

