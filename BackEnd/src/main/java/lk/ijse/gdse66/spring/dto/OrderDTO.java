package lk.ijse.gdse66.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private String oid;
    private LocalDate date;
    private String cusID;
    List<OrderDetailsDTO> orderDetails;

    public OrderDTO(String oid, LocalDate date, String cusID) {
        this.oid = oid;
        this.date = date;
        this.cusID = cusID;
    }

}
