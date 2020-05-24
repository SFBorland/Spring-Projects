package com.seanborland.springmvcerrorhandling;

import lombok.Data;
import org.junit.Test;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class Temp {
    
    @Test
    public void charlie() {
        bar();
    }
    
    @Test
    public void alpha() {
        try {
            foo();
        } catch (IllegalMonitorStateException illegalMonitorStateException) {
            throw handleMyException(illegalMonitorStateException);
        }
    }
    
    @ExceptionHandler(MyException.class)
    public String bravo() {
        System.out.println(">>> SEAN <<<");
        return "Sean";
    }
    
    private void bar() {
        throw new MyException();
    }
    private String foo() {
        throw new IllegalMonitorStateException();
    }
    
    private MyException handleMyException(IllegalMonitorStateException illegalMonitorStateException) {
        MyException myException = new MyException();
        myException.setName("An exception was thrown...");
        myException.setMessage(illegalMonitorStateException.getMessage());
        
        return myException;
    }
}

@Data
class MyException extends RuntimeException{
    
    private String name;
    private String message;
    
    public MyException() {
        super("this is the super() call");
    }
}
