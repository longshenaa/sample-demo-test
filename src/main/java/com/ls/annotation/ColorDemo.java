package com.ls.annotation;

import java.lang.reflect.Method;

public class ColorDemo {
    @Color(value = "yellow")
    public String getColor(){
        return "hello";
    }
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, NoSuchMethodException {
        Class<ColorDemo> colorDemoClass = ColorDemo.class;
        Method method = colorDemoClass.getMethod("getColor");
        if (method.isAnnotationPresent(Color.class)) {
            Color annotation = method.getAnnotation(Color.class);
            System.out.println(annotation.value());
        }
    }
}
