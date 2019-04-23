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
public class PercentageClosedPolicyUnderwittingData {

    private Integer percentageClosedPolicyId;
    private double actualPercentageClosedPolicy;
    private double expectedPercentageClosedPolicy;
    private Date timestamp;
    private AgentData agent;

    public PercentageClosedPolicyUnderwittingData() {
    }

    public PercentageClosedPolicyUnderwittingData(Integer percentageClosedPolicyId, double actualPercentageClosedPolicy, double expectedPercentageClosedPolicy, Date timestamp, AgentData agentId) {
        this.percentageClosedPolicyId = percentageClosedPolicyId;
        this.actualPercentageClosedPolicy = actualPercentageClosedPolicy;
        this.expectedPercentageClosedPolicy = expectedPercentageClosedPolicy;
        this.timestamp = timestamp;
        this.agent = agentId;
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

    public AgentData getAgent() {
        return agent;
    }

    public void setAgent(AgentData agent) {
        this.agent = agent;
    }
}
