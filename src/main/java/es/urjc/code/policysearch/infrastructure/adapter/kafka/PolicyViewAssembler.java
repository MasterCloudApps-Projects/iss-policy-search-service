package es.urjc.code.policysearch.infrastructure.adapter.kafka;

import org.springframework.stereotype.Component;

import es.urjc.code.policysearch.domain.PolicyView;
import es.urjc.code.policysearch.service.api.v1.events.dto.PolicyDto;



@Component
public class PolicyViewAssembler {

   public PolicyView map(PolicyDto policy) {
       return new PolicyView.Builder()
               .withNumber(policy.getNumber())
               .withDateFrom(policy.getFrom())
               .withDateTo(policy.getTo())
               .withPolicyHolder(policy.getPolicyHolder())
               .build();
   }
}
