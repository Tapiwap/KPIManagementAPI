/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alpha.data.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Tsotsoh
 */
@Entity
@Table(name = "premium_bordereaux_report")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PremiumBordereauxReport.findAll", query = "SELECT p FROM PremiumBordereauxReport p")
    , @NamedQuery(name = "PremiumBordereauxReport.findByPolicyNumber", query = "SELECT p FROM PremiumBordereauxReport p WHERE p.policyNumber = :policyNumber")
    , @NamedQuery(name = "PremiumBordereauxReport.findByGroupCode", query = "SELECT p FROM PremiumBordereauxReport p WHERE p.groupCode = :groupCode")
    , @NamedQuery(name = "PremiumBordereauxReport.countWithTransactionReportByGroupCode", 
            query = "SELECT p.policyNumber, p.groupCode, t.agencySubAgentName FROM PremiumBordereauxReport p, TransactionReportByProduct t WHERE t.agencySubAgentName = :agentName AND p.groupCode = :groupCode AND p.policyNumber = t.policyNumber")})
public class PremiumBordereauxReport implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "policy_number")
    private String policyNumber;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "group_code")
    private String groupCode;

    public PremiumBordereauxReport() {
    }

    public PremiumBordereauxReport(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    public PremiumBordereauxReport(String policyNumber, String groupCode) {
        this.policyNumber = policyNumber;
        this.groupCode = groupCode;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (policyNumber != null ? policyNumber.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PremiumBordereauxReport)) {
            return false;
        }
        PremiumBordereauxReport other = (PremiumBordereauxReport) object;
        if ((this.policyNumber == null && other.policyNumber != null) || (this.policyNumber != null && !this.policyNumber.equals(other.policyNumber))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.alpha.data.entities.PremiumBordereauxReport[ policyNumber=" + policyNumber + " ]";
    }
    
}
