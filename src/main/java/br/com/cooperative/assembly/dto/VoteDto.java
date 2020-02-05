package br.com.cooperative.assembly.dto;

import br.com.cooperative.assembly.domain.DecisionVote;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class VoteDto {

    private Long id;

    private Long voteSessionId;

    private String document;

    private DecisionVote decision;
}
