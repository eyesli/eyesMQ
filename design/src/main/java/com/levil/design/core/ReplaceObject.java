package com.levil.design.core;

import com.levil.design.core.constants.HandlerGroupEnum;
import com.levil.design.core.constants.HandlerTypeEnum;
import com.levil.design.core.constants.StructureTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedHashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReplaceObject{
    private StructureTypeEnum structureTypeEnum;
    private LinkedHashMap<HandlerGroupEnum,HandlerTypeEnum> map;

}
