package es.urjc.code.policysearch.service.api.v1.queries.findpolicy;

import javax.validation.constraints.NotEmpty;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import es.codeurjc.policysearch.command.api.Query;

public class FindPolicyQuery implements Query<FindPolicyQueryResult>{
	
	@NotEmpty
	private String queryText;
	
    public String getQueryText() {
        return queryText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof FindPolicyQuery)) return false;

        FindPolicyQuery that = (FindPolicyQuery) o;

        return new EqualsBuilder()
                .append(queryText, that.queryText)
                .isEquals();
    }


    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(queryText)
                .toHashCode();
    }
    
    public static final class Builder {

        private final FindPolicyQuery object;

        public Builder() {
            object = new FindPolicyQuery();
        }

        public Builder withQueryText(String value) {
            object.queryText = value;
            return this;
        }

        public FindPolicyQuery build() {
            return object;
        }

    }
}
