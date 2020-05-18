package com.seanborland.reactivespringexamples.workspace;

import lombok.Data;
import org.junit.Test;

import java.time.Period;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Workspace {
    
    @Test
    public void clearMeAfterUse() {
    
        List<String> names = Arrays.asList("sean", "brandon", "desiree");
    
        Person person = new Person();
        
        Optional<Person> person1 = names.stream()
                .map(name -> {
                    person.setFname(name);
                    return person;
                }).findAny();
    
        System.out.println(person1.get().getFname());
    }
}

@Data
class Person {
    private String fname;
}
