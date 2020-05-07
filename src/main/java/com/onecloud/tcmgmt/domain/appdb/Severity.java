
package com.onecloud.tcmgmt.domain.appdb;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "DEFECT_SEVERITY")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Severity extends IdentifiableEntity implements Comparable{

    private static final long serialVersionUID = 3956729267360500809L;

    private String severity;

    @Column(name="SEVERITY")
    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    @Override
    public boolean equals(Object o) {
        boolean isEqual = false;
        if (this == o) {
            return true;
        } else if (this.getId() == null) {
            return false;
        } else if (o instanceof Severity) {
            Severity that = (Severity) o;
            if(this.getId().equals(that.getId())){
               if(this.getSeverity().compareTo(that.getSeverity())==0){
                           isEqual=true;
                   }
               }
            }
        return isEqual;
    }

    @Override
    public int hashCode() {
        return this.getSeverity() == null ? System.identityHashCode(this)
                                        : 17 * this.getSeverity().hashCode();
    }

    @Override
    public String toString() {
        return this.getSeverity();
    }

    @Override
    public int compareTo(Object o) {
        int isEqual = -1;
        if (this == o) {
            return 0;
        } else if (this.getId() == null) {
            return 10000000;
        } else if (o instanceof Severity) {
            Severity that = (Severity) o;
            if (this.getId().equals(that.getId())) {
                if (this.getSeverity().compareTo(that.getSeverity()) == 0) {
                    isEqual = 0;
                } else {
                return this.getSeverity().compareTo(that.getSeverity());
                }
            } else {
                return this.getId().intValue() - that.getId().intValue();
            }
    }

        return isEqual;
    }
}