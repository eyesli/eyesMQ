package com.levil.design.constants;

import com.levil.design.handler.BuildHandler;
import com.levil.design.handler.impl.abstra.AbstractBuildHandler;
import com.levil.design.handler.impl.common.*;
import com.levil.design.handler.impl.ot1.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum HandlerTypeEnum {

    /**
     * 假设一个默认的公共的构建有这些流程 要build这么多东西，实际可能更多一点
     */
    DEFAULT_1(1, TopCommon.class, HandlerGroupEnum.LOGO, "默认模块构建1,比如是top") {
    },
    DEFAULT_2(2, FooterCommon.class, HandlerGroupEnum.HEADER, "默认模块构建2,比如是footer"),

    DEFAULT_3(3, TitleCommon.class, HandlerGroupEnum.BILL_TO, "默认模块构建3，比如是title"),
    DEFAULT_4(4, OT1AddressImpl.class, HandlerGroupEnum.ADDRESS, "默认模块构建4，比如是address"),
    DEFAULT_5(5, RemitToCommon.class, HandlerGroupEnum.REMIT_TO, "默认模块构建5"),
    DEFAULT_6(6, OT1FooterImpl.class, HandlerGroupEnum.REMIT_TO, "默认模块构建5");
    //    DEFAULT_6(6, FooterCommon.class, HandlerGroupEnum.LOGO, "默认模块构建5"),
//    DEFAULT_7(7, FooterCommon.class, HandlerGroupEnum.LOGO, "默认模块构建5"),
//    DEFAULT_8(8, FooterCommon.class, HandlerGroupEnum.LOGO, "默认模块构建5"),
//    DEFAULT_16(16, FooterCommon.class, HandlerGroupEnum.LOGO, "默认模块构建16");
    //类型递增
    private final Integer type;
    //枚举对应的类
    private final Class<? extends BuildHandler> aClass;
    //枚举在哪个组，多个枚举构成一个组。就算是重写了，我也要知道你重写的是哪部分内容。方便管理
    private final HandlerGroupEnum handlerGroupEnum;
    //描述
    private final String desc;

    public static List<Integer> getAllHandlerType() {
        return Stream.of(HandlerTypeEnum.values()).map(HandlerTypeEnum::getType).collect(Collectors.toList());
    }
}
