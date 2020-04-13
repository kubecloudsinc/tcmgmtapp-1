
package com.onecloud.autotools.domain.appdb;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "REGIONS")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@AttributeOverride(name = "id", column = @Column(name = "REGION_ID"))
public class Region extends IdentifiableEntity {

    private static final long serialVersionUID = 2993569267760500809L;

    private String regionName;

    @Size(max = 25)
    @Column(name="REGION_NAME", length = 20, nullable = false)
    public String getRegionName() {
        return this.regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (this.getRegionName() == null) {
            return false;
        } else if (o instanceof Region) {
            Region that = (Region) o;
            return this.getRegionName().equals(that.getRegionName());
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return this.getRegionName() == null ? System.identityHashCode(this) : 17 * this.getRegionName()
                .hashCode();
    }

    @Override
    public String toString() {
        return this.getRegionName();
    }
}