package com.levil.design.factory;

import com.levil.design.core.constants.HandlerGroupEnum;
import com.levil.design.core.constants.HandlerTypeEnum;
import com.levil.design.core.constants.OrderTypeEnum;
import com.levil.design.pojo.Big;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class OT1ProcessFactory extends AbstractProcessFactory<Big> {


    @Override
    public Map<HandlerGroupEnum, HandlerTypeEnum> replacePipeLine() {
        LinkedHashMap<HandlerGroupEnum, HandlerTypeEnum> map = new LinkedHashMap<>();
        map.put(HandlerGroupEnum.REMIT_TO, HandlerTypeEnum.DEFAULT_6);
        return map;
    }

    @Override
    public OrderTypeEnum getOrderType() {
        return OrderTypeEnum.ONE;
    }

    @Override
    public StructureTypeEnum getStructureType() {
        return StructureTypeEnum.HASH_ORDER;
    }
}
