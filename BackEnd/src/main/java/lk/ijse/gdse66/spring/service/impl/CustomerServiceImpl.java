package lk.ijse.gdse66.spring.service.impl;

import lk.ijse.gdse66.spring.dto.CustomerDTO;
import lk.ijse.gdse66.spring.repository.CustomerRepo;
import lk.ijse.gdse66.spring.service.CustomerService;
import lk.ijse.gdse66.spring.service.exception.NotFoundException;
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
        return (CustomerDTO) customerRepo.findById(id)
                .map(cus -> tranformer.convert(cus, Tranformer.ClassType.CUS_DTO))
                .orElseThrow(() -> new RuntimeException("Customer Not Exist"));
    }

    @Override
    public void saveCustomer(CustomerDTO dto) {
        customerRepo.findById(dto.getId()).ifPresentOrElse(
                customer -> { throw new RuntimeException("Customer Already Exist"); },
                () -> customerRepo.save(tranformer.convert(dto, Tranformer.ClassType.CUS_ENTITY)));
    }

    @Override
    public void updateCustomer(CustomerDTO dto) {
        customerRepo.findById(dto.getId()).ifPresentOrElse(
                customer -> customerRepo.save(tranformer.convert(dto, Tranformer.ClassType.CUS_ENTITY)),
                () -> {throw new RuntimeException("Customer Not Exist");});
    }

    @Override
    public void deleteCustomer(String id) {
        customerRepo.findById(id).ifPresentOrElse(
                customer -> customerRepo.deleteById(id),
                ()-> {throw new NotFoundException("Customer Not Exist");}
        );
    }
}
