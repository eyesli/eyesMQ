package com.levil.design.factory;

import com.levil.design.constants.OrderTypeEnum;
import com.levil.design.pojo.Big;
import org.springframework.stereotype.Service;

@Service
public class DefaultProcessFactory extends AbstractProcessFactory<Big> {


    @Override
    public OrderTypeEnum getOrderType() {
        return OrderTypeEnum.DEFAULT;
    }

    @Override
    public StructureTypeEnum getStructureType() {
        return StructureTypeEnum.LINKED_LIST;
    }
}
