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
public class PercentageCostPerPulaGwpData {

    private Integer id;
    private double expectedPercentage, actualPercentage;
    private Date timestamp;
    private AgentData agent;

    public PercentageCostPerPulaGwpData() {
    }

    public PercentageCostPerPulaGwpData(Integer id, double expectedPercentage, double actualPercentage, Date timestamp, AgentData agent) {
        this.id = id;
        this.expectedPercentage = expectedPercentage;
        this.actualPercentage = actualPercentage;
        this.timestamp = timestamp;
        this.agent = agent;
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

    public AgentData getAgent() {
        return agent;
    }

    public void setAgent(AgentData agent) {
        this.agent = agent;
    }

}
