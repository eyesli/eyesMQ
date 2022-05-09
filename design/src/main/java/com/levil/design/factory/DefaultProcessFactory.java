package com.levil.design.factory;

import com.levil.design.core.constants.HandlerGroupEnum;
import com.levil.design.core.constants.HandlerTypeEnum;
import com.levil.design.core.constants.OrderTypeEnum;
import com.levil.design.pojo.Big;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

@Service
public class DefaultProcessFactory extends AbstractProcessFactory<Big>{


    @Override
    public LinkedHashMap<HandlerGroupEnum,HandlerTypeEnum> replacePipeLine() {
        LinkedHashMap<HandlerGroupEnum,HandlerTypeEnum> map = new LinkedHashMap<>();
        map.put(HandlerGroupEnum.HEADER,HandlerTypeEnum.DEFAULT_1);
        return map;
    }

    @Override
    public OrderTypeEnum getOrderType() {
        return OrderTypeEnum.ONE;
    }

    @Override
    public StructureTypeEnum getStructureType() {
        return StructureTypeEnum.LINKED_LIST;
    }
}
