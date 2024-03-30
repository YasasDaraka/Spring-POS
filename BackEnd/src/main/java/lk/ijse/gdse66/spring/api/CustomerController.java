package lk.ijse.gdse66.spring.api;

import jakarta.validation.Valid;
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

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/getAll")
    public List<CustomerDTO>getAllCustomers(){
        return cusService.getAllCustomer();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/search/{id:C00-0*[1-9]\\d{0,2}}")
    public CustomerDTO getCustomer(@PathVariable("id") String id){
        return cusService.searchCustomer(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> saveCustomer(@Valid @ModelAttribute CustomerDTO dto){
        cusService.saveCustomer(dto);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Void> updateCustomer(@Valid @RequestBody CustomerDTO dto){
        cusService.updateCustomer(dto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(params = "cusId")
    public ResponseEntity<Void> deleteCustomer(@RequestParam("cusId") String cusId){
        cusService.deleteCustomer(cusId);
        return ResponseEntity.noContent().build();
    }

}
