/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alpha.data.dao;

import com.alpha.data.controllers.PercentageComplianceUnderwritingJpaController;
import com.alpha.data.controllers.exceptions.NonexistentEntityException;
import com.alpha.data.entities.PercentageComplianceUnderwriting;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Tsotsoh
 */
public class PercentageComplianceDAO {
    
    private final EntityManagerFactory emf;
    private final PercentageComplianceUnderwritingJpaController controller;

    public PercentageComplianceDAO() {
        emf = Persistence.createEntityManagerFactory("AlphaDirectKPIManagementPU");
        controller = new PercentageComplianceUnderwritingJpaController(emf);
    }
    
    public void add(PercentageComplianceUnderwriting pcu){
        controller.create(pcu);
    }
    
    public void edit(PercentageComplianceUnderwriting pcu) throws Exception{
        controller.edit(pcu);
    }
    
    public void delete(int id) throws NonexistentEntityException{
        controller.destroy(id);
    }
    
    public double getAggregatedBetweenRanges(String name, Date startDate, Date endDate){
        return controller.getAggregatedBetweenRanges(name, startDate, endDate);
    }
    
    public List<PercentageComplianceUnderwriting> getPercentageBetweenRange(String name, Date startDate, Date endDate){
        return controller.getPercentageBetweenRange(name, startDate, endDate);
    }
}
