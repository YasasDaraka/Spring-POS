package lk.ijse.gdse66.spring.api;

import lk.ijse.gdse66.spring.dto.CustomerDTO;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/customer")
@CrossOrigin
public class CustomerController {
    public CustomerController() {
        System.out.println("CustomerController");
    }

    @GetMapping(path = "/getAll")
    public List<CustomerDTO>getAllCustomer(){
        ArrayList<CustomerDTO> customers = new ArrayList<CustomerDTO>();
        return customers;
    }
}
