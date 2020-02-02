package br.com.cooperative.assembly.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EnumMessage {
    ASSOCIATE_ALREADY_VOTED("associate.already.voted"),
    EXTERNAL_SERVICE_UNAVALIABLE("external.service.unavaliable"),
    INVALID_DOCUMENT("invalid.document"),
    INVALID_VOTING_SESSION_OPENED("invalid.voting.session.opened"),
    VOTING_SESSION_ALREADY_OPENED("voting.session.already.opened"),
    AGENDA_REGISTRY_EXISTS("agenda.registry.exists"),
    AGENDA_REGISTRY_NOT_EXISTS("agenda.registry.not.exists");

    private String message;

}
