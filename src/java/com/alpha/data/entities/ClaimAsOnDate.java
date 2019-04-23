/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alpha.data.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Tsotsoh
 */
@Entity
@Table(name = "claim_as_on_date")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ClaimAsOnDate.findAll", query = "SELECT c FROM ClaimAsOnDate c")
    , @NamedQuery(name = "ClaimAsOnDate.findByClaimNumber", query = "SELECT c FROM ClaimAsOnDate c WHERE c.claimNumber = :claimNumber")
    , @NamedQuery(name = "ClaimAsOnDate.findByProductName", query = "SELECT c FROM ClaimAsOnDate c WHERE c.productName = :productName")
    , @NamedQuery(name = "ClaimAsOnDate.findByStatusDate", query = "SELECT c FROM ClaimAsOnDate c WHERE c.statusDate = :statusDate")
    , @NamedQuery(name = "ClaimAsOnDate.findByReportedDate", query = "SELECT c FROM ClaimAsOnDate c WHERE c.reportedDate = :reportedDate")
    , @NamedQuery(name = "ClaimAsOnDate.findByClaimsAllocatedTo", query = "SELECT c FROM ClaimAsOnDate c WHERE c.claimsAllocatedTo = :claimsAllocatedTo")
    , @NamedQuery(name = "ClaimAsOnDate.findByPercentageComplianceToWorkflow", query = "SELECT c FROM ClaimAsOnDate c WHERE c.percentageComplianceToWorkflow = :percentageComplianceToWorkflow")
    , @NamedQuery(name = "ClaimAsOnDate.claimsCountByAgent",
            query = "SELECT COUNT(c) FROM ClaimAsOnDate c WHERE c.claimsAllocatedTo = :claimsAllocatedTo")
    , @NamedQuery(name = "ClaimAsOnDate.ClaimsCountByAgentAndDesc",
            query = "SELECT COUNT(c) FROM ClaimAsOnDate c WHERE c.claimsAllocatedTo = :claimsAllocatedTo AND c.shortDescription = :shortDesc")
    , @NamedQuery(name = "ClaimAsOnDate.ClaimsCountByDesc",
            query = "SELECT COUNT(c) FROM ClaimAsOnDate c WHERE c.shortDescription = :shortDesc")
    , @NamedQuery(name = "ClaimAsOnDate.claimsCountByReportedDate",
            query = "SELECT COUNT(c) FROM ClaimAsOnDate c WHERE c.reportedDate >= :date AND c.reportedDate <= :date")
    , @NamedQuery(name = "ClaimAsOnDate.claimsCountByStatusDate",
            query = "SELECT COUNT(c) FROM ClaimAsOnDate c WHERE c.statusDate >= :date AND c.statusDate < :date")
    , @NamedQuery(name = "ClaimAsOnDate.claimsCountByReportedDateAndAgent",
            query = "SELECT COUNT(c) FROM ClaimAsOnDate c WHERE c.reportedDate = :date AND c.claimsAllocatedTo = :claimsAllocatedTo")
    , @NamedQuery(name = "ClaimAsOnDate.claimsCountByAgentBetweenStatusDates",
            query = "SELECT COUNT(c) FROM ClaimAsOnDate c WHERE c.claimsAllocatedTo = :claimsAllocatedTo AND c.statusDate BETWEEN :startDate AND :endDate")
    , @NamedQuery(name = "ClaimAsOnDate.claimsCountByAgentAndDescBetweenStatusDates", 
            query = "SELECT COUNT(c) FROM ClaimAsOnDate c WHERE c.claimsAllocatedTo = :claimsAllocatedTo AND c.shortDescription = :desc AND c.statusDate BETWEEN :startDate AND :endDate")
    , @NamedQuery(name = "ClaimAsOnDate.claimsCountByAgentBetweenReportedDates",
            query = "SELECT COUNT(c) FROM ClaimAsOnDate c WHERE c.claimsAllocatedTo = :claimsAllocatedTo AND c.reportedDate BETWEEN :startDate AND :endDate")
    , @NamedQuery(name = "ClaimAsOnDate.NonMotorClaimsCountByAgentBetweenStatusDates", 
            query = "SELECT COUNT(c) FROM ClaimAsOnDate c WHERE c.claimsAllocatedTo = :claimsAllocatedTo AND c.shortDescription != 'Motor Accident Claim ' "
                    + "AND c.shortDescription != 'Glass Claim' AND c.statusDate BETWEEN :startDate AND :endDate")
        , @NamedQuery(name = "ClaimAsOnDate.claimsDifferenceByAgent", 
            query = "SELECT FUNCTION('DATEDIFF', c.statusDate, c.reportedDate) AS numberOfDays FROM ClaimAsOnDate c WHERE c.claimsAllocatedTo = :claimsAllocatedTo")
    , @NamedQuery(name = "ClaimAsOnDate.claimsDifferenceByAgentAndDesc", 
            query = "SELECT FUNCTION('DATEDIFF', c.statusDate, c.reportedDate) AS numberOfDays FROM ClaimAsOnDate c WHERE c.claimsAllocatedTo = :claimsAllocatedTo AND c.shortDescription = :desc")
    , @NamedQuery(name = "ClaimAsOnDate.daysDifferenceByAgentAndDescAndDates", 
            query = "SELECT FUNCTION('DATEDIFF', c.statusDate, c.reportedDate) AS numberOfDays FROM ClaimAsOnDate c WHERE c.claimsAllocatedTo = :claimsAllocatedTo AND c.shortDescription = :desc AND c.statusDate BETWEEN :startDate AND :endDate")
    , @NamedQuery(name = "ClaimAsOnDate.nonMotorDaysDifferenceByAgentAndDates", 
            query = "SELECT FUNCTION('DATEDIFF', c.statusDate, c.reportedDate) FROM ClaimAsOnDate c WHERE c.claimsAllocatedTo = :claimsAllocatedTo AND c.shortDescription != 'Motor Accident Claim ' "
                    + "AND c.shortDescription != 'Glass Claim' AND c.statusDate BETWEEN :startDate AND :endDate")
    , @NamedQuery(name = "ClaimAsOnDate.findGroupedByMonth", 
            query = "SELECT Count(c) AS counts, FUNCTION('MONTH', c.statusDate) AS months FROM ClaimAsOnDate c WHERE c.claimsAllocatedTo = :claimsAllocatedTo AND c.shortDescription = :desc AND"
                    + " c.statusDate BETWEEN :startDate AND :endDate GROUP BY FUNCTION('DATE_FORMAT', c.statusDate, '%Y-%m')")})
