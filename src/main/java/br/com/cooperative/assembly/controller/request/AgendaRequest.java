package br.com.cooperative.assembly.controller.request;

import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;

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
public class AgendaRequest {

    private static final String DESCRIPTION_MESSAGE = "Pauta deve ter no mínimo 5 caracteres e máximo 300";

    @NotNull(message = DESCRIPTION_MESSAGE)
    @Size(min=4, max=300, message = DESCRIPTION_MESSAGE)
    private String description;
}
