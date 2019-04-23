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
@Table(name = "percentage_cost_per_pula_gwp")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PercentageCostPerPulaGwp.findAll", query = "SELECT p FROM PercentageCostPerPulaGwp p")
    , @NamedQuery(name = "PercentageCostPerPulaGwp.findById", query = "SELECT p FROM PercentageCostPerPulaGwp p WHERE p.id = :id")
    , @NamedQuery(name = "PercentageCostPerPulaGwp.findByExpectedPercentage", query = "SELECT p FROM PercentageCostPerPulaGwp p WHERE p.expectedPercentage = :expectedPercentage")
    , @NamedQuery(name = "PercentageCostPerPulaGwp.findByActualPercentage", query = "SELECT p FROM PercentageCostPerPulaGwp p WHERE p.actualPercentage = :actualPercentage")
    , @NamedQuery(name = "PercentageCostPerPulaGwp.findByTimestamp", query = "SELECT p FROM PercentageCostPerPulaGwp p WHERE p.timestamp = :timestamp")
    , @NamedQuery(name = "PercentageCostPerPulaGwp.findByAgent", query = "SELECT p FROM PercentageCostPerPulaGwp p WHERE p.agent = :agent")
    , @NamedQuery(name = "PercentageCostPerPulaGwp.findAggregated", 
            query = "SELECT SUM(p.actualPercentage) FROM PercentageCostPerPulaGwp p WHERE p.agent =:agent AND p.timestamp BETWEEN :startDate AND :endDate")
    , @NamedQuery(name = "PercentageCostPerPulaGwp.findSingularBetweenRange", 
            query="SELECT p FROM PercentageCostPerPulaGwp p WHERE p.agent =:agent AND p.timestamp BETWEEN :startDate AND :endDate")})
public class PercentageCostPerPulaGwp implements Serializable {

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
    @Basic(optional = false)
    @NotNull
    @Column(name = "timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;
    @Size(max = 100)
    @Column(name = "agent")
    private String agent;

    public PercentageCostPerPulaGwp() {
    }

    public PercentageCostPerPulaGwp(Integer id) {
        this.id = id;
    }

    public PercentageCostPerPulaGwp(Integer id, double expectedPercentage, double actualPercentage, Date timestamp) {
        this.id = id;
        this.expectedPercentage = expectedPercentage;
        this.actualPercentage = actualPercentage;
        this.timestamp = timestamp;
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
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PercentageCostPerPulaGwp)) {
            return false;
        }
        PercentageCostPerPulaGwp other = (PercentageCostPerPulaGwp) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.alpha.data.entities.PercentageCostPerPulaGwp[ id=" + id + " ]";
    }
    
}
