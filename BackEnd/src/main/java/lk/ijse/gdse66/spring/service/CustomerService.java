package lk.ijse.gdse66.spring.service;

import lk.ijse.gdse66.spring.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {
    List<CustomerDTO> getAllCustomer();
    CustomerDTO searchCustomer(String id);
    void saveCustomer(CustomerDTO dto);
    void updateCustomer(CustomerDTO dto);
    void deleteCustomer(String id);
}
