package es.urjc.code.policysearch.service.api.v1.queries.findpolicy.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class PolicyListItemDto {
	@NotEmpty
    private String number;
	@NotNull
    private LocalDate dateFrom;
	@NotNull
    private LocalDate dateTo;
	@NotEmpty
    private String policyHolder;
    
    public String getNumber() {
        return number;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public String getPolicyHolder() {
        return policyHolder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof PolicyListItemDto)) return false;

        PolicyListItemDto that = (PolicyListItemDto) o;

        return new EqualsBuilder()
                .append(number, that.number)
                .append(dateFrom, that.dateFrom)
                .append(dateTo, that.dateTo)
                .append(policyHolder, that.policyHolder)
                .isEquals();
    }


    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(number)
                .append(dateFrom)
                .append(dateTo)
                .append(policyHolder)
                .toHashCode();
    }
    
    public static final class Builder {

        private final PolicyListItemDto object;

        public Builder() {
            object = new PolicyListItemDto();
        }

        public Builder withNumber(String value) {
            object.number = value;
            return this;
        }

        public Builder withDateFrom(LocalDate value) {
            object.dateFrom = value;
            return this;
        }

        public Builder withDateTo(LocalDate value) {
            object.dateTo = value;
            return this;
        }

        public Builder withPolicyHolder(String value) {
            object.policyHolder = value;
            return this;
        }

        public PolicyListItemDto build() {
            return object;
        }

    }    
}
