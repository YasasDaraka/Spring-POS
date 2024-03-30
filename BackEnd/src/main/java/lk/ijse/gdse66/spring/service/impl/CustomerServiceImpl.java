package lk.ijse.gdse66.spring.service.impl;

import lk.ijse.gdse66.spring.dto.CustomerDTO;
import lk.ijse.gdse66.spring.repository.CustomerRepo;
import lk.ijse.gdse66.spring.service.CustomerService;
import lk.ijse.gdse66.spring.service.util.Tranformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepo customerRepo;
    @Autowired
    Tranformer tranformer;
    @Override
    public List<CustomerDTO> getAllCustomer() {
        return tranformer.convert(customerRepo.findAll(), Tranformer.ClassType.CUS_DTO_LIST);
    }

    @Override
    public CustomerDTO searchCustomer(String id) {
        return null;
    }

    @Override
    public void saveCustomer(CustomerDTO dto) {

    }

    @Override
    public void updateCustomer(CustomerDTO dto) {

    }

    @Override
    public void deleteCustomer(String id) {

    }
}
