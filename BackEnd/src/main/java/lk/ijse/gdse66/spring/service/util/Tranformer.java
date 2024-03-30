package lk.ijse.gdse66.spring.service.util;

import lk.ijse.gdse66.spring.dto.CustomerDTO;
import lk.ijse.gdse66.spring.entity.Customer;
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
        CUS_DTO,CUS_ENTITY,CUS_DTO_LIST,CUS_ENTITY_LIST
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
            default:
                throw new IllegalArgumentException("Unsupported ClassType: " + type);
        }
    }
}
