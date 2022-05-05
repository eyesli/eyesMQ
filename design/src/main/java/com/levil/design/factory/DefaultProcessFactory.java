package com.levil.design.factory;

import com.google.common.collect.Lists;
import com.levil.design.core.constants.HandlerTypeEnum;
import com.levil.design.core.constants.OrderTypeEnum;
import com.levil.design.pojo.Big;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultProcessFactory extends AbstractProcessFactory<Big>{


    @Override
    public List<HandlerTypeEnum> pipelineCreate() {
        return Lists.newArrayList(HandlerTypeEnum.DEFAULT_1,HandlerTypeEnum.DEFAULT_2);
    }


    @Override
    public OrderTypeEnum getOrderType() {
        return OrderTypeEnum.ONE;
    }
}
