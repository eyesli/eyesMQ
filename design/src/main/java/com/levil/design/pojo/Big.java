package com.levil.design.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@EqualsAndHashCode(callSuper = true)
@Data
public class Big extends AbstractBuildBO {
    private String string1;
    private String string2;
    private String string3;
    private String string4;
    private String string5;
    private String string6;
}
