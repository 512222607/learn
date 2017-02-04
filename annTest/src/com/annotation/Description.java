package com.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)//指明注解是在哪些方面起作用的   有编译时注解  有运行时注解(运行时有效) 有源码时注解
@Inherited //接口实现是没有作用的  只能在类的实现上有用  并且只能解析到类上面的该注解
@Documented //在javadoc生成文档的时候会包含相关注解的内容
public @interface Description {

	String value();
	
	@Deprecated//这个注解是过时的意思   如果父类的方法上使用了此注解  则子类不一定需要实现吃方法
	int age() default 18;//写了default表示注解的属性有默认值  不是必须填写的
	
	char sex() default 'x';
	//只能是八大基本类型加String, Class, annotation, enumeration(枚举)
}
