package com.levil.design.service.Impl;

import com.levil.design.pojo.Big;
import com.levil.design.service.GenerateService;
import org.springframework.stereotype.Service;

@Service
public class GenerateServiceImpl implements GenerateService {

    @Override
    public void generate(Big big) {
        System.out.println("big = " + big);
    }
}
