package com.levil.design.constants;

import com.levil.design.handler.BuildHandler;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.concurrent.CompletableFuture;

@Getter
@AllArgsConstructor
@SuppressWarnings("all")
public enum AsyncModeEnum {
    RUN_ASYNC("异步不等待,跟build无关的数据，比如存入日志，发送邮件") {
        @Override
        public CompletableFuture asyncMethod(BuildHandler o, Object obj) {
            CompletableFuture.runAsync(() -> o.build(obj));
            return null;
        }
    },
    SUPPLY_ASYNC("异步等待，跟build相关的,等待所有异步build 返回之后执行打印操作") {
        @Override
        public CompletableFuture asyncMethod(BuildHandler o, Object obj) {
            return CompletableFuture.supplyAsync(() -> o.build(obj));
        }
    },
    NO_ASYNC("串行化,一个一个执行") {
        @Override
        public CompletableFuture asyncMethod(BuildHandler o, Object obj) {
            o.build(obj);
            return null;
        }
    };
    private final String desc;

    public abstract CompletableFuture asyncMethod(BuildHandler o, Object obj);

}
