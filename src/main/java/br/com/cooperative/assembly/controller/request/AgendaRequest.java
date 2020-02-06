package br.com.cooperative.assembly.controller.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class AgendaRequest {

    @NotBlank(message = "{request.agenda.description}")
    @Size(min=5, max=300, message = "{request.agenda.description}")
    private String description;
}
