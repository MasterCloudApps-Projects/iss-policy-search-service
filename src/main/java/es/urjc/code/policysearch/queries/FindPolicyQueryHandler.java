package es.urjc.code.policysearch.queries;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import es.codeurjc.policysearch.command.bus.QueryHandler;
import es.urjc.code.policysearch.application.port.outgoing.FindPolicyViewPort;
import es.urjc.code.policysearch.domain.PolicyView;
import es.urjc.code.policysearch.service.api.v1.queries.findpolicy.FindPolicyQuery;
import es.urjc.code.policysearch.service.api.v1.queries.findpolicy.FindPolicyQueryResult;

@Component
public class FindPolicyQueryHandler implements QueryHandler<FindPolicyQueryResult, FindPolicyQuery> {
	
	private final FindPolicyViewPort findPolicyViewPort;
	private final PolicyQueryResultAssembler policyQueryResultAssembler;
	
	@Autowired
	public FindPolicyQueryHandler(FindPolicyViewPort findPolicyViewPort,PolicyQueryResultAssembler policyQueryResultAssembler) {
		this.findPolicyViewPort = findPolicyViewPort;
		this.policyQueryResultAssembler = policyQueryResultAssembler;
	}

	@Transactional
	@Override
	public FindPolicyQueryResult handle(FindPolicyQuery query) {
		final List<PolicyView> policyViews = findPolicyViewPort.findAll(query.getQueryText());
		return policyQueryResultAssembler.constructResult(policyViews);
	}

}
