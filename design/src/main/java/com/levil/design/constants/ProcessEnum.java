package com.levil.design.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProcessEnum {
    COMMON(0, "公共"),
    BRAZIL(1, "巴西流程"),
    USA(2, "美国流程"),
    Canada(3, "加拿大流程");
    private final Integer type;
    private final String desc;
}
