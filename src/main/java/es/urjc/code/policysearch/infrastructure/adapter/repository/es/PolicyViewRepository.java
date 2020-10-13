package es.urjc.code.policysearch.infrastructure.adapter.repository.es;

import java.util.List;

import es.urjc.code.policysearch.domain.PolicyView;

public interface PolicyViewRepository {

	List<PolicyView> findAll(String queryText);
	void save(PolicyView policyView);
}
