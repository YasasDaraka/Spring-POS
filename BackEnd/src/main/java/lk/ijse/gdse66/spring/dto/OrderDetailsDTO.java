package lk.ijse.gdse66.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailsDTO {

    private String oid;
    private String itmCode;
    private int itmQTY;
    private double itmPrice;

}
