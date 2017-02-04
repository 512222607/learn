package com.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Iterator;

public class parseAnn {

	
	public static void main(String[] args) {
		//1 使用类加载器加载类
		try {
			Class c = Class.forName("com.annotation.Child");
			//2 找到类上面的注解 判断加载的类上面是否有Description这个注解
			boolean isExist = c.isAnnotationPresent(Description.class);
			if(isExist){
				//3 拿到注解实例
				Description d = (Description) c.getAnnotation(Description.class);
				System.out.println(d.value());
			}
			
			//4 解析找到方法上的注解
			//遍历类的所有方法 
			Method[] ms = c.getMethods();
			for(Method m : ms){
				boolean isMExist = m.isAnnotationPresent(Description.class);
				if (isMExist) {
					Description d = m.getAnnotation(Description.class);
					System.out.println(d.value()+d.age());
				}
			}
			
			//另一种解析方式
			for(Method m : ms){
				Annotation[] annotations = m.getAnnotations();
				for(Annotation a : annotations){
					Description d = (Description)a;
					System.out.println(d.value());
				}
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
