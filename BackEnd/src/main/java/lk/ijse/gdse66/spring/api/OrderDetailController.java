package lk.ijse.gdse66.spring.api;

import lk.ijse.gdse66.spring.dto.OrderDetailsDTO;
import lk.ijse.gdse66.spring.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/orderDetail")
@CrossOrigin
public class OrderDetailController {
    @Autowired
    OrderDetailService orderDetailService;
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/getAll")
    public List<OrderDetailsDTO> getAllOrder(){
        List<OrderDetailsDTO> allOrder = orderDetailService.getAllOrderDetails();
        for (OrderDetailsDTO orderDTO : allOrder) {
            System.out.println(orderDTO);
        }
        return orderDetailService.getAllOrderDetails();
    }
}
