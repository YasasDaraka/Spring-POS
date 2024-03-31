package lk.ijse.gdse66.spring.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @Column(name = "oid")
    private String oid;

    @Column(name = "date")
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "cusID", nullable = false)
    private Customer customer;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "order")
    private List<OrderDetails> orderDetails = new ArrayList<>();

    /*public OrderDTO toDTO() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOid(this.oid);
        orderDTO.setDate(this.date);
        orderDTO.setCusID(this.customer.getCusID());
        return orderDTO;
    }*/

}
