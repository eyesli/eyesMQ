package com.levil.design.core;

import com.google.common.collect.Lists;
import com.levil.design.constants.HandlerGroupEnum;
import com.levil.design.handler.BuildHandler;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.CompletableFuture;


@Data
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("all")
public class HandlerNode<T> {

    private HandlerGroupEnum handlerGroupEnum = null;
    private BuildHandler<T> handler = null;
    private HandlerNode<T> next = null;
    private int length;

    public T exec(T obj) {
        List<CompletableFuture> futureList = Lists.newArrayList();

        while (this.next != null) {
            //这是一个带头结点的单链表,直接拿next
            CompletableFuture completableFuture = next.handler.asyncModeActuator(obj);
            if (completableFuture != null) {
                futureList.add(completableFuture);
            }
            next = next.getNext();
        }
        CompletableFuture.allOf(futureList.toArray(new CompletableFuture[]{})).join();
        return obj;
    }

    public HandlerNode(HandlerNode<T> handlerNode) {
        Random r = new Random();
        int num = r.nextInt(100);
        if (true) {
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
        if (true) {
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
        HandlerNode<T> header = handlerNode;
        while (header != null) {
            HandlerNode<T> newNode = new HandlerNode<>();
            newNode.setHandler(header.getHandler());
            newNode.setNext(header.getNext());
            newNode.setHandlerGroupEnum(header.getHandlerGroupEnum());
            list.add(newNode);
            header = header.getNext();
        }
        HandlerNode<T> handler = list.get(0);
        this.setNext(handler.getNext());
        this.setHandlerGroupEnum(handler.getHandlerGroupEnum());
        this.setHandler(handler.getHandler());
        this.setLength(handlerNode.getLength());
    }

    private void hash(HandlerNode<T> handlerNode) {
        Map<HandlerNode<T>, HandlerNode<T>> map = new LinkedHashMap<>();
        HandlerNode<T> temp = handlerNode;
        while (temp != null) {
            HandlerNode<T> newNode = new HandlerNode<>();
            newNode.setHandler(temp.getHandler());
            newNode.setNext(temp.getNext());
            newNode.setHandlerGroupEnum(temp.getHandlerGroupEnum());
            map.put(temp, newNode);
            temp = temp.getNext();
        }
        HandlerNode<T> handler = map.get(handlerNode);
        this.setNext(handler.getNext());
        this.setHandlerGroupEnum(handler.getHandlerGroupEnum());
        this.setHandler(handler.getHandler());
        this.setLength(handlerNode.getLength());
    }
}
