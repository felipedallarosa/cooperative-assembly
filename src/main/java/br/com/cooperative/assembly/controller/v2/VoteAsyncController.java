package br.com.cooperative.assembly.controller.v2;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cooperative.assembly.controller.request.VoteRequest;
import br.com.cooperative.assembly.controller.v1.response.VoteResponse;
import br.com.cooperative.assembly.controller.v2.adapter.VoteAsyncAdapter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/v2/vote")
@Api(
    value = "Management of Async Vote",
    tags = {"Management of Async Vote"})
public class VoteAsyncController {

    private VoteAsyncAdapter voteAsyncAdapter;

    public VoteAsyncController(final VoteAsyncAdapter voteAsyncAdapter) {
        this.voteAsyncAdapter = voteAsyncAdapter;
    }

    @PostMapping(value="/{votingSessionId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Register Member Vote")
    public ResponseEntity<Void> registryAsyncVote(@PathVariable Long votingSessionId,
                                                     @Valid @RequestBody VoteRequest voteRequest) {
        voteAsyncAdapter.handleRequest(votingSessionId, voteRequest);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @GetMapping(value="/{votingSessionId}/{document}")
    @ApiOperation(value = "Get Status Member Vote")
    public ResponseEntity<VoteResponse> registryVote(@PathVariable Long votingSessionId,
        @PathVariable String document) {
        return ResponseEntity.status(HttpStatus.CREATED).body(voteAsyncAdapter.handleRequest(votingSessionId, document));
    }
}
