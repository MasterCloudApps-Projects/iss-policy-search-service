package es.urjc.code.policysearch.infrastructure.adapter.repository.es;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;

import es.urjc.code.policysearch.domain.PolicyView;

@Component
@ConditionalOnMissingBean(ElasticPolicyViewRepository.class)
public class MockPolicyViewRepository implements PolicyViewRepository {

    private Map<String, PolicyView> policyMap = init();
    
	@Override
	public List<PolicyView> findAll(String queryTest) {
		return new ArrayList<>(policyMap.values());
	}

	@Override
	public void save(PolicyView policyView) {
		 var pview = policyMap.get(policyView.getNumber());
		 if (pview!=null) {
			policyMap.remove(policyView.getNumber()); 
		 }
		 policyMap.put(policyView.getNumber(), policyView);
	}

    private Map<String, PolicyView> init() {
        Map<String, PolicyView> map = new LinkedHashMap<>();

        map.put("1234", new PolicyView.Builder().withNumber("1234").withDateFrom(LocalDate.of(2019, 1, 1)).withDateTo(LocalDate.of(2020, 1, 1)).withPolicyHolder("Xxxx Yyyy").build());
        map.put("1235", new PolicyView.Builder().withNumber("1235").withDateFrom(LocalDate.of(2019, 1, 1)).withDateTo(LocalDate.of(2020, 1, 1)).withPolicyHolder("Xxxx Yyyy").build());
        map.put("1236", new PolicyView.Builder().withNumber("1236").withDateFrom(LocalDate.of(2019, 1, 1)).withDateTo(LocalDate.of(2020, 1, 1)).withPolicyHolder("Xxxx Yyyy").build());
        map.put("1237", new PolicyView.Builder().withNumber("1237").withDateFrom(LocalDate.of(2019, 1, 1)).withDateTo(LocalDate.of(2020, 1, 1)).withPolicyHolder("Xxxx Yyyy").build());
        return map;
    }

	@Override
	public PolicyView findByPolicyNumber(String policyNumber) {
		return policyMap.get(policyNumber);
	}
}
