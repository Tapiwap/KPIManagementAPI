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
@Table(name = "motor_non_motor_ratio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MotorNonMotorRatio.findAll", query = "SELECT m FROM MotorNonMotorRatio m")
    , @NamedQuery(name = "MotorNonMotorRatio.findByPolicyNumber", query = "SELECT m FROM MotorNonMotorRatio m WHERE m.policyNumber = :policyNumber")
    , @NamedQuery(name = "MotorNonMotorRatio.findByGroupCode", query = "SELECT m FROM MotorNonMotorRatio m WHERE m.groupCode = :groupCode")
    , @NamedQuery(name = "MotorNonMotorRatio.findByAgencySubAgentName", query = "SELECT m FROM MotorNonMotorRatio m WHERE m.agencySubAgentName = :agencySubAgentName")})
public class MotorNonMotorRatio implements Serializable {

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
    @Column(name = "group_code")
    private String groupCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "agency_sub_agent_name")
    private String agencySubAgentName;

    public MotorNonMotorRatio() {
    }

    public MotorNonMotorRatio(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    public MotorNonMotorRatio(String policyNumber, String groupCode, String agencySubAgentName) {
        this.policyNumber = policyNumber;
        this.groupCode = groupCode;
        this.agencySubAgentName = agencySubAgentName;
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
        if (!(object instanceof MotorNonMotorRatio)) {
            return false;
        }
        MotorNonMotorRatio other = (MotorNonMotorRatio) object;
        if ((this.policyNumber == null && other.policyNumber != null) || (this.policyNumber != null && !this.policyNumber.equals(other.policyNumber))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.alpha.data.entities.MotorNonMotorRatio[ policyNumber=" + policyNumber + " ]";
    }
    
}
