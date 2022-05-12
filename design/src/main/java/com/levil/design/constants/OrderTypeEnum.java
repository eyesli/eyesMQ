package com.levil.design.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum OrderTypeEnum {
    DEFAULT(-1, "没有指定的都走默认"),
    ZERO(0, "orderType0"),
    ONE(1, "orderType1"),
    TWO(2, "orderType2"),
    THREE(3, "orderType3"),
    FOUR(4, "orderType4");

    private final Integer type;
    private final String desc;

    public static List<OrderTypeEnum> getAllTypeList() {
        return Stream.of(OrderTypeEnum.values()).collect(Collectors.toList());
    }

    public static Map<Integer, OrderTypeEnum> getAllTypeMap() {
        return Arrays.stream(OrderTypeEnum.values()).collect(Collectors.toMap(OrderTypeEnum::getType, map -> map));
    }

    public static OrderTypeEnum getByType(Integer type) {
        OrderTypeEnum orderTypeEnum = getAllTypeMap().get(type);
        if (orderTypeEnum == null) {
            return DEFAULT;
        }
        return orderTypeEnum;
    }
}
