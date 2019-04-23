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
@Table(name = "percentage_compliance_claims")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PercentageComplianceClaims.findAll", query = "SELECT p FROM PercentageComplianceClaims p")
    , @NamedQuery(name = "PercentageComplianceClaims.findById", query = "SELECT p FROM PercentageComplianceClaims p WHERE p.id = :id")
    , @NamedQuery(name = "PercentageComplianceClaims.findByExpectedPercentage", query = "SELECT p FROM PercentageComplianceClaims p WHERE p.expectedPercentage = :expectedPercentage")
    , @NamedQuery(name = "PercentageComplianceClaims.findByActualPercentage", query = "SELECT p FROM PercentageComplianceClaims p WHERE p.actualPercentage = :actualPercentage")
    , @NamedQuery(name = "PercentageComplianceClaims.findByAgent", query = "SELECT p FROM PercentageComplianceClaims p WHERE p.agent = :agent")
    , @NamedQuery(name = "PercentageComplianceClaims.aggregate", 
            query = "SELECT sum(p.actualPercentage) FROM PercentageComplianceClaims p WHERE p.agent = :agent AND p.timestamp BETWEEN :startDate AND :endDate")
    , @NamedQuery(name = "PercentageComplianceClaims.singular", 
            query = "SELECT p FROM PercentageComplianceClaims p WHERE p.agent = :agent AND p.timestamp BETWEEN :startDate AND :endDate")})
public class PercentageComplianceClaims implements Serializable {


    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "expected_percentage")
    private double expectedPercentage;
    @Basic(optional = false)
    @NotNull
    @Column(name = "actual_percentage")
    private double actualPercentage;
    @Size(max = 100)
    @Column(name = "agent")
    private String agent;
    @Basic(optional = false)
    @NotNull
    @Column(name = "timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;

    public PercentageComplianceClaims() {
    }

    public PercentageComplianceClaims(Integer id) {
        this.id = id;
    }

    public PercentageComplianceClaims(Integer id, double expectedPercentage, double actualPercentage) {
        this.id = id;
        this.expectedPercentage = expectedPercentage;
        this.actualPercentage = actualPercentage;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
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
        if (!(object instanceof PercentageComplianceClaims)) {
            return false;
        }
        PercentageComplianceClaims other = (PercentageComplianceClaims) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.alpha.data.entities.PercentageComplianceClaims[ id=" + id + " ]";
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
    
}
