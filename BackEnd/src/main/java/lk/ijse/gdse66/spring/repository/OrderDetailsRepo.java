package lk.ijse.gdse66.spring.repository;

import lk.ijse.gdse66.spring.entity.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderDetailsRepo extends JpaRepository<OrderDetails,String> {

    @Query(value = "SELECT * FROM OrderDetails ORDER BY CAST(SUBSTRING(oid, 5) AS SIGNED), SUBSTRING(oid, 1, 4)",nativeQuery = true)
    List<OrderDetails> getAllOrderDetails();
}
