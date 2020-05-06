package com.ls.spring;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.List;

public class ClassPathXmlApplicaitonContextCopy {
    private String xmlPath;
    public ClassPathXmlApplicaitonContextCopy(String xmlPath) {
        this.xmlPath = xmlPath;
    }
    public Object getBean(String beanId) throws DocumentException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException, InstantiationException {
        if (StringUtils.isEmpty(beanId)) {
            throw new NullPointerException("传入的参数不能为空！");
        }
        //读取xml文件
        SAXReader reader = new SAXReader();
        Document document = reader.read(this.getClass().getClassLoader().getResourceAsStream("applicationContext.xml"));
        Element rootDocument = document.getRootElement();
        List<Element> elements = rootDocument.elements();
        Object obj = null;
        for (Element element : elements) {
            String id = element.attributeValue("id");
            if (!id.equals(beanId)) {
                continue;
            }
            String beanClassPath = element.attributeValue("class");
            Class<?> forName = Class.forName(beanClassPath);
            obj = forName.newInstance();
            List<Element> list = element.elements();
            for (Element element1 : list) {
                String name = element1.attributeValue("name");
                String value = element1.attributeValue("value");
                Field field = forName.getDeclaredField(name);
                field.setAccessible(true);
                field.set(obj, value);
            }
            break;
        }
        return obj;
    }
    public static void main(String[] args) {
        ClassPathXmlApplicaitonContextCopy context = new ClassPathXmlApplicaitonContextCopy("applicationContext.xml");
        try {
            User user1 = (User) context.getBean("user1");
            System.out.println(user1 != null ? user1.getName() + ";" + user1.getUserId() : "");
            User user2 = (User) context.getBean("user2");
            System.out.println(user2 != null ? user2.getName() + ";" + user2.getUserId() : "");
        }

        catch (DocumentException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        catch (InstantiationException e) {
            e.printStackTrace();
        }
        catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
