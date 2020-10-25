package es.urjc.code.policysearch.queries;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import es.urjc.code.policysearch.domain.PolicyView;
import es.urjc.code.policysearch.service.api.v1.queries.findpolicy.FindPolicyQueryResult;
import es.urjc.code.policysearch.service.api.v1.queries.findpolicy.dto.PolicyListItemDto;

@Component
public class PolicyQueryResultAssembler {

    public FindPolicyQueryResult constructResult(List<PolicyView> policies) {
        return new FindPolicyQueryResult.Builder().withPolicies(policies.stream()
                .map(this::toPolicyListItemDto)
                .sorted(Comparator.comparing(PolicyListItemDto::getDateFrom, Comparator.nullsLast(Comparator.reverseOrder())))
                .collect(Collectors.toList())).build();
    }
    
    
	private PolicyListItemDto toPolicyListItemDto(PolicyView policy) {
        return new PolicyListItemDto.Builder()
        		                    .withNumber(policy.getNumber())
        		                    .withDateFrom(policy.getDateFrom())
        		                    .withDateTo(policy.getDateTo())
        		                    .withPolicyHolder(policy.getPolicyHolder())
        		                    .build();
    }
}
