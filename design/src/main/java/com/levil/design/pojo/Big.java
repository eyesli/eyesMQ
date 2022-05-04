package com.levil.design.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
public class Big extends AbstractBuildBO {
    private Map<String,Object> map;
}
