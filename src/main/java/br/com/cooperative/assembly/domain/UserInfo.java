package br.com.cooperative.assembly.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {

    private static final String ABLE_TO_VOTE = "ABLE_TO_VOTE";

    private String status;

    public Boolean isAbleToVote() {
        return ABLE_TO_VOTE.equalsIgnoreCase(this.getStatus());
    }

}
