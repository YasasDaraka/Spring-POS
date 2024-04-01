package lk.ijse.gdse66.spring.service;


import lk.ijse.gdse66.spring.dto.OrderDetailsDTO;
import lk.ijse.gdse66.spring.entity.OrderDetails;

import java.util.List;

public interface OrderDetailService {
     List<OrderDetailsDTO> getAllOrderDetails();
}
