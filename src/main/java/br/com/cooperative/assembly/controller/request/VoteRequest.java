package br.com.cooperative.assembly.controller.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
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

    @NotEmpty
    @Size(min=9, max=14)
    @Pattern(regexp="\\d+")
    private String document;

    @NotEmpty
    private boolean decision;
}
