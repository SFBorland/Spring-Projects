package com.seanborland.springmvcexamples.scope;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = BeanDefinition.SCOPE_SINGLETON)
public class PersonSingleton {
    
    String personSingletonName = "I'm a singleton";
    
    public String getPersonSingletonName() {
        return personSingletonName;
    }
}
