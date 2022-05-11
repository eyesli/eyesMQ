package com.levil.design.core.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AsyncModeEnum {
    RUN_ASYNC("异步无返回值"){
        @Override
        public Object asyncMethod(Object o) {
            return null;
        }
    },
    SUPPLY_ASYNC("异步有返回值") {
        @Override
        public Object asyncMethod(Object o) {
            return null;
        }
    };
    private final String desc;

    public abstract Object asyncMethod(Object o);

}
