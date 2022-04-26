package com.levil.design.core;

import com.levil.design.handler.BuildHandler;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class HandlerDispatch {


    // TODO 1.业务抽取要考虑真正公共的东西，2.就算真正公共的东西还是要允许重写,3.这玩意儿放这里吗
    public List<BuildHandler> getDefaultCommonBuild(){
//        List<String> commonBuild = Lists.newArrayList(FOOTER_COMMON, TOP_COMMON);
//        return commonBuild.stream().map(e -> buildHandlerMap.get(e)).collect(Collectors.toList());
        return null;
    }
}
