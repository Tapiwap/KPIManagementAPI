/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alpha.presentation.beans;

/**
 *
 * @author Tsotsoh
 */
public class TransationReportTemp {
    
    private String policyNumber, agencySubAgentName;

    public TransationReportTemp() {
    }

    public TransationReportTemp(String policyumber, String agencySubAgentName) {
        this.policyNumber = policyumber;
        this.agencySubAgentName = agencySubAgentName;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    public String getAgencySubAgentName() {
        return agencySubAgentName;
    }

    public void setAgencySubAgentName(String agencySubAgentName) {
        this.agencySubAgentName = agencySubAgentName;
    }
    
    
}
