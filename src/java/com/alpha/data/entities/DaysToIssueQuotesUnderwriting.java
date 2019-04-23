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
@Table(name = "days_to_issue_quotes_underwriting")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DaysToIssueQuotesUnderwriting.findAll", query = "SELECT d FROM DaysToIssueQuotesUnderwriting d")
    , @NamedQuery(name = "DaysToIssueQuotesUnderwriting.findByDaysToIssueQuotesId", query = "SELECT d FROM DaysToIssueQuotesUnderwriting d WHERE d.daysToIssueQuotesId = :daysToIssueQuotesId")
    , @NamedQuery(name = "DaysToIssueQuotesUnderwriting.findByExpectedDaysTaken", query = "SELECT d FROM DaysToIssueQuotesUnderwriting d WHERE d.expectedDaysTaken = :expectedDaysTaken")
    , @NamedQuery(name = "DaysToIssueQuotesUnderwriting.findByActualDaysTaken", query = "SELECT d FROM DaysToIssueQuotesUnderwriting d WHERE d.actualDaysTaken = :actualDaysTaken")
    , @NamedQuery(name = "DaysToIssueQuotesUnderwriting.findByTimestamp", query = "SELECT d FROM DaysToIssueQuotesUnderwriting d WHERE d.timestamp = :timestamp")
    , @NamedQuery(name = "DaysToIssueQuotesUnderwriting.findByAgent", query = "SELECT d FROM DaysToIssueQuotesUnderwriting d WHERE d.agent = :agent")
    , @NamedQuery(name = "DaysToIssueQuotesUnderwriting.avgDaysToIssueQuoteAggregate", 
            query = "SELECT d.actualDaysTaken FROM DaysToIssueQuotesUnderwriting d WHERE d.agent =:agent AND d.timestamp BETWEEN :startDate AND :endDate")
    , @NamedQuery(name = "DaysToIssueQuotesUnderwriting.avgDaysToIssueQuoteSingular", 
            query = "SELECT d FROM DaysToIssueQuotesUnderwriting d WHERE d.agent =:agent AND d.timestamp BETWEEN :startDate AND :endDate")})
public class DaysToIssueQuotesUnderwriting implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "days_to_issue_quotes_id")
    private Integer daysToIssueQuotesId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "expected_days_taken")
    private int expectedDaysTaken;
    @Basic(optional = false)
    @NotNull
    @Column(name = "actual_days_taken")
    private int actualDaysTaken;
    @Basic(optional = false)
    @NotNull
    @Column(name = "timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;
    @Size(max = 100)
    @Column(name = "agent")
    private String agent;

    public DaysToIssueQuotesUnderwriting() {
    }

    public DaysToIssueQuotesUnderwriting(Integer daysToIssueQuotesId) {
        this.daysToIssueQuotesId = daysToIssueQuotesId;
    }

    public DaysToIssueQuotesUnderwriting(Integer daysToIssueQuotesId, int expectedDaysTaken, int actualDaysTaken, Date timestamp) {
        this.daysToIssueQuotesId = daysToIssueQuotesId;
        this.expectedDaysTaken = expectedDaysTaken;
        this.actualDaysTaken = actualDaysTaken;
        this.timestamp = timestamp;
    }

    public Integer getDaysToIssueQuotesId() {
        return daysToIssueQuotesId;
    }

    public void setDaysToIssueQuotesId(Integer daysToIssueQuotesId) {
        this.daysToIssueQuotesId = daysToIssueQuotesId;
    }

    public int getExpectedDaysTaken() {
        return expectedDaysTaken;
    }

    public void setExpectedDaysTaken(int expectedDaysTaken) {
        this.expectedDaysTaken = expectedDaysTaken;
    }

    public int getActualDaysTaken() {
        return actualDaysTaken;
    }

    public void setActualDaysTaken(int actualDaysTaken) {
        this.actualDaysTaken = actualDaysTaken;
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
        hash += (daysToIssueQuotesId != null ? daysToIssueQuotesId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DaysToIssueQuotesUnderwriting)) {
            return false;
        }
        DaysToIssueQuotesUnderwriting other = (DaysToIssueQuotesUnderwriting) object;
        if ((this.daysToIssueQuotesId == null && other.daysToIssueQuotesId != null) || (this.daysToIssueQuotesId != null && !this.daysToIssueQuotesId.equals(other.daysToIssueQuotesId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.alpha.data.entities.DaysToIssueQuotesUnderwriting[ daysToIssueQuotesId=" + daysToIssueQuotesId + " ]";
    }
    
}
