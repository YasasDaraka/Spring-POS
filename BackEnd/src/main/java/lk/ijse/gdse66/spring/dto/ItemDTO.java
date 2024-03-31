package lk.ijse.gdse66.spring.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
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

    @NotNull(message = "Item code is required")
    @Pattern(regexp = "I00-0*[1-9]\\d{0,2}", message = "Item Code is a required field: I00-")
    private String itmCode;
    @NotNull(message = "Item name is required")
    @Pattern(regexp = "[A-Za-z ]{5,}", message = "Item-Name is a required field: Minimum 5,Max 20,Spaces Allowed")
    private String itmName;
    @DecimalMin(value = "0.01", message = "Item price must be greater than 0")
    private double itmPrice;
    @Min(value = 1, message = "Item quantity must be at least 1")
    private int itmQTY;

}
