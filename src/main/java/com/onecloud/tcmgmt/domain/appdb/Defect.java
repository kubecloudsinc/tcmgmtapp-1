
package com.onecloud.tcmgmt.domain.appdb;

import com.onecloud.tcmgmt.semantic.dto.TestCaseDTO;
import org.apache.commons.collections.CollectionUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.SortNatural;
import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.support.PagedListHolder;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

@Entity
@Table(name = "DEFECT")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Defect extends IdentifiableEntity implements Comparable{

    private static final Logger logger = LoggerFactory.getLogger(Defect.class);

    private static final long serialVersionUID = 3L;

    private String name;

    private String description;

    private Long product;

    private Long component;

    private Long priority;

    private Long severity;

    private Long status;

    private String reportedBy;

    private PagedListHolder<Defect> defectPageList= new PagedListHolder<Defect>();

    @Size(max = 25)
    @NotEmpty
    @Column(name="NAME", length = 25)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Size(max = 200)
    @Column(name="DESCRIPTION", length = 200)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name="PRODUCT_ID")
    public Long getProduct() {
        return product;
    }

    public void setProduct(Long product) {
        this.product = product;
    }

    @Column(name="COMPONENT_ID")
    public Long getComponent() {
        return component;
    }

    public void setComponent(Long component) {
        this.component = component;
    }

    @Column(name="PRIORITY_ID")
    public Long getPriority() {
        return priority;
    }

    public void setPriority(Long priority) {
        this.priority = priority;
    }

    @Column(name="SEVERITY_ID")
    public Long getSeverity() {
        return severity;
    }

    public void setSeverity(Long severity) {
        this.severity = severity;
    }

    @Column(name="STATUS_ID")
    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    @Column(name="REPORTER")
    public String getReportedBy() {
        return reportedBy;
    }

    public void setReportedBy(String reportedBy) {
        this.reportedBy = reportedBy;
    }

    @Transient
    public PagedListHolder<Defect> getDefectPageList() {
        return defectPageList;
    }

    public void setDefectPageList(PagedListHolder<Defect> defectPageList) {
        this.defectPageList = defectPageList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (this.getName() == null) {
            return false;
        } else if (o instanceof Defect) {
            Defect that = (Defect) o;
            return this.getName().equals(that.getName());
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return this.getName() == null ? System.identityHashCode(this)
                            : 17 * this.getName().hashCode();
    }

    @Override
    public String toString() {
        return this.getName();
    }

    @Override
    public int compareTo(Object o) {

        if (this == o) {
            return 0;
        } else if (this.getName() == null) {
            return -1;
        } else if (o instanceof Defect) {
            Defect that = (Defect) o;
            return this.getName().compareTo(that.getName());
        } else {
            return -1;
        }
    }

    public void createUpdateFromAnother(Defect anotherDefect){
        logger.debug("another defect is: "+(anotherDefect!=null));

        if(anotherDefect!=null){
            if(!anotherDefect.getName().equals(this.name)){
                setName(anotherDefect.getName());
            }
            if(!anotherDefect.getDescription().equals(this.description)){
                setDescription(anotherDefect.getDescription());
            }
            if(anotherDefect.getComponent().intValue()!=this.component.intValue()){
                setComponent(anotherDefect.getComponent());
            }
            if(anotherDefect.getPriority().intValue()!=this.priority.intValue()){
                setPriority(anotherDefect.getPriority());
            }
            if(anotherDefect.getProduct().intValue()!=this.product.intValue()){
                setProduct(anotherDefect.getProduct());
            }
            if(anotherDefect.getSeverity().intValue()!=this.severity.intValue()){
                setSeverity(anotherDefect.getSeverity());
            }
            if(anotherDefect.getStatus().intValue()!=this.status.intValue()){
                setStatus(anotherDefect.getStatus());
            }
            if(!anotherDefect.getReportedBy().equals(this.reportedBy)){
                setReportedBy(anotherDefect.getReportedBy());
            }
        }
    }
}