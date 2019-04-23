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
@Table(name = "percentage_compliance_underwriting")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PercentageComplianceUnderwriting.findAll", query = "SELECT p FROM PercentageComplianceUnderwriting p")
    , @NamedQuery(name = "PercentageComplianceUnderwriting.findByPercentageComplianceId", query = "SELECT p FROM PercentageComplianceUnderwriting p WHERE p.percentageComplianceId = :percentageComplianceId")
    , @NamedQuery(name = "PercentageComplianceUnderwriting.findByExpectedPercentage", query = "SELECT p FROM PercentageComplianceUnderwriting p WHERE p.expectedPercentage = :expectedPercentage")
    , @NamedQuery(name = "PercentageComplianceUnderwriting.findByActualPercentage", query = "SELECT p FROM PercentageComplianceUnderwriting p WHERE p.actualPercentage = :actualPercentage")
    , @NamedQuery(name = "PercentageComplianceUnderwriting.findByTimestamp", query = "SELECT p FROM PercentageComplianceUnderwriting p WHERE p.timestamp = :timestamp")
    , @NamedQuery(name = "PercentageComplianceUnderwriting.findByAgent", query = "SELECT p FROM PercentageComplianceUnderwriting p WHERE p.agent = :agent")
    , @NamedQuery(name = "PercentageComplianceUnderwriting.aggregate", 
            query = "SELECT AVG(p.actualPercentage) FROM PercentageComplianceUnderwriting p WHERE p.agent = :agent AND p.timestamp BETWEEN :startDate AND :endDate")
    , @NamedQuery(name = "PercentageComplianceUnderwriting.singular", 
            query = "SELECT p FROM PercentageComplianceUnderwriting p WHERE p.agent = :agent AND p.timestamp BETWEEN :startDate AND :endDate")})
public class PercentageComplianceUnderwriting implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "percentage_compliance_id")
    private Integer percentageComplianceId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "expected_percentage")
    private double expectedPercentage;
    @Basic(optional = false)
    @NotNull
    @Column(name = "actual_percentage")
    private double actualPercentage;
    @Basic(optional = false)
    @NotNull
    @Column(name = "timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;
    @Size(max = 100)
    @Column(name = "agent")
    private String agent;

    public PercentageComplianceUnderwriting() {
    }

    public PercentageComplianceUnderwriting(Integer percentageComplianceId) {
        this.percentageComplianceId = percentageComplianceId;
    }

    public PercentageComplianceUnderwriting(Integer percentageComplianceId, double expectedPercentage, double actualPercentage, Date timestamp) {
        this.percentageComplianceId = percentageComplianceId;
        this.expectedPercentage = expectedPercentage;
        this.actualPercentage = actualPercentage;
        this.timestamp = timestamp;
    }

    public Integer getPercentageComplianceId() {
        return percentageComplianceId;
    }

    public void setPercentageComplianceId(Integer percentageComplianceId) {
        this.percentageComplianceId = percentageComplianceId;
    }

    public double getExpectedPercentage() {
        return expectedPercentage;
    }

    public void setExpectedPercentage(double expectedPercentage) {
        this.expectedPercentage = expectedPercentage;
    }

    public double getActualPercentage() {
        return actualPercentage;
    }

    public void setActualPercentage(double actualPercentage) {
        this.actualPercentage = actualPercentage;
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
        hash += (percentageComplianceId != null ? percentageComplianceId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PercentageComplianceUnderwriting)) {
            return false;
        }
        PercentageComplianceUnderwriting other = (PercentageComplianceUnderwriting) object;
        if ((this.percentageComplianceId == null && other.percentageComplianceId != null) || (this.percentageComplianceId != null && !this.percentageComplianceId.equals(other.percentageComplianceId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.alpha.data.entities.PercentageComplianceUnderwriting[ percentageComplianceId=" + percentageComplianceId + " ]";
    }
    
}
