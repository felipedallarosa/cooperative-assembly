package br.com.cooperative.assembly.controller.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class AgendaResponse {

    private Long id;

    @ApiModelProperty(notes = "The description that insert in database")
    private String description;

}
