package es.urjc.code.policysearch.infrastructure.adapter.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import es.codeurjc.policysearch.command.bus.Bus;
import es.urjc.code.policysearch.service.api.v1.queries.findpolicy.FindPolicyQuery;
import es.urjc.code.policysearch.service.api.v1.queries.findpolicy.FindPolicyQueryResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "policies search", description = "the policies search API")
@Validated
public class PoliciesSearchQueryController {

	private final Bus bus;
	
	@Autowired
	public PoliciesSearchQueryController(Bus bus) {
		this.bus = bus;
	}

	@Operation(summary = "Find policies by query text", description = "Returns a list of policies", tags = { "policies search" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = FindPolicyQueryResult.class))) })
    @GetMapping("/api/v1/policies/{queryText}")
    public ResponseEntity<FindPolicyQueryResult> policies(@Parameter(description = "Query text. Cannot be empty.", required = true) @Valid @PathVariable("queryText") @NotEmpty  String queryText) {
    	return ResponseEntity.status(HttpStatus.OK).body(bus.executeQuery(new FindPolicyQuery.Builder().withQueryText(queryText).build()));
    }
}
