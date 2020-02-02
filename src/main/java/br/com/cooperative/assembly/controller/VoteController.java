package br.com.cooperative.assembly.controller;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cooperative.assembly.controller.adapter.VoteAdapter;
import br.com.cooperative.assembly.controller.request.VoteRequest;
import br.com.cooperative.assembly.controller.response.VoteResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/v1/vote", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@Api(
    value = "Management of Vote",
    tags = {"Management of Vote"})
public class VoteController {

    private VoteAdapter voteAdapter;

    public VoteController(final VoteAdapter voteAdapter) {
        this.voteAdapter = voteAdapter;
    }

    @PostMapping(value="/{votingSessionId}")
    @ApiOperation(value = "Register Member Vote")
    public ResponseEntity<VoteResponse> registryVote(@PathVariable Long votingSessionId,
                                                     @Valid @RequestBody VoteRequest voteRequest) {
        return ResponseEntity.ok(voteAdapter.handleRequest(votingSessionId, voteRequest));
    }
}
