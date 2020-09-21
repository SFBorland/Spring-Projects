package com.seanborland.springmvcexamples.scope;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
public class PersonPrototype {
    
    private final String personPrototypeName = "I'm a prototype.";
    
    public String getPersonPrototypeName() {
        return personPrototypeName;
    }
}
