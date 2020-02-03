package br.com.cooperative.assembly.exception;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class ErrorMessageTest {

    @Test(expected = IllegalArgumentException.class)
    public void tryCreateErrorMessage(){
        new ErrorMessage();
    }

}