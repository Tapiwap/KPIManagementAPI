/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alpha.data.dao;

import com.alpha.data.controllers.PercentageComplianceClaimsJpaController;
import com.alpha.data.controllers.exceptions.NonexistentEntityException;
import com.alpha.data.entities.PercentageComplianceClaims;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Tsotsoh
 */
public class PercentageComplianceClaimsDAO {
    
    private final EntityManagerFactory emf;
    private final PercentageComplianceClaimsJpaController controller;

    public PercentageComplianceClaimsDAO() {
        this.emf = Persistence.createEntityManagerFactory("AlphaDirectKPIManagementPU");
        this.controller = new PercentageComplianceClaimsJpaController(emf);
    }
    
    
    public void add(PercentageComplianceClaims claim){
        controller.create(claim);
    }
    
    public void edit(PercentageComplianceClaims claim) throws Exception{
        controller.edit(claim);
    }
    
    public void delete(int id) throws NonexistentEntityException{
        controller.destroy(id);
    }
    
    public double getAggregatedBetweenRanges(String name, Date startDate, Date endDate){
        return controller.getAggregatedBetweenRanges(name, startDate, endDate);
    }
    
    public List<PercentageComplianceClaims> getPercentageBetweenRange(String name, Date startDate, Date endDate){
        return controller.getPercentageBetweenRange(name, startDate, endDate);
    }
}
