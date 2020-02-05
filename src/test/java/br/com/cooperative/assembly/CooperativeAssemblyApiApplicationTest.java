package br.com.cooperative.assembly;

import static org.junit.Assert.*;

import org.junit.Test;

public class CooperativeAssemblyApiApplicationTest {


    @Test
    public void assertConfig() {
        try {
            new CooperativeAssemblyApiApplication().main(new String[]{""});
        } catch(Exception e) {
            fail("Should not have thrown any exception");
        }
    }
}