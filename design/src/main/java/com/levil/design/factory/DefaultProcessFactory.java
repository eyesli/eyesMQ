package com.levil.design.factory;

import com.levil.design.core.constants.HandlerGroupEnum;
import com.levil.design.core.constants.HandlerTypeEnum;
import com.levil.design.core.constants.OrderTypeEnum;
import com.levil.design.pojo.Big;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class DefaultProcessFactory extends AbstractProcessFactory<Big>{


    @Override
    public Map<HandlerGroupEnum,HandlerTypeEnum> pipeLine() {
        return super.pipeLine();
    }


    @Override
    public OrderTypeEnum getOrderType() {
        return OrderTypeEnum.ONE;
    }
}
