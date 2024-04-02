package lk.ijse.gdse66.spring.dto;

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
public class CustomerDTO {
    @NotNull(message = "Customer ID is required")
    @Pattern(regexp = "C00-0*[1-9]\\d{0,2}", message = "ID is not valid")
    private String id;
    @NotNull(message = "Customer name is required")
    @Pattern(regexp = "[A-Za-z ]{5,}", message = "Name is not valid")
    private String name;
    @NotNull(message = "Customer address is required")
    @Pattern(regexp = "[A-Za-z0-9 ]{5,}", message = "Address is not valid")
    private String address;
    private String proPic;
}
