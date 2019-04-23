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
@Table(name = "transaction_report_by_product")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TransactionReportByProduct.findAll", query = "SELECT t FROM TransactionReportByProduct t")
    , @NamedQuery(name = "TransactionReportByProduct.findByPolicyNumber", query = "SELECT t FROM TransactionReportByProduct t WHERE t.policyNumber = :policyNumber")
    , @NamedQuery(name = "TransactionReportByProduct.findByTransaction", query = "SELECT t FROM TransactionReportByProduct t WHERE t.transaction = :transaction")
    , @NamedQuery(name = "TransactionReportByProduct.findByAgencySubAgentName", query = "SELECT t FROM TransactionReportByProduct t WHERE t.agencySubAgentName = :agencySubAgentName")
    , @NamedQuery(name = "TransactionReportByProduct.countByAgencySubAgentName", 
            query = "SELECT COUNT(t) FROM TransactionReportByProduct t WHERE t.agencySubAgentName = :agencySubAgentName")
    , @NamedQuery(name = "TransactionReportByProduct.getMotorNonMotorRatio", 
            query = "SELECT t.policyNumber, t.transaction, p.groupCode, t.agencySubAgentName FROM TransactionReportByProduct t, PremiumBordereauxReport p WHERE t.policyNumber = p.policyNumber AND t.agencySubAgentName = :agent")})
public class TransactionReportByProduct implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "policy_number")
    private String policyNumber;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "transaction")
    private String transaction;
    @Size(max = 100)
    @Column(name = "agency_sub_agent_name")
    private String agencySubAgentName;

    public TransactionReportByProduct() {
    }

    public TransactionReportByProduct(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    public TransactionReportByProduct(String policyNumber, String transaction) {
        this.policyNumber = policyNumber;
        this.transaction = transaction;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    public String getTransaction() {
        return transaction;
    }

    public void setTransaction(String transaction) {
        this.transaction = transaction;
    }

    public String getAgencySubAgentName() {
        return agencySubAgentName;
    }

    public void setAgencySubAgentName(String agencySubAgentName) {
        this.agencySubAgentName = agencySubAgentName;
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
        if (!(object instanceof TransactionReportByProduct)) {
            return false;
        }
        TransactionReportByProduct other = (TransactionReportByProduct) object;
        if ((this.policyNumber == null && other.policyNumber != null) || (this.policyNumber != null && !this.policyNumber.equals(other.policyNumber))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.alpha.data.entities.TransactionReportByProduct[ policyNumber=" + policyNumber + " ]";
    }
    
}
