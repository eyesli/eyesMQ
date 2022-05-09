package com.levil.design.factory;

import com.levil.design.handler.BuildHandler;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PipeLineImpl<T> implements PipeLine<T> {

    private HandlerNode<T> tail = new HandlerNode<>();


    @Override
    public final PipeLine<T> createPipeline(List<BuildHandler<T>> buildHandler) {
        HandlerNode<T> linkedList = tail;
        for (BuildHandler<T> handler : buildHandler) {
            HandlerNode<T> handlerNode = new HandlerNode<>();
            handlerNode.setHandler(handler);
            linkedList.setNext(handlerNode);
            linkedList = handlerNode;
        }
        return this;
    }

    @Override
    public void exec(T obj) {
        tail.exec(obj);
    }

}
