package lk.ijse.gdse66.spring.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ItemDTO {

    @Pattern(regexp = "^I00-(0*[1-9]\\d{0,2})$", message = "Item code is not valid")
    private String itmCode;
    @Pattern(regexp = "^[A-Za-z ]{5,}$", message = "Item name is not valid")
    private String itmName;
    @DecimalMin(value = "0.01", message = "Item price must be greater than 0")
    private double itmPrice;
    @Min(value = 1, message = "Item quantity must be at least 1")
    private int itmQTY;

}
