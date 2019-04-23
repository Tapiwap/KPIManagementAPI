/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alpha.presentation.beans;

import java.util.Date;

/**
 *
 * @author Tsotsoh
 */
public class ClaimAsOnDate {
    
    private Integer claimNumber;
    private String productName;
    private Date statusDate;
    private Date reportedDate;
    private String shortDescription;
    private String claimsAllocatedTo;
    private Integer percentageComplianceToWorkflow;

    public ClaimAsOnDate() {
    }

    public ClaimAsOnDate(Integer claimNumber, String productName, Date statusDate, Date reportedDate, String shortDescription, String claimsAllocatedTo, Integer percentageComplianceToWorkflow) {
        this.claimNumber = claimNumber;
        this.productName = productName;
        this.statusDate = statusDate;
        this.reportedDate = reportedDate;
        this.shortDescription = shortDescription;
        this.claimsAllocatedTo = claimsAllocatedTo;
        this.percentageComplianceToWorkflow = percentageComplianceToWorkflow;
    }

    public Integer getClaimNumber() {
        return claimNumber;
    }

    public void setClaimNumber(Integer claimNumber) {
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
    
    
}
