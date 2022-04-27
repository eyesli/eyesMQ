package com.levil.design.handler.impl.ot1;

import com.levil.design.core.constants.HandlerTypeEnum;
import com.levil.design.handler.impl.common.FooterCommon;
import com.levil.design.pojo.Big;
import org.springframework.stereotype.Service;

@Service
public class OT1FooterImpl extends FooterCommon {



    @Override
    public void build(Big big) {
        System.out.println("OT1重写或者是增强= " + big);
    }
//
//    @Override
//    public OrderTypeEnum getOrderType() {
//        return OrderTypeEnum.ONE;
//    }

    @Override
    public HandlerTypeEnum getHandlerType() {
        /**
         *  TODO 这里考虑是不是 重写的是哪部分的。考虑通过type区分,不然只有开发者自己知道是build的是什么。
         *  重写哪部分的公共代码，在这里配置，这里好好考虑，或者用双层map。区分公共的和自定义的，这个方法
         */
        return HandlerTypeEnum.DEFAULT_5;
    }

}
