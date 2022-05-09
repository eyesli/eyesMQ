package com.levil.design.core.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StructureTypeEnum {
    LINKED_LIST("链表"),
    ARRAY("数组");

    private final String desc;
}
