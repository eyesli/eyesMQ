package com.levil.design.factory;

import com.levil.design.core.constants.HandlerGroupEnum;
import com.levil.design.core.constants.HandlerTypeEnum;
import com.levil.design.core.constants.OrderTypeEnum;
import com.levil.design.pojo.Big;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class DefaultProcessFactory extends AbstractProcessFactory<Big>{


    @Override
    public Map<HandlerGroupEnum,HandlerTypeEnum> replacePipeLine() {
        return new LinkedHashMap<>();
    }

    @Override
    public OrderTypeEnum getOrderType() {
        return OrderTypeEnum.ONE;
    }
}
