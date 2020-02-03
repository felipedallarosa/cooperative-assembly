package br.com.cooperative.assembly.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
public class VoteDto {

    private Long voteSessionId;

    private String document;

    private boolean decision;
}
