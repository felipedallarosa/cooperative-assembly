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
    public void testGetMessageButNull(){

        String response =  messageService.get(null);

        assertEquals("", response );
    }

    @Test
    public void testGetMessage(){

        when(messageSource.getMessage(any(), any(), any())).thenReturn(INVALID_DOCUMENT);

        String response =  messageService.get(EnumMessage.INVALID_DOCUMENT);

        assertEquals(INVALID_DOCUMENT , response );
    }
}