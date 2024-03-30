package lk.ijse.gdse66.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ItemDTO {
    private String itmCode;
    private String itmName;
    private double itmPrice;
    private int itmQTY;
}
