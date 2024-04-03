package lk.ijse.gdse66.spring.repository;

import lk.ijse.gdse66.spring.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerRepo extends JpaRepository<Customer,String> {
    @Query(value = "SELECT CAST(SUBSTRING(id, 5) AS SIGNED) AS cusid FROM Customer ORDER BY cusid DESC LIMIT 1",nativeQuery = true)
    String getCusId();
}
