package br.com.cooperative.assembly.controller.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.br.CPF;

import br.com.cooperative.assembly.domain.DecisionVote;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class VoteRequest {

    @NotBlank(message = "{invalid.document}")
    @Pattern(regexp="\\d+", message = "{invalid.document}")
    @CPF(message = "{invalid.document}")
    private String document;

    @NotNull(message = "{request.agenda.decision.error}")
    private DecisionVote decision;
}
