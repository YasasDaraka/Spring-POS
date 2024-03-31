package lk.ijse.gdse66.spring.api;

import lk.ijse.gdse66.spring.dto.OrderDTO;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.ArrayList;
@RestController
@RequestMapping("/item")
@CrossOrigin
public class OrderController {

    public boolean saveOrder(OrderDTO dto)  {
    }

    public OrderDTO searchOrder(String id) {

    }

    public ArrayList<OrderDTO> getAllOrder() throws SQLException, ClassNotFoundException {
    }

}
