package lk.ijse.gdse66.spring.api;

import lk.ijse.gdse66.spring.dto.CustomerDTO;
import lk.ijse.gdse66.spring.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@CrossOrigin
public class CustomerController {
    public CustomerController() {
        System.out.println("CustomerController");
    }
    @Autowired
    CustomerService cusService;
    @GetMapping(path = "/getAll")
    public List<CustomerDTO>getAllCustomers(){
        return cusService.getAllCustomer();
    }
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/search/{id}")
    public CustomerDTO getCustomer(@PathVariable("id") String id){
        return cusService.searchCustomer(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<Void> saveCustomer(@ModelAttribute CustomerDTO dto){
        cusService.saveCustomer(dto);
        return ResponseEntity.ok().build();
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
