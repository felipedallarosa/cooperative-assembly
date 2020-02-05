package br.com.cooperative.assembly.controller.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import br.com.cooperative.assembly.domain.DecisionVote;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class VoteRequest {

    @NotEmpty
    @Size(min=9, max=11)
    @Pattern(regexp="\\d+")
    private String document;

    private DecisionVote decision;
}
