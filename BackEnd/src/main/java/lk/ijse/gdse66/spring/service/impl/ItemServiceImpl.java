package lk.ijse.gdse66.spring.service.impl;

import lk.ijse.gdse66.spring.dto.ItemDTO;
import lk.ijse.gdse66.spring.repository.ItemRepo;
import lk.ijse.gdse66.spring.service.ItemService;
import lk.ijse.gdse66.spring.service.exception.DuplicateRecordException;
import lk.ijse.gdse66.spring.service.exception.NotFoundException;
import lk.ijse.gdse66.spring.service.util.IdGenerator;
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
    @Autowired
    IdGenerator generator;
    @Override
    public List<ItemDTO> getAllItem() {
        return tranformer.convert(itemRepo.findAll(), Tranformer.ClassType.ITEM_DTO_LIST);
    }

    @Override
    public ItemDTO searchItem(String id) {
        return (ItemDTO) itemRepo.findById(id)
                .map(itm -> tranformer.convert(itm, Tranformer.ClassType.ITEM_DTO))
                .orElseThrow(() -> new NotFoundException("Item Not Exist"));
    }

    @Override
    public void saveItem(ItemDTO dto) {
        itemRepo.findById(dto.getItmCode()).ifPresentOrElse(
                item -> { throw new DuplicateRecordException("Item Already Exist"); },
                () -> itemRepo.save(tranformer.convert(dto, Tranformer.ClassType.ITEM_ENTITY)));
    }

    @Override
    public void updateItem(ItemDTO dto) {
        itemRepo.findById(dto.getItmCode()).ifPresentOrElse(
                item -> itemRepo.save(tranformer.convert(dto, Tranformer.ClassType.ITEM_ENTITY)),
                () -> {throw new NotFoundException("Item Not Exist");});
    }

    @Override
    public void deleteItem(String id) {
        itemRepo.findById(id).ifPresentOrElse(
                item -> itemRepo.deleteById(id),
                ()-> {throw new NotFoundException("Item Not Exist");}
        );
    }
    @Override
    public String getItemGenId() {
        return generator.getGenerateID(itemRepo.getItemId(), IdGenerator.GenerateTypes.ITEM);
    }
}
