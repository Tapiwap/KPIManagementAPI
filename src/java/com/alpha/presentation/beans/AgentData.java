/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alpha.presentation.beans;

import java.util.List;

/**
 *
 * @author Tsotsoh
 */
public class AgentData {

    private Integer id;
    private String name;
    private List<DaysToIssueQuotesUnderwritingData> daysToIssueQuotesUnderwritingList;
    private List<PercentageCostPerPulaGwpData> percentageCostPerPulaGwpList;
    private List<PercentageComplianceUnderwritingData> percentageComplianceUnderwritingList;
    private List<PercentageClosedPolicyUnderwittingData> percentageClosedPolicyUnderwittingList;

    public AgentData() {
    }

    public AgentData(Integer id, String name, List<DaysToIssueQuotesUnderwritingData> daysToIssueQuotesUnderwritingList, List<PercentageCostPerPulaGwpData> percentageCostPerPulaGwpList, List<PercentageComplianceUnderwritingData> percentageComplianceUnderwritingList, List<PercentageClosedPolicyUnderwittingData> percentageClosedPolicyUnderwittingList) {
        this.id = id;
        this.name = name;
        this.daysToIssueQuotesUnderwritingList = daysToIssueQuotesUnderwritingList;
        this.percentageCostPerPulaGwpList = percentageCostPerPulaGwpList;
        this.percentageComplianceUnderwritingList = percentageComplianceUnderwritingList;
        this.percentageClosedPolicyUnderwittingList = percentageClosedPolicyUnderwittingList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DaysToIssueQuotesUnderwritingData> getDaysToIssueQuotesUnderwritingList() {
        return daysToIssueQuotesUnderwritingList;
    }

    public void setDaysToIssueQuotesUnderwritingList(List<DaysToIssueQuotesUnderwritingData> daysToIssueQuotesUnderwritingList) {
        this.daysToIssueQuotesUnderwritingList = daysToIssueQuotesUnderwritingList;
    }

    public List<PercentageCostPerPulaGwpData> getPercentageCostPerPulaGwpList() {
        return percentageCostPerPulaGwpList;
    }

    public void setPercentageCostPerPulaGwpList(List<PercentageCostPerPulaGwpData> percentageCostPerPulaGwpList) {
        this.percentageCostPerPulaGwpList = percentageCostPerPulaGwpList;
    }

    public List<PercentageComplianceUnderwritingData> getPercentageComplianceUnderwritingList() {
        return percentageComplianceUnderwritingList;
    }

    public void setPercentageComplianceUnderwritingList(List<PercentageComplianceUnderwritingData> percentageComplianceUnderwritingList) {
        this.percentageComplianceUnderwritingList = percentageComplianceUnderwritingList;
    }

    public List<PercentageClosedPolicyUnderwittingData> getPercentageClosedPolicyUnderwittingList() {
        return percentageClosedPolicyUnderwittingList;
    }

    public void setPercentageClosedPolicyUnderwittingList(List<PercentageClosedPolicyUnderwittingData> percentageClosedPolicyUnderwittingList) {
        this.percentageClosedPolicyUnderwittingList = percentageClosedPolicyUnderwittingList;
    }

}
