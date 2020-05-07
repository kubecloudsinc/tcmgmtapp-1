
package com.onecloud.tcmgmt.domain.appdb;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "DEFECT_STATUS")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DefectStatus extends IdentifiableEntity implements Comparable{

    private static final long serialVersionUID = 2925289267360500809L;

    private String status;

    @Column(name="STATUS")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        boolean isEqual = false;
        if (this == o) {
            return true;
        } else if (this.getId() == null) {
            return false;
        } else if (o instanceof DefectStatus) {
            DefectStatus that = (DefectStatus) o;
            if(this.getId().equals(that.getId())){
               if(this.getStatus().compareTo(that.getStatus())==0){
                           isEqual=true;
                   }
               }
            }
        return isEqual;
    }

    @Override
    public int hashCode() {
        return this.getStatus() == null ? System.identityHashCode(this)
                                        : 17 * this.getStatus().hashCode();
    }

    @Override
    public String toString() {
        return this.getStatus();
    }

    @Override
    public int compareTo(Object o) {
        int isEqual = -1;
        if (this == o) {
            return 0;
        } else if (this.getId() == null) {
            return 10000000;
        } else if (o instanceof DefectStatus) {
            DefectStatus that = (DefectStatus) o;
            if (this.getId().equals(that.getId())) {
                if (this.getStatus().compareTo(that.getStatus()) == 0) {
                    isEqual = 0;
                } else {
                return this.getStatus().compareTo(that.getStatus());
                }
            } else {
                return this.getId().intValue() - that.getId().intValue();
            }
    }

        return isEqual;
    }
}