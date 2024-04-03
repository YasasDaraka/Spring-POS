package lk.ijse.gdse66.spring.service;

import lk.ijse.gdse66.spring.dto.OrderDTO;
import java.util.List;

public interface OrderService {
    List<OrderDTO> getAllOrder();
    OrderDTO searchOrder(String id);
    void saveOrder(OrderDTO dto);
    String getOrderGenId();
}
