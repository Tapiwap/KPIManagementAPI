/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alpha.presentation.beans;

import java.util.Date;

/**
 *
 * @author Tsotsoh
 */
public class DaysToIssueQuotesUnderwritingData {
    
    private Integer daysToIssueQuotesId;
    private int expectedDaysTaken;
    private int actualDaysTaken;
    private Date timestamp;
    private AgentData agent;

    public DaysToIssueQuotesUnderwritingData() {
    }
    
    public DaysToIssueQuotesUnderwritingData(Integer daysToIssueQuotesId, int expectedDaysTaken, int actualDaysTaken, Date timestamp, AgentData agent) {
        this.daysToIssueQuotesId = daysToIssueQuotesId;
        this.expectedDaysTaken = expectedDaysTaken;
        this.actualDaysTaken = actualDaysTaken;
        this.timestamp = timestamp;
        this.agent = agent;
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

    public AgentData getAgent() {
        return agent;
    }

    public void setAgent(AgentData agent) {
        this.agent = agent;
    }    
}
