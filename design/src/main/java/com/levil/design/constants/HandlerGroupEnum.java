package com.levil.design.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;


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
}
