/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alpha.data.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Tsotsoh
 */
@Entity
@Table(name = "assessment_cost_per_claim")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AssessmentCostPerClaim.findAll", query = "SELECT a FROM AssessmentCostPerClaim a")
    , @NamedQuery(name = "AssessmentCostPerClaim.findById", query = "SELECT a FROM AssessmentCostPerClaim a WHERE a.id = :id")
    , @NamedQuery(name = "AssessmentCostPerClaim.findByReportedDate", query = "SELECT a FROM AssessmentCostPerClaim a WHERE a.reportedDate = :reportedDate")
    , @NamedQuery(name = "AssessmentCostPerClaim.findByTransSubType", query = "SELECT a FROM AssessmentCostPerClaim a WHERE a.transSubType = :transSubType")
    , @NamedQuery(name = "AssessmentCostPerClaim.findByRservePaymentAmt", query = "SELECT a FROM AssessmentCostPerClaim a WHERE a.rservePaymentAmt = :rservePaymentAmt")
    , @NamedQuery(name = "AssessmentCostPerClaim.findCountBetweenDates", 
            query = "SELECT COUNT(a) FROM AssessmentCostPerClaim a WHERE a.reportedDate BETWEEN :startDate AND :endDate")
    , @NamedQuery(name = "AssessmentCostPerClaim.findBetweenDates", 
            query = "SELECT a FROM AssessmentCostPerClaim a WHERE a.reportedDate BETWEEN :startDate AND :endDate")})
public class AssessmentCostPerClaim implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "reported_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date reportedDate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "trans_sub_type")
    private String transSubType;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "rserve_payment_amt")
    private BigDecimal rservePaymentAmt;

    public AssessmentCostPerClaim() {
    }

    public AssessmentCostPerClaim(Integer id) {
        this.id = id;
    }

    public AssessmentCostPerClaim(Integer id, Date reportedDate, String transSubType, BigDecimal rservePaymentAmt) {
        this.id = id;
        this.reportedDate = reportedDate;
        this.transSubType = transSubType;
        this.rservePaymentAmt = rservePaymentAmt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getReportedDate() {
        return reportedDate;
    }

    public void setReportedDate(Date reportedDate) {
        this.reportedDate = reportedDate;
    }

    public String getTransSubType() {
        return transSubType;
    }

    public void setTransSubType(String transSubType) {
        this.transSubType = transSubType;
    }

    public BigDecimal getRservePaymentAmt() {
        return rservePaymentAmt;
    }

    public void setRservePaymentAmt(BigDecimal rservePaymentAmt) {
        this.rservePaymentAmt = rservePaymentAmt;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AssessmentCostPerClaim)) {
            return false;
        }
        AssessmentCostPerClaim other = (AssessmentCostPerClaim) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.alpha.data.entities.AssessmentCostPerClaim[ id=" + id + " ]";
    }
    
}
