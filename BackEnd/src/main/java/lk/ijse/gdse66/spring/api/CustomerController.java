package lk.ijse.gdse66.spring.api;

import lk.ijse.gdse66.spring.dto.CustomerDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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
    public List<CustomerDTO>getAllCustomers(){
        ArrayList<CustomerDTO> customers = new ArrayList<CustomerDTO>();
        return customers;
    }
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/search/{id}")
    public String getCustomer(@PathVariable("id") String id){
        return id;
    }

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public CustomerDTO saveCustomer(@ModelAttribute CustomerDTO dto){
        return dto;
    }

    @PutMapping
    public CustomerDTO updateCustomer(@RequestBody CustomerDTO dto){
        return dto;
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(params = "cusId")
    public String deleteCustomer(@PathVariable("cusId") String cusId){
        return cusId;
    }

}
