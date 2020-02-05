package br.com.cooperative.assembly.domain;

public enum VotingSessionResultStatus {
    APPROVED,
    REJECTED;

    public static VotingSessionResultStatus generateResultStatus(final Long voteYes, final Long voteNo) {
        if (voteYes.compareTo(voteNo) > 0) {
            return APPROVED;
        }
        return REJECTED;
    }

}

