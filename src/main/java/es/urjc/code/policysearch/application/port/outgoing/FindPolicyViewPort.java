package es.urjc.code.policysearch.application.port.outgoing;

import java.util.List;

import es.urjc.code.policysearch.domain.PolicyView;

public interface FindPolicyViewPort {

	public List<PolicyView> findAll(String queryText);
}
