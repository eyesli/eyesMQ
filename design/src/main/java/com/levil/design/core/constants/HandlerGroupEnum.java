package com.levil.design.core.constants;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

import static com.levil.design.core.constants.HandlerTypeEnum.DEFAULT_1;
import static com.levil.design.core.constants.HandlerTypeEnum.DEFAULT_16;
import static com.levil.design.core.constants.HandlerTypeEnum.DEFAULT_2;
import static com.levil.design.core.constants.HandlerTypeEnum.DEFAULT_3;
import static com.levil.design.core.constants.HandlerTypeEnum.DEFAULT_4;
import static com.levil.design.core.constants.HandlerTypeEnum.DEFAULT_5;
import static com.levil.design.core.constants.HandlerTypeEnum.DEFAULT_6;
import static com.levil.design.core.constants.HandlerTypeEnum.DEFAULT_7;
import static com.levil.design.core.constants.HandlerTypeEnum.DEFAULT_8;

@Getter
@AllArgsConstructor
public enum HandlerGroupEnum {

    //虽然可能是巴西的系统，但是在流程上可以用美国的。3层嵌套，感觉有点多了  流程->组>细节实现。就定义组的概念吧，代码里自己组装流程
    HEADER("HEADER"),
    LOGO("LOGO"),
    BILL_TO("BILL_TO"),
    REMIT_TO("REMIT_TO"),
    ADDRESS("ADDRESS");
    private final String desc;

    //这个模块有哪些实现
    public static List<HandlerTypeEnum> getHandlerTypeEnumList(HandlerGroupEnum groupEnum){
        switch (groupEnum){
            case HEADER:
                return Lists.newArrayList(DEFAULT_1, DEFAULT_2);
            case LOGO:
                return Lists.newArrayList(DEFAULT_3, DEFAULT_4);
            case BILL_TO:
                return Lists.newArrayList(DEFAULT_5,DEFAULT_6);
            case REMIT_TO:
                return Lists.newArrayList(DEFAULT_7,DEFAULT_8);
            case ADDRESS:
                return Lists.newArrayList(DEFAULT_16);
            default:
                return null;
        }
    }

}
