package es.urjc.code.policysearch.application.port.outgoing;

import es.urjc.code.policysearch.domain.PolicyView;

public interface PolicyViewUpdatePort {

	public void save(PolicyView policyView);
}
