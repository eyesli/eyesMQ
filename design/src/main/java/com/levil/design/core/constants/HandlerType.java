package com.levil.design.core.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum HandlerType {

    DEFAULT_TOP(1,"TOP"),
    DEFAULT_FOOTER(2,"FOOTER"),
    CUSTOM_TOP(3,"自定义TOP"),
    CUSTOM_FOOTER(4,"自定义FOOTER");

    private final Integer type;
    private final String desc;
}
