package es.urjc.code.policysearch.infrastructure.adapter.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.urjc.code.policysearch.application.port.outgoing.PolicyViewLoadPort;
import es.urjc.code.policysearch.application.port.outgoing.PolicyViewUpdatePort;
import es.urjc.code.policysearch.domain.PolicyView;
import es.urjc.code.policysearch.infrastructure.adapter.repository.es.PolicyViewRepository;

@Service
@Transactional
public class PolicyRepositoryAdapter implements PolicyViewLoadPort, PolicyViewUpdatePort {

	private final PolicyViewRepository policyViewRepository;
	
	@Autowired
	public PolicyRepositoryAdapter(PolicyViewRepository policyViewRepository) {
		this.policyViewRepository = policyViewRepository;
	}

	@Override
	public List<PolicyView> findAll(String queryText) {
		return policyViewRepository.findAll(queryText);
	}

	@Override
	public void save(PolicyView policyView) {
		policyViewRepository.save(policyView);
	}

}
