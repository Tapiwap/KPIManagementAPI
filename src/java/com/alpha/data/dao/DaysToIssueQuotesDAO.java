/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alpha.data.dao;

import com.alpha.data.controllers.DaysToIssueQuotesUnderwritingJpaController;
import com.alpha.data.controllers.exceptions.NonexistentEntityException;
import com.alpha.data.entities.DaysToIssueQuotesUnderwriting;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Tsotsoh
 */
public class DaysToIssueQuotesDAO {
    
    
    private final EntityManagerFactory emf;
    private final DaysToIssueQuotesUnderwritingJpaController controller;

    public DaysToIssueQuotesDAO() {
        emf = Persistence.createEntityManagerFactory("AlphaDirectKPIManagementPU");
        controller = new DaysToIssueQuotesUnderwritingJpaController(emf);
    }
    
    public void add(DaysToIssueQuotesUnderwriting object){
        controller.create(object);
    }
    
    public void edit(DaysToIssueQuotesUnderwriting object) throws Exception{
        controller.edit(object);
    }
    
    public void delete(int id) throws NonexistentEntityException{
        controller.destroy(id);
    }
    
    public List<Integer> getDaysToIssueQuotesAggregate(String agent, Date startDate, Date endDate){
        return controller.getDaysToIssueQuotesAggregate(agent, startDate, endDate);
    }
    
    public List<DaysToIssueQuotesUnderwriting> getAvgDaysToIssueQuoteSingular(String agent, Date startDate, Date endDate){
        return controller.getAvgDaysToIssueQuoteSingular(agent, startDate, endDate);
    }
}
