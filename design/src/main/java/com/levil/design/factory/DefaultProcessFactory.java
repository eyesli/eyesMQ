package com.levil.design.factory;

import com.levil.design.core.ReplaceObject;
import com.levil.design.core.constants.HandlerGroupEnum;
import com.levil.design.core.constants.HandlerTypeEnum;
import com.levil.design.core.constants.OrderTypeEnum;
import com.levil.design.core.constants.StructureTypeEnum;
import com.levil.design.pojo.Big;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

@Service
public class DefaultProcessFactory extends AbstractProcessFactory<Big>{


    @Override
    public ReplaceObject replacePipeLine() {
        LinkedHashMap<HandlerGroupEnum,HandlerTypeEnum> map = new LinkedHashMap<>();
        map.put(HandlerGroupEnum.HEADER,HandlerTypeEnum.DEFAULT_1);
        return new ReplaceObject(StructureTypeEnum.LINKED_LIST,map);
    }

    @Override
    public OrderTypeEnum getOrderType() {
        return OrderTypeEnum.ONE;
    }
}
