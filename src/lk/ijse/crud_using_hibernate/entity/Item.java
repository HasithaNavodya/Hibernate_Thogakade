package lk.ijse.crud_using_hibernate.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "item")
public class Item {

    @Id
    @Column(name = "item_code")
    private String item_code;
    @Column(name = "item_description")
    private String description;
    @Column(name = "item_unit_price")
    private double unit_price;
    @Column(name = "item_qty")
    private int item_quantity;

    public Item() {
    }

    public Item(String item_code, String description, double unit_price, int item_quantity) {
        this.item_code = item_code;
        this.description = description;
        this.unit_price = unit_price;
        this.item_quantity = item_quantity;
    }

    public String getItem_code() {
        return item_code;
    }

    public void setItem_code(String item_code) {
        this.item_code = item_code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(double unit_price) {
        this.unit_price = unit_price;
    }

    public int getItem_quantity() {
        return item_quantity;
    }

    public void setItem_quantity(int item_quantity) {
        this.item_quantity = item_quantity;
    }

    @Override
    public String toString() {
        return "Item{" +
                "item_code='" + item_code + '\'' +
                ", description='" + description + '\'' +
                ", unit_price=" + unit_price +
                ", item_quantity=" + item_quantity +
                '}';
    }
}
