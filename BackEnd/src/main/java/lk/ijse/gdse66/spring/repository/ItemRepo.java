package lk.ijse.gdse66.spring.repository;

import lk.ijse.gdse66.spring.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ItemRepo extends JpaRepository<Item,String> {
    @Query(value = "SELECT CAST(SUBSTRING(itmCode, 5) AS SIGNED) AS id FROM Item ORDER BY id DESC LIMIT 1",nativeQuery = true)
    String getItemId();
}
