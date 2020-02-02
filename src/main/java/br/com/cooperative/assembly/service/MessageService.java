package br.com.cooperative.assembly.service;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import br.com.cooperative.assembly.domain.EnumMessage;

@Service
public class MessageService {

    private MessageSource messageSource;

    public MessageService(final MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String get(final EnumMessage mensagem, final Object... args) {
        if (mensagem == null) {
            return "";
        }

        return messageSource.getMessage(mensagem.getMessage(), args, LocaleContextHolder.getLocale());
    }
}
