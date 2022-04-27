package com.levil.design.handler.impl.common;

import org.springframework.stereotype.Service;

@Service
public class CommonBuildService {
    public void build(String s){
        System.out.println("s = " + s);
    }
}
