package com.levil.design.core.constants;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HandlerType {

    //handler类型
    private HandlerTypeEnum handlerTypeEnum;
    //是否是方法重写
    private Boolean isOverride;
    //orderType
    private Integer orderType;
    //在哪个流程 巴西，美国，加拿大
    private processEnum process;

}
