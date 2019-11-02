package com.seanborland;

import com.seanborland.config.ApplicationConfig;
import com.seanborland.service.CustomerService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
    
    public static void main(String[] args) {
        
        //** Using AnnotationConfigApplicationContext(ApplicationConfig.class) instead of
        // ClassPathXmlApplicationContext("applicationContext.xml").
        ApplicationContext appContext = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        
        //** The only thing in the ApplicationConfig this needs is the "@ComponentScan" to tell it where the package
        //   is holding the actual CustomerService bean?
        CustomerService service = appContext.getBean("customerService", CustomerService.class);
        
        System.out.println(service.findAll().get(0).getFirstname());
        
        // System.out.println(service);
        
        // ** This is to show that you will get the same address because of the
        // 	  singleton pattern/scope.
        // CustomerService service2 = appContext.getBean("customerService", CustomerService.class);
        // System.out.println(service2);
    }
}
