package es.urjc.code.policysearch.infrastructure.adapter.repository.es;

import java.util.List;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import es.urjc.code.policysearch.domain.PolicyView;

@Component
@ConditionalOnProperty(name = "elasticsearch.repository.enabled", havingValue = "true", matchIfMissing = true)
public class ElasticPolicyViewRepository implements PolicyViewRepository {

	@Override
	public List<PolicyView> findAll(String queryText) {
		return null;
	}

	@Override
	public void save(PolicyView policyView) {
	}

	@Override
	public PolicyView findByPolicyNumber(String policyNumber) {
		return null;
	}

}
