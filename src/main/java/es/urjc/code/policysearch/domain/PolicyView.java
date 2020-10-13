package es.urjc.code.policysearch.domain;

import java.time.LocalDate;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class PolicyView {
	
    private String number;
    private LocalDate dateFrom;
    private LocalDate dateTo;
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

        if (!(o instanceof PolicyView)) return false;

        PolicyView that = (PolicyView) o;

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

        private final PolicyView object;

        public Builder() {
            object = new PolicyView();
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

        public PolicyView build() {
            return object;
        }

    }

}
