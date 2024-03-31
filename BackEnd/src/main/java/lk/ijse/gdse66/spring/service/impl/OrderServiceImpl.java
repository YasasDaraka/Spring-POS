package lk.ijse.gdse66.spring.service.impl;

import lk.ijse.gdse66.spring.dto.OrderDTO;
import lk.ijse.gdse66.spring.repository.OrderRepo;
import lk.ijse.gdse66.spring.service.OrderService;
import lk.ijse.gdse66.spring.service.util.Tranformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepo orderRepo;
    @Autowired
    Tranformer tranformer;
    @Override
    public List<OrderDTO> getAllOrder() {
        return tranformer.toOrderDTOList(orderRepo.findAll());
    }

    @Override
    public OrderDTO searchOrder(String id) {
        return null;
    }

    @Override
    public void saveOrder(OrderDTO dto) {

    }
}
