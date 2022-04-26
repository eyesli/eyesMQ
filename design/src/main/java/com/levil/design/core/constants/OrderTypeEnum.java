package com.levil.design.core.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum OrderTypeEnum {
    ONE(1,"orderType1"),
    TWO(2,"orderType2"),
    THREE(3,"orderType3"),
    FOUR(4,"orderType4");

    private final Integer type;
    private final String desc;

    public static List<OrderTypeEnum> getAllType(){
        return Stream.of(OrderTypeEnum.values()).collect(Collectors.toList());
    }
    public  OrderTypeEnum getByType(Integer type){
        for (OrderTypeEnum value : OrderTypeEnum.values()) {
            if (Objects.equals(value.getType(), type)) {
                return value;
            }
        }
        return null;
    }
}
