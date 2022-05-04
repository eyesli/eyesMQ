package com.levil.design.factory;

import com.levil.design.core.constants.ProcessEnum;
import com.levil.design.pojo.Big;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultProcessFactory extends AbstractProcessFactory<Big>{



    @Autowired
    Pipeline<Big> pipeline;
    @Override
    public Pipeline<Big> pipelineCreate() {
        pipeline.createPipeline();

        return null;
    }

    @Override
    public ProcessEnum getProcess() {
        return ProcessEnum.BRAZIL;
    }
}
