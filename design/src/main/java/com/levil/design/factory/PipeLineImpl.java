package com.levil.design.factory;

import com.levil.design.handler.BuildHandler;
import org.springframework.stereotype.Service;

@Service
public class PipeLineImpl<T> implements Pipeline<T>{

    private HandlerNode<T> tail = new HandlerNode<>();


    @SafeVarargs
    @Override
    public final void createPipeline(BuildHandler<T>... buildHandler) {
        HandlerNode<T> linkedList = tail;
        for (BuildHandler<T> handler : buildHandler) {
            HandlerNode<T> handlerNode = new HandlerNode<>();
            handlerNode.setHandler(handler);
            linkedList.setNext(handlerNode);
            linkedList = handlerNode;
        }
        tail= linkedList;
    }

    @Override
    public void exec(T obj) {
        tail.exec(obj);
    }

}
