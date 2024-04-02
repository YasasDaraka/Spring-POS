package lk.ijse.gdse66.spring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Customer {
    @Id
    private String id;
    private String name;
    private String address;
    @Column(columnDefinition = "LONGTEXT")
    private String proPic;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "customer")
    private List<Order> orders = new ArrayList<>();
}
