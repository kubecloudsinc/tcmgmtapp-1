
package com.onecloud.tcmgmt.domain.appdb;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "COMPONENT")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Component extends IdentifiableEntity implements Comparable{

    private static final long serialVersionUID = 4952799267360500809L;

    private String component;

    @Column(name="COMPONENT")
    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    @Override
    public boolean equals(Object o) {
        boolean isEqual = false;
        if (this == o) {
            return true;
        } else if (this.getId() == null) {
            return false;
        } else if (o instanceof Component) {
            Component that = (Component) o;
            if(this.getId().equals(that.getId())){
               if(this.getComponent().compareTo(that.getComponent())==0){
                           isEqual=true;
                   }
               }
            }
        return isEqual;
    }

    @Override
    public int hashCode() {
        return this.getComponent() == null ? System.identityHashCode(this)
                                        : 17 * this.getComponent().hashCode();
    }

    @Override
    public String toString() {
        return this.getComponent();
    }

    @Override
    public int compareTo(Object o) {
        int isEqual = -1;
        if (this == o) {
            return 0;
        } else if (this.getId() == null) {
            return 10000000;
        } else if (o instanceof Component) {
            Component that = (Component) o;
            if (this.getId().equals(that.getId())) {
                if (this.getComponent().compareTo(that.getComponent()) == 0) {
                    isEqual = 0;
                } else {
                return this.getComponent().compareTo(that.getComponent());
                }
            } else {
                return this.getId().intValue() - that.getId().intValue();
            }
    }

        return isEqual;
    }
}