package lk.ijse.gdse66.spring.dto;

import jakarta.validation.constraints.Pattern;
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

    @Pattern(regexp = "OID-0*[1-9]\\d{0,2}", message = "Order ID is not valid")
    private String oid;
    private LocalDate date;
    @Pattern(regexp = "C00-0*[1-9]\\d{0,2}", message = "Customer ID is not valid")
    private String cusID;
    List<OrderDetailsDTO> orderDetails;

    public OrderDTO(String oid, LocalDate date, String cusID) {
        this.oid = oid;
        this.date = date;
        this.cusID = cusID;
    }

}
