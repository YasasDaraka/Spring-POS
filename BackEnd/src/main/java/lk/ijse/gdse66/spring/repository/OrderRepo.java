package lk.ijse.gdse66.spring.repository;

import lk.ijse.gdse66.spring.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order,String> {
}
