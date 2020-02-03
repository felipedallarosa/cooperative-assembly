package br.com.cooperative.assembly.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.cooperative.assembly.domain.UserInfo;
import br.com.cooperative.assembly.exception.FeignExceptionException;
import br.com.cooperative.assembly.facede.client.UserInfoClient;
import feign.FeignException;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserInfoServiceTest {

    private static final String DOCUMENT = "83714006087";

    private UserInfoService userInfoService;

    @Mock
    private MessageService messageService;

    @Mock
    private UserInfoClient userInfoClient;

    @Before
    public void setUp() {
        userInfoService = new UserInfoService(messageService, userInfoClient);
    }

    @Test
    public void verifyIsValidDocumentSucessufulAndAbleToVote(){

        UserInfo response = UserInfo.builder().status("ABLE_TO_VOTE").build();
        when(userInfoClient.findUser(DOCUMENT)).thenReturn(response);

        Boolean isValid =  userInfoService.isValidDocument(DOCUMENT);

        assertTrue( isValid );
    }

    @Test
    public void verifyIsValidDocumentSucessufulAndUnableToVote(){

        UserInfo response = UserInfo.builder().status("UNABLE_TO_VOTE").build();
        when(userInfoClient.findUser(DOCUMENT)).thenReturn(response);

        Boolean isValid =  userInfoService.isValidDocument(DOCUMENT);

        assertFalse( isValid );
    }

    @Test(expected = FeignExceptionException.class)
    public void verifyIsValidDocumentButServerError(){

        when(userInfoClient.findUser(DOCUMENT)).thenThrow(FeignException.class);

        Boolean isValid =  userInfoService.isValidDocument(DOCUMENT);
    }



}