package br.com.cooperative.assembly;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class CooperativeAssemblyApiApplicationTest {

    @Test
    public void main() {
        try {
            CooperativeAssemblyApiApplication.main(new String[0]);
        } catch(Exception e) {
            fail("Should not have thrown any exception");
        }
    }
}