package com.levil.design.factory;

import com.levil.design.handler.BuildHandler;
import lombok.Data;

@Data
public class HandlerNode<T> {

    private BuildHandler<T> handler;
    private HandlerNode<T> next = null;

    public void exec(T obj) {
        while (next != null) {
            this.handler.build(obj);
            next=next.getNext();
        }
    }
}
