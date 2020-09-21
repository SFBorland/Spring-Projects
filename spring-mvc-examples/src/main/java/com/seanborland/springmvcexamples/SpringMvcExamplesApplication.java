package com.seanborland.springmvcexamples;


import com.seanborland.springmvcexamples.scope.PersonPrototype;
import com.seanborland.springmvcexamples.scope.PersonSingleton;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringMvcExamplesApplication {
    
    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(SpringMvcExamplesApplication.class, args);
    
        PersonSingleton personSingleton = applicationContext.getBean("personSingleton", PersonSingleton.class);
        System.out.println("PersonSingleton name: " + personSingleton.getPersonSingletonName());

        PersonPrototype personPrototype = applicationContext.getBean("personPrototype", PersonPrototype.class);
        System.out.println("PersonSingleton name: " + personPrototype.getPersonPrototypeName());
        
        //Address printed will be the same since it's a singleton.
        PersonSingleton personSingleton1 = applicationContext.getBean("personSingleton", PersonSingleton.class);
        PersonSingleton personSingleton2 = applicationContext.getBean("personSingleton", PersonSingleton.class);
        System.out.println(personSingleton1);
        System.out.println(personSingleton2);
    
        //Address's printed will be different because the scope is Prototype.
        PersonPrototype personPrototype1 = applicationContext.getBean("personPrototype", PersonPrototype.class);
        PersonPrototype personPrototype2 = applicationContext.getBean("personPrototype", PersonPrototype.class);
        System.out.println(personPrototype1);
        System.out.println(personPrototype2);
    }
    
}
