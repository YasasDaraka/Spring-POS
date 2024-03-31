package lk.ijse.gdse66.spring.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class OrderDetailPK implements Serializable {
    @Column(name = "oid")
    private String oid;
    @Column(name = "itmCode")
    private String itmCode;
}
