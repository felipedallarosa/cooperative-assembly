package br.com.cooperative.assembly.service;

import static br.com.cooperative.assembly.domain.EnumMessage.EXTERNAL_SERVICE_UNAVALIABLE;

import org.springframework.stereotype.Service;

import br.com.cooperative.assembly.domain.UserInfo;
import br.com.cooperative.assembly.facede.client.UserInfoClient;
import br.com.cooperative.assembly.exception.FeignExceptionException;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserInfoService {

    private MessageService messageService;

    private UserInfoClient userInfoClient;

    public UserInfoService(final MessageService messageService,
        final UserInfoClient userInfoClient) {
        this.messageService = messageService;
        this.userInfoClient = userInfoClient;
    }

    public boolean isValidDocument(String document) {

        boolean validDocument = false;

        try {
            log.warn("Trying information about Document {}.", document);

            UserInfo userInfo = userInfoClient.findUser(document);

            log.warn("Result information about Document {}.", userInfo);

            validDocument = userInfo.isAbleToVote();

            log.warn("Sucess information about Document {}.", document);
        } catch (FeignException e) {
            log.error("Error when try information about Document {}.", document);
            throw new FeignExceptionException(messageService.get(EXTERNAL_SERVICE_UNAVALIABLE));
        }

        return validDocument;
    }

}
