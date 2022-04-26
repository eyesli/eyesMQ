package com.levil.design.service.Impl;

import com.levil.design.handler.BuildHandler;
import com.levil.design.pojo.Big;
import com.levil.design.service.GenerateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class GenerateServiceImpl implements GenerateService {

    @Autowired
    Map<String, BuildHandler> buildHandlerMap;

    @Override
    public void generate(Big big) {

        buildHandlerMap.values().parallelStream().forEach(e->e.build(big));

    }
}
