package com.levil.eyesmq;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

@Data
public class ProcessLogStatistics implements Serializable {
    private List<Model> generatedInfo;
    private List<Model> sentInfo;

    public ProcessLogStatistics(Builder builder) {
        this.generatedInfo=builder.getGeneratedInfo();
        this.sentInfo=builder.getSentInfo();
    }

    public interface add{
        List get();
    }

    private static class Builder {
        private  List<Model> generatedInfo;;
        private  List<Model> sentInfo;

        public Builder() {
        }

        public Builder setGeneratedInfo(List<Model> generatedInfo) {
            this.generatedInfo = generatedInfo;
            return this;
        }
        public Builder setGeneratedInfo2(Function<Builder,List<Model>> function) {
            this.generatedInfo = function.apply(this);
            return this;
        }

        public Builder setSentInfo(add add1) {
            return this;
        }
        public Builder setSentInfo2(Supplier<List<Model>> consumer) {
            this.sentInfo= consumer.get();
            return this;
        }

        public List<Model> getGeneratedInfo() {
            return generatedInfo;
        }

        public List<Model> getSentInfo() {
            return sentInfo;
        }

        public ProcessLogStatistics build(){
            return new ProcessLogStatistics(this);
        }
    }

    public static void main(String[] args) {
        Supplier<List<Model>> consumer = ()->hah(8);
        ProcessLogStatistics build = new Builder()
                .setGeneratedInfo2((x)-> new ArrayList<>())
                .setSentInfo2(ArrayList::new)
                .setSentInfo(() -> null)
                .build();
        System.out.println("build = " + build);

    }
    public static List<Model> hah(Integer a ) {
        return new ArrayList<>();
    }

}
