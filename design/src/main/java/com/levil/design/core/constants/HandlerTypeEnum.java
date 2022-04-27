package com.levil.design.core.constants;

import com.levil.design.factory.Actuator;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum HandlerTypeEnum implements Actuator {

    /**
     *     假设一个默认的公共的构建有这些流程 要build这么多东西，实际可能更多一点
     */
    DEFAULT_1(1,"默认模块构建1,比如是top"){
        @Override
        public HandlerTypeEnum getHandlerTypeEnum() {
            return this;
        }
    },
    DEFAULT_2(2,"默认模块构建2,比如是footer") {
        @Override
        public HandlerTypeEnum getHandlerTypeEnum() {
            return this;
        }
    },
    DEFAULT_3(3,"默认模块构建3，比如是title") {
        @Override
        public HandlerTypeEnum getHandlerTypeEnum() {
           return this;
        }
    },
    DEFAULT_4(4,"默认模块构建4") {
        @Override
        public HandlerTypeEnum getHandlerTypeEnum() {
            return this;
        }
    },
//    DEFAULT_5(5,"默认模块构建5") {
//        @Override
//        public void defaultBuild(BuildHandler buildHandler, Big big) {
//
//        }
//    },
//    DEFAULT_6(6,"默认模块构建6") {
//        @Override
//        public void defaultBuild(BuildHandler buildHandler, Big big) {
//
//        }
//    },
//    DEFAULT_7(7,"默认模块构建7") {
//        @Override
//        public void defaultBuild(BuildHandler buildHandler, Big big) {
//
//        }
//    },
//    DEFAULT_8(8,"默认模块构建8") {
//        @Override
//        public void defaultBuild(BuildHandler buildHandler, Big big) {
//
//        }
//    },
//    DEFAULT_9(9,"默认模块构建9") {
//        @Override
//        public void defaultBuild(BuildHandler buildHandler, Big big) {
//
//        }
//    },
//    DEFAULT_10(10,"默认模块构建10") {
//        @Override
//        public void defaultBuild(BuildHandler buildHandler, Big big) {
//
//        }
//    },
//    DEFAULT_11(11,"默认模块构建11") {
//        @Override
//        public void defaultBuild(BuildHandler buildHandler, Big big) {
//
//        }
//    },
//    DEFAULT_12(12,"默认模块构建12") {
//        @Override
//        public void defaultBuild(BuildHandler buildHandler, Big big) {
//
//        }
//    },
//    DEFAULT_13(13,"默认模块构建13") {
//        @Override
//        public void defaultBuild(BuildHandler buildHandler, Big big) {
//
//        }
//    },
//    DEFAULT_14(14,"默认模块构建14") {
//        @Override
//        public void defaultBuild(BuildHandler buildHandler, Big big) {
//
//        }
//    },
//    DEFAULT_15(15,"默认模块构建15") {
//        @Override
//        public void defaultBuild(BuildHandler buildHandler, Big big) {
//
//        }
//    },
    DEFAULT_16(16,"默认模块构建16") {
        @Override
        public HandlerTypeEnum getHandlerTypeEnum() {
            return this;
        }
    };
    private final Integer type;
    private final String desc;


    public static List<Integer> getAllHandlerType(){
       return Stream.of(HandlerTypeEnum.values()).map(HandlerTypeEnum::getType).collect(Collectors.toList());
    }
}
