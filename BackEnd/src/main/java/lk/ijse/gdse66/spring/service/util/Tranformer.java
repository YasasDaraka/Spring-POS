package lk.ijse.gdse66.spring.service.util;

import lk.ijse.gdse66.spring.dto.CustomerDTO;
import lk.ijse.gdse66.spring.dto.ItemDTO;
import lk.ijse.gdse66.spring.entity.Customer;
import lk.ijse.gdse66.spring.entity.Item;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.ArrayList;


@Component
public class Tranformer {
    @Autowired
    private ModelMapper mapper;

    public enum ClassType{
        CUS_DTO,CUS_ENTITY,CUS_DTO_LIST,CUS_ENTITY_LIST,ITEM_DTO,ITEM_ENTITY,ITEM_DTO_LIST,ITEM_ENTITY_LIST,
    }

    public <R> R convert(Object from, ClassType to) {
        return (R) mapper.map(from, getType(to));
    }

    private Type getType(ClassType type) {
        switch (type) {
            case CUS_DTO:
                return CustomerDTO.class;
            case CUS_ENTITY:
                return Customer.class;
            case CUS_DTO_LIST:
                return new TypeToken<ArrayList<CustomerDTO>>() {}.getType();
            case CUS_ENTITY_LIST:
                return  new TypeToken<ArrayList<Customer>>() {}.getType();
            case ITEM_DTO:
                return ItemDTO.class;
            case ITEM_ENTITY:
                return Item.class;
            case ITEM_DTO_LIST:
                return new TypeToken<ArrayList<ItemDTO>>() {}.getType();
            case ITEM_ENTITY_LIST:
                return  new TypeToken<ArrayList<Item>>() {}.getType();
            default:
                throw new IllegalArgumentException("Unsupported ClassType: " + type);
        }
    }
}
