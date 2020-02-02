package br.com.cooperative.assembly.controller.response;

import br.com.cooperative.assembly.domain.VotingSession;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class VotingSessionResultResponse {

    private VotingSession votingSession;
    private Long voteYes;
    private Long voteNo;

}
