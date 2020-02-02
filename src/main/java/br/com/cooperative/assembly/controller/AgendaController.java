package br.com.cooperative.assembly.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cooperative.assembly.controller.adapter.AgendaAdapter;
import br.com.cooperative.assembly.controller.request.AgendaRequest;
import br.com.cooperative.assembly.controller.response.AgendaResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/v1/agenda", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@Api(
    value = "Management of Agenda",
    tags = {"Management of Agenda"})
public class AgendaController {

    private AgendaAdapter agendaAdapter;

    public AgendaController(AgendaAdapter agendaAdapter) {
        this.agendaAdapter = agendaAdapter;
    }

    @PostMapping
    @ApiOperation(value = "Generate a new agenda")
    public ResponseEntity<AgendaResponse> insertPauta(@Valid @RequestBody AgendaRequest agendaRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(agendaAdapter.handleRequest(agendaRequest));
    }

}
