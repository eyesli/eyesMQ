package com.levil.design.factory;

import com.levil.design.handler.BuildHandler;
import lombok.Data;

@Data
public class HandlerNode<T> {

    private BuildHandler<T> handler;
    private HandlerNode<T> next;

    public void exec(T obj) {
        handler.build(obj);
        while (next.handler != null) {
            next.handler.build(obj);
            next=next.getNext();
        }
    }
}
