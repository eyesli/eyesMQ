package com.levil.design.service.Impl;

import com.levil.design.factory.BuildStorage;
import com.levil.design.factory.ProcessFactory;
import com.levil.design.pojo.Big;
import com.levil.design.service.GenerateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenerateServiceImpl implements GenerateService {


    @Autowired
    BuildStorage<Big> bigBuildStorage;
    @Override
    public void generate(Big big) {
        Big data = new Big();
        data.setOrderType(1);

        ProcessFactory<Big> orderTypeFactory = bigBuildStorage.getOrderTypeFactory(data);
        orderTypeFactory.build(big);
    }
}
