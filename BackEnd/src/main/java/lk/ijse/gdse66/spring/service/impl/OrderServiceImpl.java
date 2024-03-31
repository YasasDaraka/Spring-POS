package lk.ijse.gdse66.spring.service.impl;

import lk.ijse.gdse66.spring.dto.OrderDTO;
import lk.ijse.gdse66.spring.dto.OrderDetailsDTO;
import lk.ijse.gdse66.spring.entity.Item;
import lk.ijse.gdse66.spring.repository.ItemRepo;
import lk.ijse.gdse66.spring.repository.OrderRepo;
import lk.ijse.gdse66.spring.service.OrderService;
import lk.ijse.gdse66.spring.service.exception.DuplicateRecordException;
import lk.ijse.gdse66.spring.service.exception.NotFoundException;
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
    ItemRepo itemRepo;
    @Autowired
    Tranformer tranformer;

    @Override
    public List<OrderDTO> getAllOrder() {
        return tranformer.convert(orderRepo.findAll(), Tranformer.ClassType.ORDER_DTO_LIST);
    }

    @Override
    public OrderDTO searchOrder(String id) {
        return (OrderDTO) orderRepo.findById(id)
                .map(order -> tranformer.convert(order, Tranformer.ClassType.ORDER_DTO))
                .orElseThrow(() -> new NotFoundException("Order Not Exist"));

    }

    @Override
    @Transactional
    public void saveOrder(OrderDTO dto) {
        System.out.println(dto.toString());
        orderRepo.findById(dto.getOid()).ifPresentOrElse(
                order -> {
                    throw new DuplicateRecordException("Order Already Exist");
                },
                () -> {
                    orderRepo.save(tranformer.convert(dto, Tranformer.ClassType.ORDER_ENTITY));
                    for (OrderDetailsDTO dtos : dto.getOrderDetails()) {
                        Item item = itemRepo.findById(dtos.getItmCode())
                                .map(order -> {
                                    int current = order.getItmQTY();
                                    int ordered = dtos.getItmQTY();
                                    order.setItmQTY(current - ordered);
                                    return order;
                                })
                                .orElseThrow(() -> {
                                    System.out.println("Item not found");
                                    throw new NotFoundException("Item not found");
                                });
                        itemRepo.save(item);
                    }
                    System.out.println("order added");
                });
    }
}
