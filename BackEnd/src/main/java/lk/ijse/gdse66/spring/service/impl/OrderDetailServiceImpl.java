package lk.ijse.gdse66.spring.service.impl;

import lk.ijse.gdse66.spring.dto.OrderDetailsDTO;
import lk.ijse.gdse66.spring.repository.OrderDetailsRepo;
import lk.ijse.gdse66.spring.service.OrderDetailService;
import lk.ijse.gdse66.spring.service.util.Tranformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class OrderDetailServiceImpl implements OrderDetailService {
    @Autowired
    OrderDetailsRepo detailsRepo;
    @Autowired
    Tranformer tranformer;

    @Override
    public List<OrderDetailsDTO> getAllOrderDetails() {
        return tranformer.convert(detailsRepo.getAllOrderDetails(), Tranformer.ClassType.ORDER_DETAILS_DTO_LIST);
    }
}
