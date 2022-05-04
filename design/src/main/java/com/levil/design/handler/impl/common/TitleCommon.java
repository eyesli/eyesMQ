package com.levil.design.handler.impl.common;

import com.levil.design.core.constants.HandlerTypeEnum;
import com.levil.design.handler.impl.abstra.AbstractBuildHandler;
import com.levil.design.pojo.Big;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class TitleCommon extends AbstractBuildHandler<Big> {
    @Override
    public void build(Big big) {
        big.setMap(new HashMap<String, Object>() {
            {
                put("TitleCommon", HandlerTypeEnum.DEFAULT_3);
            }
        });
    }

    @Override
    public HandlerTypeEnum getHandlerType() {
        return HandlerTypeEnum.DEFAULT_3;
    }

    @Override
    public boolean isAsync() {
        return false;
    }
}
