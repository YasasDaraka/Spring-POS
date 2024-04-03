package lk.ijse.gdse66.spring.api;

import jakarta.validation.Valid;
import lk.ijse.gdse66.spring.dto.CustomerDTO;
import lk.ijse.gdse66.spring.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public List<CustomerDTO> getAllCustomers() {
        return cusService.getAllCustomer();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/search/{id:C00-0*[1-9]\\d{0,2}}")
    public CustomerDTO getCustomer(@PathVariable("id") String id) {
        return cusService.searchCustomer(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/getGenId")
    public String getCustomerGenId() {
        return cusService.getCustomerGenId();
    }

    /*@PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> saveCustomer(@Valid @ModelAttribute CustomerDTO dto){
        cusService.saveCustomer(dto);
        return ResponseEntity.ok().build();
    }*/
    /*@SneakyThrows
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> saveCustomer(@RequestPart("file") String file, @RequestPart("dto")@ModelAttribute CustomerDTO dto) {
        System.out.println("Received image file: " + file);
        System.out.println("Received customer data: " + dto);
        String base64Data = file.substring(file.indexOf(",") + 1);

        byte[] imageData = Base64.getDecoder().decode(base64Data);
        String uploadsDirectory = "E:\\Spring Project\\BackEnd\\src\\main\\resources";
        String filename = "uploaded_image.png";
        String filePath = uploadsDirectory + File.separator + filename;
        FileUtils.writeByteArrayToFile(new File(filePath), imageData);
        return ResponseEntity.ok().build();
    }*/
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> saveCustomer(@Valid @ModelAttribute CustomerDTO dto) {
        System.out.println("Received customer data: " + dto.toString());
        cusService.saveCustomer(dto);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Void> updateCustomer(@Valid @RequestBody CustomerDTO dto) {
        System.out.println("Received customer data: " + dto.toString());
        cusService.updateCustomer(dto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(params = "cusId")
    public ResponseEntity<Void> deleteCustomer(@RequestParam("cusId") String cusId) {
        cusService.deleteCustomer(cusId);
        return ResponseEntity.noContent().build();
    }

}
