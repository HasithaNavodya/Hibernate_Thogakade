package lk.ijse.crud_using_hibernate.embedded;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class OrderDetailsId implements Serializable {
    @Column(name = "order_id")
    private String order_id;

    @Column(name = "item_id")
    private String item_code;

    public OrderDetailsId() {
    }

    public OrderDetailsId(String order_id, String item_id) {
        this.order_id = order_id;
        this.item_code = item_id;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getItem_id() {
        return item_code;
    }

    public void setItem_id(String item_id) {
        this.item_code = item_id;
    }
}
