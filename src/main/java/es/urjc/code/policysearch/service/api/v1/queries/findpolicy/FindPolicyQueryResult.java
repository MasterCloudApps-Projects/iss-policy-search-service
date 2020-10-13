package es.urjc.code.policysearch.service.api.v1.queries.findpolicy;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import es.urjc.code.policysearch.service.api.v1.queries.findpolicy.dto.PolicyListItemDto;

public class FindPolicyQueryResult {
	
	@NotNull
	private List<PolicyListItemDto> policies;
	
    public List<PolicyListItemDto> getPolicies() {
        return policies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof FindPolicyQueryResult)) return false;

        FindPolicyQueryResult that = (FindPolicyQueryResult) o;

        return new EqualsBuilder()
                .append(policies, that.policies)
                .isEquals();
    }


    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(policies)
                .toHashCode();
    }
    
    public static final class Builder {

        private final FindPolicyQueryResult object;

        public Builder() {
            object = new FindPolicyQueryResult();
        }

        public Builder withPolicies(List<PolicyListItemDto> value) {
            object.policies = value;
            return this;
        }

        public FindPolicyQueryResult build() {
            return object;
        }

    }

}
