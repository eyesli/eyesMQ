package com.levil.design.factory;

import com.levil.design.core.constants.HandlerGroupEnum;
import com.levil.design.handler.BuildHandler;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class HandlerNode<T> {

    private HandlerGroupEnum handlerGroupEnum=null;
    private BuildHandler<T> handler=null;
    private HandlerNode<T> next=null;

    public void exec(T obj) {

        while (next != null) {
            next.handler.build(obj);
            next = next.getNext();
        }
    }

    public HandlerNode(HandlerNode<T> handlerNode) {
        Random r = new Random();
        int num = r.nextInt(100);
        if (num % 2 == 0) {
            //demo List实现快1ms
            System.out.println("偶数实现");
            list(handlerNode);
        } else {
            System.out.println("奇数实现");
            hash(handlerNode);
        }
    }
    public void deepCopy(HandlerNode<T> handlerNode) {
        Random r = new Random();
        int num = r.nextInt(100);
        if (num % 2 == 0) {
            //demo List实现快1ms
            System.out.println("偶数实现");
            list(handlerNode);
        } else {
            System.out.println("奇数实现");
            hash(handlerNode);
        }
    }
    private void list(HandlerNode<T> handlerNode) {
        List<HandlerNode<T>> list = new LinkedList<>();
        while (handlerNode != null) {
            HandlerNode<T> newNode = new HandlerNode<>();
            newNode.setHandler(handlerNode.getHandler());
            newNode.setNext(handlerNode.getNext());
            newNode.setHandlerGroupEnum(handlerNode.getHandlerGroupEnum());
            list.add(newNode);
            handlerNode = handlerNode.getNext();
        }
        HandlerNode<T> handler = list.get(0);
        this.setNext(handler.getNext());
        this.setHandlerGroupEnum(handler.getHandlerGroupEnum());
        this.setHandler(handler.getHandler());
    }

    private void hash(HandlerNode<T> handlerNode) {
        Map<HandlerNode<T>, HandlerNode<T>> map = new LinkedHashMap<>();
        HandlerNode<T> temp = handlerNode;
        while (handlerNode != null) {
            HandlerNode<T> newNode = new HandlerNode<>();
            newNode.setHandler(handlerNode.getHandler());
            newNode.setNext(handlerNode.getNext());
            newNode.setHandlerGroupEnum(handlerNode.getHandlerGroupEnum());
            map.put(handlerNode, newNode);
            handlerNode = handlerNode.getNext();
        }
        HandlerNode<T> handler = map.get(temp);
        this.setNext(handler.getNext());
        this.setHandlerGroupEnum(handler.getHandlerGroupEnum());
        this.setHandler(handler.getHandler());
    }
}
