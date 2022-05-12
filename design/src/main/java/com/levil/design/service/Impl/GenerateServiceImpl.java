package com.levil.design.service.Impl;

import com.levil.design.core.BuildStorage;
import com.levil.design.factory.ProcessFactory;
import com.levil.design.pojo.AbstractBuildBO;
import com.levil.design.pojo.Big;
import com.levil.design.service.GenerateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenerateServiceImpl<T extends AbstractBuildBO> implements GenerateService<T> {


    @Autowired
    BuildStorage<T> bigBuildStorage;

    @Override
    public void generate(T big) {
        big.setOrderType(-2);
        ProcessFactory<T> orderTypeFactory = bigBuildStorage.getOrderTypeFactory(big);
        orderTypeFactory.invoicePrint(big);
    }
}
