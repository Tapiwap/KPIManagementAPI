/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alpha.data.dao;

import com.alpha.data.controllers.PercentageCostPerPulaGwpJpaController;
import com.alpha.data.controllers.exceptions.NonexistentEntityException;
import com.alpha.data.entities.PercentageCostPerPulaGwp;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Tsotsoh
 */
public class PercentageCostPerPulaGwpDAO {
    
    private final EntityManagerFactory emf;
    private final PercentageCostPerPulaGwpJpaController controller;

    public PercentageCostPerPulaGwpDAO() {
        emf = Persistence.createEntityManagerFactory("AlphaDirectKPIManagementPU");
        controller = new PercentageCostPerPulaGwpJpaController(emf);
    }
    
    public void add(PercentageCostPerPulaGwp pcpp){
        controller.create(pcpp);
    }
    
    public void edit(PercentageCostPerPulaGwp pcpp) throws Exception{
        controller.edit(pcpp);
    }
    
    public void delete(int id) throws NonexistentEntityException{
        controller.destroy(id);
    }
    
    public double getAggregatedBetweenRanges(String name, Date startDate, Date endDate){
        return controller.getAggregatedBetweenRanges(name, startDate, endDate);
    }
    
    public List<PercentageCostPerPulaGwp> getPercentageBetweenRange(String name, Date startDate, Date endDate){
        return controller.getPercentageBetweenRange(name, startDate, endDate);
    }
}
