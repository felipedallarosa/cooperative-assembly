package br.com.cooperative.assembly.controller.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class VoteRequest {

    @NotNull
    @Size(min=9, max=14)
    private String document;

    @NotNull
    private boolean decision;
}
