package com.example;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import javax.management.Query;

public class Test {
	
	public static void main(String[] args) {
		
		Filter f1 = new Filter();
		f1.setId(10);//表示查询ID为10 的用户
		
		Filter f2 = new Filter();
		f2.setUserName("lucy");//模糊查询
		
		Filter f3 = new Filter();
		f3.setEmail("512222607@qq.com,1111111@qq.com,222222@qq.com");//查询邮箱为其中任意一个的
		
		String sql1 = query(f1);
		String sql2 = query(f2);
		String sql3 = query(f3);
		
		System.out.println(sql1);
		System.out.println(sql2);
		System.out.println(sql3);
	}

	private static String query(Object f){
		StringBuffer sql = new StringBuffer();
		//1 获取class
		Class c = f.getClass();
		//2 获取table的名字
		boolean annotation = c.isAnnotationPresent(Table.class);
		if(!annotation){
			return null;
		}
		Table t = (Table)c.getAnnotation(Table.class);
		String tableName = t.value();
		sql.append("select * from ").append(tableName).append(" where 1=1");
		//3 遍历所有字段
		Field[] declaredFields = c.getDeclaredFields();
		for(Field field : declaredFields){
			//4 处理每个字段对应的sql
			//4.1 拿到字段名
			boolean annotationPresent = field.isAnnotationPresent(Column.class);
			if(!annotationPresent){
				continue;
			}
			Column annotation2 = field.getAnnotation(Column.class);
			String columnName = annotation2.value();
			//4.2 拿到字段的值
			String filedName = field.getName();
			String getMethodName = "get"+filedName.substring(0,1).toUpperCase()+filedName.substring(1);
			Object fieldValue = null;
			try {
				Method getMethod = c.getMethod(getMethodName);
				fieldValue = getMethod.invoke(f);
			} catch (Exception e) {
				e.printStackTrace();
			}
			//4.3 拼装sql
			if(fieldValue == null || (fieldValue instanceof Integer && (Integer)fieldValue == 0)){
				continue;
			}
			sql.append(" and ").append(filedName);
			if(fieldValue instanceof String){
				if(((String)fieldValue).contains(",")){
					String[] value = ((String) fieldValue).split(",");
					sql.append(" in (");
					for(String s : value){
						sql.append("'").append(s).append("'").append(",");
					}
					sql.deleteCharAt(sql.length()-1);
					sql.append(")");
				}else{					
					sql.append("=").append("'").append(fieldValue).append("'");
				}
				continue;
			}
			if(fieldValue instanceof Integer){
				sql.append("=").append(fieldValue);
			}
			
		}
		return sql.toString();
	}
}
