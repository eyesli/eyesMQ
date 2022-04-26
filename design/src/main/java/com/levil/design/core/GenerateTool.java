package com.levil.design.core;

import com.levil.design.pojo.Big;
import org.springframework.stereotype.Component;

@Component
public class GenerateTool {
    public void generate(Big big){
        System.out.println("generateFile = " + big);
    }
}
