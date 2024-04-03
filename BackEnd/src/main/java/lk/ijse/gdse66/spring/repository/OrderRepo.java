package lk.ijse.gdse66.spring.repository;

import lk.ijse.gdse66.spring.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepo extends JpaRepository<Order,String> {
    @Query(value = "SELECT CAST(SUBSTRING(oid, 5) AS SIGNED) AS id FROM Orders ORDER BY id DESC LIMIT 1",nativeQuery = true)
    String getOrderId();
}
