package com.levil.design.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Type {
    ONE(1,"orderType1"),
    TWO(2,"orderType2"),
    THREE(3,"orderType3"),
    FOUR(4,"orderType4");

    private final Integer type;
    private final String desc;
}
