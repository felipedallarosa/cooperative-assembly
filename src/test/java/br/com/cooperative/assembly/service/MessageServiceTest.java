package br.com.cooperative.assembly.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.context.MessageSource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.cooperative.assembly.domain.EnumMessage;

@RunWith(SpringJUnit4ClassRunner.class)
public class MessageServiceTest {

    private static final String INVALID_DOCUMENT = "invalid.document";

    private MessageService messageService;

    @Mock
    private MessageSource messageSource;

    @Before
    public void setUp() {
        messageService = new MessageService(messageSource);
    }

    @Test
    public void getMessageButNull(){
        // given

        // when
        String response =  messageService.get(null);

        //then
        assertEquals("", response );
    }

    @Test
    public void getMessage(){
        // given

        // when
        when(messageSource.getMessage(any(), any(), any())).thenReturn(INVALID_DOCUMENT);

        String response =  messageService.get(EnumMessage.INVALID_DOCUMENT);

        //then
        assertEquals(INVALID_DOCUMENT , response );
    }
}