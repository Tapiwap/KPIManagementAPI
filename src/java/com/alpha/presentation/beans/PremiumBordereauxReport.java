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
public class PremiumBordereauxReport {
    
    private String policyNumber, groupCode;

    public PremiumBordereauxReport() {
    }

    public PremiumBordereauxReport(String policyNumber, String groupCode) {
        this.policyNumber = policyNumber;
        this.groupCode = groupCode;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }
    
    
}
