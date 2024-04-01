package lk.ijse.gdse66.spring.api;

import jakarta.validation.Valid;
import lk.ijse.gdse66.spring.dto.OrderDTO;
import lk.ijse.gdse66.spring.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public List<OrderDTO> getAllOrder(){
        List<OrderDTO> allOrder = orderService.getAllOrder();
        for (OrderDTO orderDTO : allOrder) {
            System.out.println(orderDTO);
        }
        return orderService.getAllOrder();
    }
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/search/{id}")
    public OrderDTO searchOrder(@PathVariable("id") String id) {
        System.out.println(id);
        System.out.println(orderService.searchOrder(id).toString());
        return orderService.searchOrder(id);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> saveOrder(@Valid @RequestBody OrderDTO dto) {
        orderService.saveOrder(dto);
        return ResponseEntity.ok().build();
    }

}
