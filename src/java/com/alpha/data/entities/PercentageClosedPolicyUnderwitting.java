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
@Table(name = "percentage_closed_policy_underwitting")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PercentageClosedPolicyUnderwitting.findAll", query = "SELECT p FROM PercentageClosedPolicyUnderwitting p")
    , @NamedQuery(name = "PercentageClosedPolicyUnderwitting.findByPercentageClosedPolicyId", query = "SELECT p FROM PercentageClosedPolicyUnderwitting p WHERE p.percentageClosedPolicyId = :percentageClosedPolicyId")
    , @NamedQuery(name = "PercentageClosedPolicyUnderwitting.findByActualPercentageClosedPolicy", query = "SELECT p FROM PercentageClosedPolicyUnderwitting p WHERE p.actualPercentageClosedPolicy = :actualPercentageClosedPolicy")
    , @NamedQuery(name = "PercentageClosedPolicyUnderwitting.findByExpectedPercentageClosedPolicy", query = "SELECT p FROM PercentageClosedPolicyUnderwitting p WHERE p.expectedPercentageClosedPolicy = :expectedPercentageClosedPolicy")
    , @NamedQuery(name = "PercentageClosedPolicyUnderwitting.findByTimestamp", query = "SELECT p FROM PercentageClosedPolicyUnderwitting p WHERE p.timestamp = :timestamp")
    , @NamedQuery(name = "PercentageClosedPolicyUnderwitting.findByAgent", query = "SELECT p FROM PercentageClosedPolicyUnderwitting p WHERE p.agent = :agent")
    , @NamedQuery(name = "PercentageClosedPolicyUnderwitting.AggregateBetweenDates", 
            query = "SELECT SUM(p.actualPercentageClosedPolicy) FROM PercentageClosedPolicyUnderwitting p WHERE p.agent = :agent AND p.timestamp BETWEEN :startDate AND :endDate")
    , @NamedQuery(name = "PercentageClosedPolicyUnderwitting.SingularPercentageClosedPolicyBetweenDates", 
            query = "SELECT p FROM PercentageClosedPolicyUnderwitting p WHERE p.agent = :agent AND p.timestamp BETWEEN :startDate AND :endDate")})
public class PercentageClosedPolicyUnderwitting implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "percentage_closed_policy_id")
    private Integer percentageClosedPolicyId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "actual_percentage_closed_policy")
    private double actualPercentageClosedPolicy;
    @Basic(optional = false)
    @NotNull
    @Column(name = "expected_percentage_closed_policy")
    private double expectedPercentageClosedPolicy;
    @Basic(optional = false)
    @NotNull
    @Column(name = "timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;
    @Size(max = 100)
    @Column(name = "agent")
    private String agent;

    public PercentageClosedPolicyUnderwitting() {
    }

    public PercentageClosedPolicyUnderwitting(Integer percentageClosedPolicyId) {
        this.percentageClosedPolicyId = percentageClosedPolicyId;
    }

    public PercentageClosedPolicyUnderwitting(Integer percentageClosedPolicyId, double actualPercentageClosedPolicy, double expectedPercentageClosedPolicy, Date timestamp) {
        this.percentageClosedPolicyId = percentageClosedPolicyId;
        this.actualPercentageClosedPolicy = actualPercentageClosedPolicy;
        this.expectedPercentageClosedPolicy = expectedPercentageClosedPolicy;
        this.timestamp = timestamp;
    }

    public Integer getPercentageClosedPolicyId() {
        return percentageClosedPolicyId;
    }

    public void setPercentageClosedPolicyId(Integer percentageClosedPolicyId) {
        this.percentageClosedPolicyId = percentageClosedPolicyId;
    }

    public double getActualPercentageClosedPolicy() {
        return actualPercentageClosedPolicy;
    }

    public void setActualPercentageClosedPolicy(double actualPercentageClosedPolicy) {
        this.actualPercentageClosedPolicy = actualPercentageClosedPolicy;
    }

    public double getExpectedPercentageClosedPolicy() {
        return expectedPercentageClosedPolicy;
    }

    public void setExpectedPercentageClosedPolicy(double expectedPercentageClosedPolicy) {
        this.expectedPercentageClosedPolicy = expectedPercentageClosedPolicy;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (percentageClosedPolicyId != null ? percentageClosedPolicyId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PercentageClosedPolicyUnderwitting)) {
            return false;
        }
        PercentageClosedPolicyUnderwitting other = (PercentageClosedPolicyUnderwitting) object;
        if ((this.percentageClosedPolicyId == null && other.percentageClosedPolicyId != null) || (this.percentageClosedPolicyId != null && !this.percentageClosedPolicyId.equals(other.percentageClosedPolicyId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.alpha.data.entities.PercentageClosedPolicyUnderwitting[ percentageClosedPolicyId=" + percentageClosedPolicyId + " ]";
    }
    
}
