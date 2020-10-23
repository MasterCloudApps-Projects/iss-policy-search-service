package es.urjc.code.policysearch.application.port.outgoing;

import java.util.List;

import es.urjc.code.policysearch.domain.PolicyView;

public interface PolicyViewPort {

	public List<PolicyView> findAll(String queryText);
	public void save(PolicyView policyView);
}
