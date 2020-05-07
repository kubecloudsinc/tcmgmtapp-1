
package com.onecloud.tcmgmt.domain.appdb;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "DEFECT_PRIORITY")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Priority extends IdentifiableEntity implements Comparable{

    private static final long serialVersionUID = 2925289267360500809L;

    private String priority;

    @Column(name="PRIORITY")
    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    @Override
    public boolean equals(Object o) {
        boolean isEqual = false;
        if (this == o) {
            return true;
        } else if (this.getId() == null) {
            return false;
        } else if (o instanceof Priority) {
            Priority that = (Priority) o;
            if(this.getId().equals(that.getId())){
               if(this.getPriority().compareTo(that.getPriority())==0){
                           isEqual=true;
                   }
               }
            }
        return isEqual;
    }

    @Override
    public int hashCode() {
        return this.getPriority() == null ? System.identityHashCode(this)
                                        : 17 * this.getPriority().hashCode();
    }

    @Override
    public String toString() {
        return this.getPriority();
    }

    @Override
    public int compareTo(Object o) {
        int isEqual = -1;
        if (this == o) {
            return 0;
        } else if (this.getId() == null) {
            return 10000000;
        } else if (o instanceof Priority) {
            Priority that = (Priority) o;
            if (this.getId().equals(that.getId())) {
                if (this.getPriority().compareTo(that.getPriority()) == 0) {
                    isEqual = 0;
                } else {
                return this.getPriority().compareTo(that.getPriority());
                }
            } else {
                return this.getId().intValue() - that.getId().intValue();
            }
    }

        return isEqual;
    }
}