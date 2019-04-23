/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alpha.data.dao;

import com.alpha.data.controllers.PrecentageComplianceBdJpaController;
import com.alpha.data.controllers.exceptions.NonexistentEntityException;
import com.alpha.data.entities.PrecentageComplianceBd;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Tsotsoh
 */
public class PrecentageComplianceBdDAO {
    
    private final EntityManagerFactory emf;
    private final PrecentageComplianceBdJpaController controller;

    public PrecentageComplianceBdDAO() {
        emf = Persistence.createEntityManagerFactory("AlphaDirectKPIManagementPU");
        controller = new PrecentageComplianceBdJpaController(emf);
    }
    
    public void add(PrecentageComplianceBd object) {
        controller.create(object);
    }

    public void edit(PrecentageComplianceBd object) throws Exception {
        controller.edit(object);
    }

    public void delete(int id) throws NonexistentEntityException {
        controller.destroy(id);
    }
    
    public List<PrecentageComplianceBd> getSingularByTypeBetweenDates(String agent, String type, Date startDate, Date endDate) {
        return controller.getSingularByTypeBetweenDates(agent, type, startDate, endDate);
    }
    
    public double getAggregatedByTypeBetweenDates(String agent, String type, Date startDate, Date endDate){
        return controller.getAggregatedByTypeBetweenDates(agent, type, startDate, endDate);
    }
}
