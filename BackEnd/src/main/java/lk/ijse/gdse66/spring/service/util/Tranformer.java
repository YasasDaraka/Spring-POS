package lk.ijse.gdse66.spring.service.util;

import lk.ijse.gdse66.spring.dto.CustomerDTO;
import lk.ijse.gdse66.spring.dto.ItemDTO;
import lk.ijse.gdse66.spring.dto.OrderDetailsDTO;
import lk.ijse.gdse66.spring.entity.Customer;
import lk.ijse.gdse66.spring.entity.Item;
import lk.ijse.gdse66.spring.entity.OrderDetails;
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

    public enum ClassType {
        CUS_DTO,
        CUS_ENTITY,
        CUS_DTO_LIST,
        CUS_ENTITY_LIST,
        ITEM_DTO,
        ITEM_ENTITY,
        ITEM_DTO_LIST,
        ITEM_ENTITY_LIST,
        ORDER_DETAILS_ENTITY,
        ORDER_DETAILS_DTO
    }

    public <R> R convert(Object from, ClassType to) {
        if (getType(to) instanceof OrderDetailsDTO) {
            return (R) toOrderDetailsDTO((OrderDetails) from);
        }
        if (getType(to) instanceof OrderDetails) {
            return (R) toOrderDetailsEntity((OrderDetailsDTO) from);
        }
        return (R) mapper.map(from, getType(to));

    }

    public OrderDetailsDTO toOrderDetailsDTO(OrderDetails orderDetail) {
        return mapper.typeMap(OrderDetails.class, OrderDetailsDTO.class)
                .addMapping(src -> src.getOrderDetailPK().getOid(), OrderDetailsDTO::setOid)
                .addMapping(src -> src.getItem().getItmCode(), OrderDetailsDTO::setItmCode)
                .addMapping(src -> src.getItem().getItmPrice(), OrderDetailsDTO::setItmPrice)
                .addMapping(src -> src.getItem().getItmQTY(), OrderDetailsDTO::setItmQTY)
                .map(orderDetail);
    }

    public OrderDetails toOrderDetailsEntity(OrderDetailsDTO detailsDTO) {
        return mapper.typeMap(OrderDetailsDTO.class, OrderDetails.class)
                .addMappings(mapper -> mapper.skip(OrderDetails::setOrderDetailPK))//skip detailPK
                .addMappings(mapper -> mapper.skip(OrderDetails::setOrder))//skip orders
                .addMappings(mapper -> mapper.skip(OrderDetails::setItem))//skip item
                .addMappings(mapper -> {
                    mapper.map(OrderDetailsDTO::getOid, (dest, value) -> dest.getOrder().setOid((String) value));
                    mapper.map(OrderDetailsDTO::getItmCode, (dest, value) -> dest.getItem().setItmCode((String) value));
                    mapper.map(OrderDetailsDTO::getItmPrice, (dest, value) -> dest.getItem().setItmPrice((double) value));
                })
                .addMapping(src -> src.getItmQTY(), OrderDetails::setItmQTY)
                .map(detailsDTO);
    }

    private Type getType(ClassType type) {
        switch (type) {
            case CUS_DTO:
                return CustomerDTO.class;
            case CUS_ENTITY:
                return Customer.class;
            case CUS_DTO_LIST:
                return new TypeToken<ArrayList<CustomerDTO>>() {
                }.getType();
            case CUS_ENTITY_LIST:
                return new TypeToken<ArrayList<Customer>>() {}.getType();
            case ITEM_DTO:
                return ItemDTO.class;
            case ITEM_ENTITY:
                return Item.class;
            case ITEM_DTO_LIST:
                return new TypeToken<ArrayList<ItemDTO>>() {}.getType();
            case ITEM_ENTITY_LIST:
                return new TypeToken<ArrayList<Item>>() {}.getType();
            case ORDER_DETAILS_DTO:
                return new TypeToken<OrderDetailsDTO>() {}.getType();
            case ORDER_DETAILS_ENTITY:
                return new TypeToken<OrderDetails>() {}.getType();
            default:
                throw new IllegalArgumentException("Unsupported ClassType: " + type);
        }
    }
}
