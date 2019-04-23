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
@Table(name = "renewal_ration_bd")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RenewalRationBd.findAll", query = "SELECT r FROM RenewalRationBd r")
    , @NamedQuery(name = "RenewalRationBd.findById", query = "SELECT r FROM RenewalRationBd r WHERE r.id = :id")
    , @NamedQuery(name = "RenewalRationBd.findByActualRatio", query = "SELECT r FROM RenewalRationBd r WHERE r.actualRatio = :actualRatio")
    , @NamedQuery(name = "RenewalRationBd.findByExpectedRatio", query = "SELECT r FROM RenewalRationBd r WHERE r.expectedRatio = :expectedRatio")
    , @NamedQuery(name = "RenewalRationBd.findByTimestamp", query = "SELECT r FROM RenewalRationBd r WHERE r.timestamp = :timestamp")
    , @NamedQuery(name = "RenewalRationBd.findByAgent", query = "SELECT r FROM RenewalRationBd r WHERE r.agent = :agent")
    , @NamedQuery(name = "RenewalRationBd.findByType", query = "SELECT r FROM RenewalRationBd r WHERE r.type = :type")
    , @NamedQuery(name = "RenewalRationBd.findAggregatedByTypeAndDate",
            query = "SELECT SUM(r.actualRatio) FROM RenewalRationBd r WHERE r.agent = :agent AND r.type = :type AND r.timestamp BETWEEN :startDate AND :endDate")
    , @NamedQuery(name = "RenewalRationBd.findSingularByTypeAndDate",
            query = "SELECT r FROM RenewalRationBd r WHERE r.agent = :agent AND r.type = :type AND r.timestamp BETWEEN :startDate AND :endDate")})
public class RenewalRationBd implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "actual_ratio")
    private double actualRatio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "expected_ratio")
    private double expectedRatio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;
    @Size(max = 100)
    @Column(name = "agent")
    private String agent;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 18)
    @Column(name = "type")
    private String type;

    public RenewalRationBd() {
    }

    public RenewalRationBd(Integer id) {
        this.id = id;
    }

    public RenewalRationBd(Integer id, double actualRatio, double expectedRatio, Date timestamp, String type) {
        this.id = id;
        this.actualRatio = actualRatio;
        this.expectedRatio = expectedRatio;
        this.timestamp = timestamp;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getActualRatio() {
        return actualRatio;
    }

    public void setActualRatio(double actualRatio) {
        this.actualRatio = actualRatio;
    }

    public double getExpectedRatio() {
        return expectedRatio;
    }

    public void setExpectedRatio(double expectedRatio) {
        this.expectedRatio = expectedRatio;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
        if (!(object instanceof RenewalRationBd)) {
            return false;
        }
        RenewalRationBd other = (RenewalRationBd) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.alpha.data.entities.RenewalRationBd[ id=" + id + " ]";
    }
    
}