public class ClaimAsOnDate implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "claim_number")
    private String claimNumber;
    @Basic(optional = false)
    @Column(name = "product_name")
    private String productName;
    @Basic(optional = false)
    @Column(name = "status_date")
    @Temporal(TemporalType.DATE)
    private Date statusDate;
    @Basic(optional = false)
    @Column(name = "reported_date")
    @Temporal(TemporalType.DATE)
    private Date reportedDate;
    @Basic(optional = false)
    @Lob
    @Column(name = "short_description")
    private String shortDescription;
    @Basic(optional = false)
    @Column(name = "claims_allocated_to")
    private String claimsAllocatedTo;
    @Column(name = "percentage_compliance_to_workflow")
    private Integer percentageComplianceToWorkflow;

    public ClaimAsOnDate() {
    }

    public ClaimAsOnDate(String claimNumber) {
        this.claimNumber = claimNumber;
    }

    public ClaimAsOnDate(String claimNumber, String productName, Date statusDate, Date reportedDate, String shortDescription, String claimsAllocatedTo) {
        this.claimNumber = claimNumber;
        this.productName = productName;
        this.statusDate = statusDate;
        this.reportedDate = reportedDate;
        this.shortDescription = shortDescription;
        this.claimsAllocatedTo = claimsAllocatedTo;
    }

    public String getClaimNumber() {
        return claimNumber;
    }

    public void setClaimNumber(String claimNumber) {
        this.claimNumber = claimNumber;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Date getStatusDate() {
        return statusDate;
    }

    public void setStatusDate(Date statusDate) {
        this.statusDate = statusDate;
    }

    public Date getReportedDate() {
        return reportedDate;
    }

    public void setReportedDate(Date reportedDate) {
        this.reportedDate = reportedDate;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getClaimsAllocatedTo() {
        return claimsAllocatedTo;
    }

    public void setClaimsAllocatedTo(String claimsAllocatedTo) {
        this.claimsAllocatedTo = claimsAllocatedTo;
    }

    public Integer getPercentageComplianceToWorkflow() {
        return percentageComplianceToWorkflow;
    }

    public void setPercentageComplianceToWorkflow(Integer percentageComplianceToWorkflow) {
        this.percentageComplianceToWorkflow = percentageComplianceToWorkflow;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (claimNumber != null ? claimNumber.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClaimAsOnDate)) {
            return false;
        }
        ClaimAsOnDate other = (ClaimAsOnDate) object;
        if ((this.claimNumber == null && other.claimNumber != null) || (this.claimNumber != null && !this.claimNumber.equals(other.claimNumber))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.alpha.data.entities.ClaimAsOnDate[ claimNumber=" + claimNumber + " ]";
    }

}
