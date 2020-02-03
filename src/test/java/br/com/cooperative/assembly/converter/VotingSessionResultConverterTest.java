package br.com.cooperative.assembly.converter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class VotingSessionResultConverterTest {


    @Test(expected = IllegalStateException.class)
    public void tryCreateVotingSessionResultConverter(){

        //then
        new VotingSessionResultConverter();

    }

}