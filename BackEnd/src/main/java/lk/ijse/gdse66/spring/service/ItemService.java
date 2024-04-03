package lk.ijse.gdse66.spring.service;

import lk.ijse.gdse66.spring.dto.ItemDTO;
import java.util.List;

public interface ItemService {
    List<ItemDTO> getAllItem();
    ItemDTO searchItem(String id);
    void saveItem(ItemDTO dto);
    void updateItem(ItemDTO dto);
    void deleteItem(String id);
    String getItemGenId();
}
