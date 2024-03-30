package lk.ijse.gdse66.spring.repository;

import lk.ijse.gdse66.spring.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepo extends JpaRepository<Item,String> {
}
