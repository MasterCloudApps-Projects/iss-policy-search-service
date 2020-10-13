package es.urjc.code.policysearch.application.port.outgoing;

import es.urjc.code.policysearch.domain.PolicyView;

public interface UpdatePolicyViewPort {

	public void save(PolicyView policyView);
}
