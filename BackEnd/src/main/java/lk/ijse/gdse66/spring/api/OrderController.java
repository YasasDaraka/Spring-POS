package lk.ijse.gdse66.spring.api;

import lk.ijse.gdse66.spring.dto.OrderDTO;
import lk.ijse.gdse66.spring.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/order")
@CrossOrigin
public class OrderController {
    public OrderController() {
        System.out.println("OrderController");
    }
    @Autowired
    OrderService orderService;
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/getAll")
    public List<OrderDTO> getAllOrder() throws SQLException, ClassNotFoundException {
        List<OrderDTO> allOrder = orderService.getAllOrder();
        for (OrderDTO orderDTO : allOrder) {
            System.out.println(orderDTO);
        }
        return orderService.getAllOrder();
    }

    public ResponseEntity<Void> saveOrder(OrderDTO dto) {
        return null;
    }

    public OrderDTO searchOrder(String id) {
        return null;
    }

}
