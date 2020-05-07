package com.onecloud.tcmgmt.domain.appdb;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUCT")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Product extends IdentifiableEntity implements Comparable{

    private static final long serialVersionUID = 3956729267360500809L;

    private String product;

    @Column(name="PRODUCT")
    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        boolean isEqual = false;
        if (this == o) {
            return true;
        } else if (this.getId() == null) {
            return false;
        } else if (o instanceof Product) {
            Product that = (Product) o;
            if(this.getId().equals(that.getId())){
               if(this.getProduct().compareTo(that.getProduct())==0){
                           isEqual=true;
                   }
               }
            }
        return isEqual;
    }

    @Override
    public int hashCode() {
        return this.getProduct() == null ? System.identityHashCode(this)
                                        : 17 * this.getProduct().hashCode();
    }

    @Override
    public String toString() {
        return this.getProduct();
    }

    @Override
    public int compareTo(Object o) {
        int isEqual = -1;
        if (this == o) {
            return 0;
        } else if (this.getId() == null) {
            return 10000000;
        } else if (o instanceof Product) {
            Product that = (Product) o;
            if (this.getId().equals(that.getId())) {
                if (this.getProduct().compareTo(that.getProduct()) == 0) {
                    isEqual = 0;
                } else {
                return this.getProduct().compareTo(that.getProduct());
                }
            } else {
                return this.getId().intValue() - that.getId().intValue();
            }
    }
        return isEqual;
    }
}