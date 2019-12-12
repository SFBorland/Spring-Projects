package com.seanborland.embeddedh2;

import com.seanborland.embeddedh2.repository.PersonRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmbeddedH2ApplicationTests {
    
    @Autowired
    private PersonRepository personRepository;
    
    @Test
    public void getAllPersons() {
        System.out.println("Call in H2 DB");
        personRepository.getPerson();
    }
}
