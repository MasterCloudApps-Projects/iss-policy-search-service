package es.urjc.code.policysearch.application.port.incoming;

import es.codeurjc.policysearch.command.bus.QueryHandler;
import es.urjc.code.policysearch.service.api.v1.queries.findpolicy.FindPolicyQuery;
import es.urjc.code.policysearch.service.api.v1.queries.findpolicy.FindPolicyQueryResult;

public interface FindPolicyUseCase extends QueryHandler<FindPolicyQueryResult, FindPolicyQuery> {
	public FindPolicyQueryResult handle(FindPolicyQuery query) ;
}
