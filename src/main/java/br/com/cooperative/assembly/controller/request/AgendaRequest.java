package br.com.cooperative.assembly.controller.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
public class AgendaRequest {

    @NotEmpty
    @Size(min=5, max=300)
    private String description;
}
