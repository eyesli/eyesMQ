package com.levil.design.test;

import com.levil.design.handler.impl.abstra.AbstractBuildHandler;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
//监控类 实现BeanPostProcessor接口
public class findSonClassTest implements BeanPostProcessor {

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}

	/**
	 * 在具体子类初始化之后 确认该对象是否是对应父类（AbstractBuildHandler）的子类
	 */
	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

		if (bean instanceof AbstractBuildHandler) {
			AbstractBuildHandler dict = (AbstractBuildHandler) bean;
			System.out.println("AbstractBuildHandler = " + dict);
		}
		return bean;
	}

}