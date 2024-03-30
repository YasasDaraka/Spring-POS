package lk.ijse.gdse66.spring.service.impl;

import lk.ijse.gdse66.spring.dto.CustomerDTO;
import lk.ijse.gdse66.spring.dto.ItemDTO;
import lk.ijse.gdse66.spring.repository.ItemRepo;
import lk.ijse.gdse66.spring.service.ItemService;
import lk.ijse.gdse66.spring.service.util.Tranformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class ItemServiceImpl implements ItemService {
    @Autowired
    ItemRepo itemRepo;
    @Autowired
    Tranformer tranformer;
    @Override
    public List<ItemDTO> getAllItem() {
        return tranformer.convert(itemRepo.findAll(), Tranformer.ClassType.ITEM_DTO_LIST);
    }

    @Override
    public ItemDTO searchItem(String id) {
        return (ItemDTO) itemRepo.findById(id)
                .map(itm -> tranformer.convert(itm, Tranformer.ClassType.ITEM_DTO))
                .orElseThrow(() -> new RuntimeException("Item Not Exist"));
    }

    @Override
    public void saveItem(ItemDTO dto) {
        itemRepo.findById(dto.getItmCode()).ifPresentOrElse(
                item -> { throw new RuntimeException("Item Already Exist"); },
                () -> itemRepo.save(tranformer.convert(dto, Tranformer.ClassType.ITEM_ENTITY)));
    }

    @Override
    public void updateItem(ItemDTO dto) {

    }

    @Override
    public void deleteItem(String id) {

    }
}
