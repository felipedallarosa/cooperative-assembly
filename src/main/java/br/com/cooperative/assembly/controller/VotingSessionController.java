package br.com.cooperative.assembly.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.cooperative.assembly.controller.adapter.VotingSessionAdapter;
import br.com.cooperative.assembly.controller.request.VotingSessionRequest;
import br.com.cooperative.assembly.controller.response.VotingSessionResponse;
import br.com.cooperative.assembly.controller.response.VotingSessionResultResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/v1/votingSession", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@Api(
    value = "Management of Voting Session",
    tags = {"Management of Voting Session"})
public class VotingSessionController {

    private VotingSessionAdapter votingSessionAdapter;

    public VotingSessionController(final VotingSessionAdapter votingSessionAdapter) {
        this.votingSessionAdapter = votingSessionAdapter;
    }

    @PostMapping(value="/{agendaId}")
    @ApiOperation(value = "Open a new Voting Session")
    public ResponseEntity<VotingSessionResponse> openVotingSession(@PathVariable Long agendaId, @RequestParam VotingSessionRequest votingSessionRequest) {
        return ResponseEntity.ok(votingSessionAdapter.handleRequest(agendaId, votingSessionRequest));
    }

    @GetMapping(value="/{votingSessionId}")
    @ApiOperation(value = "Verify Result about Voting Session")
    public ResponseEntity<VotingSessionResultResponse> openVotingSession(@PathVariable Long votingSessionId) {
        return ResponseEntity.ok(votingSessionAdapter.handleRequest(votingSessionId));
    }
}
