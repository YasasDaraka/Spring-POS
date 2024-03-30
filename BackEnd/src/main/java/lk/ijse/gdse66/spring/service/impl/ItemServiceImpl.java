package lk.ijse.gdse66.spring.service.impl;

import lk.ijse.gdse66.spring.dto.ItemDTO;
import lk.ijse.gdse66.spring.repository.ItemRepo;
import lk.ijse.gdse66.spring.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class ItemServiceImpl implements ItemService {
    @Autowired
    ItemRepo itemRepo;
    @Override
    public List<ItemDTO> getAllItem() {
        return null;
    }

    @Override
    public ItemDTO searchItem(String id) {
        return null;
    }

    @Override
    public void saveItem(ItemDTO dto) {

    }

    @Override
    public void updateItem(ItemDTO dto) {

    }

    @Override
    public void deleteItem(String id) {

    }
}
