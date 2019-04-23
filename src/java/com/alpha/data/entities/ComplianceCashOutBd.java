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
@Table(name = "compliance_cash_out_bd")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ComplianceCashOutBd.findAll", query = "SELECT c FROM ComplianceCashOutBd c")
    , @NamedQuery(name = "ComplianceCashOutBd.findById", query = "SELECT c FROM ComplianceCashOutBd c WHERE c.id = :id")
    , @NamedQuery(name = "ComplianceCashOutBd.findByActualComliance", query = "SELECT c FROM ComplianceCashOutBd c WHERE c.actualComliance = :actualComliance")
    , @NamedQuery(name = "ComplianceCashOutBd.findByExpectedCompliance", query = "SELECT c FROM ComplianceCashOutBd c WHERE c.expectedCompliance = :expectedCompliance")
    , @NamedQuery(name = "ComplianceCashOutBd.findByTimestamp", query = "SELECT c FROM ComplianceCashOutBd c WHERE c.timestamp = :timestamp")
    , @NamedQuery(name = "ComplianceCashOutBd.findByAgent", query = "SELECT c FROM ComplianceCashOutBd c WHERE c.agent = :agent")
    , @NamedQuery(name = "ComplianceCashOutBd.findByType", query = "SELECT c FROM ComplianceCashOutBd c WHERE c.type = :type")
    , @NamedQuery(name = "ComplianceCashOutBd.findSingularByTypeAndDateRange", 
            query = "SELECT c FROM ComplianceCashOutBd c WHERE c.agent = :agent AND c.type = :type AND c.timestamp BETWEEN :startDate AND :endDate")
    , @NamedQuery(name = "ComplianceCashOutBd.findAggreatedByTypeAndDateRange", 
            query = "SELECT SUM(c.actualComliance) FROM ComplianceCashOutBd c WHERE c.agent = :agent AND c.type = :type AND c.timestamp BETWEEN :startDate AND :endDate")})
public class ComplianceCashOutBd implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "actual_comliance")
    private double actualComliance;
    @Basic(optional = false)
    @NotNull
    @Column(name = "expected_compliance")
    private double expectedCompliance;
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

    public ComplianceCashOutBd() {
    }

    public ComplianceCashOutBd(Integer id) {
        this.id = id;
    }

    public ComplianceCashOutBd(Integer id, double actualComliance, double expectedCompliance, Date timestamp, String type) {
        this.id = id;
        this.actualComliance = actualComliance;
        this.expectedCompliance = expectedCompliance;
        this.timestamp = timestamp;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getActualComliance() {
        return actualComliance;
    }

    public void setActualComliance(double actualComliance) {
        this.actualComliance = actualComliance;
    }

    public double getExpectedCompliance() {
        return expectedCompliance;
    }

    public void setExpectedCompliance(double expectedCompliance) {
        this.expectedCompliance = expectedCompliance;
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
        if (!(object instanceof ComplianceCashOutBd)) {
            return false;
        }
        ComplianceCashOutBd other = (ComplianceCashOutBd) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.alpha.data.entities.ComplianceCashOutBd[ id=" + id + " ]";
    }
    
}
