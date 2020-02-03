package br.com.cooperative.assembly.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class UserInfo {

    private static final String ABLE_TO_VOTE = "ABLE_TO_VOTE";

    private String status;

    public Boolean isAbleToVote() {
        return ABLE_TO_VOTE.equalsIgnoreCase(this.getStatus());
    }

}
