package lk.ijse.gdse66.spring.entity;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OrderDetails {
    @EmbeddedId
    private OrderDetailPK orderDetailPK;

    @Column(name = "itmQTY")
    private int itmQTY;

    @ManyToOne
    @JoinColumn(name = "oid", referencedColumnName = "oid", insertable = false, updatable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "itmCode", referencedColumnName = "itmCode", insertable = false, updatable = false)
    private Item item;

}

