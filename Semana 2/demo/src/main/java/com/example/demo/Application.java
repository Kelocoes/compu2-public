package com.example.demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        HolaMundo obj1 = (HolaMundo) context.getBean("HolaMundo");
        System.out.println(obj1.getMessage());
    }
}
