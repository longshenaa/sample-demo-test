package com.ls.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class UserMain {
    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> clazz = Class.forName("com.ls.annotation.UserDemo");
        SetTable setTable = clazz.getAnnotation(SetTable.class);
        System.out.println("table:" + setTable.value());
        StringBuilder sb = new StringBuilder();
        sb.append("select ");
        Field[] fields = clazz.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            SetProperty annotation = fields[i].getAnnotation(SetProperty.class);
            if (annotation != null) {
                sb.append(annotation.value());
                if (i != fields.length - 1) {
                    sb.append(",");
                }
                else {
                    sb.append(" ");
                }
            }
        }
        sb.append("from ");
        sb.append(setTable.value());
        System.out.println(sb.toString());
    }
}
