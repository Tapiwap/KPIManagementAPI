/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alpha.data.dao;

import com.alpha.data.controllers.ComplianceCashOutBdJpaController;
import com.alpha.data.controllers.exceptions.NonexistentEntityException;
import com.alpha.data.entities.ComplianceCashOutBd;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Tsotsoh
 */
public class ComplianceCashOutBdDAO {

    private final EntityManagerFactory emf;
    private final ComplianceCashOutBdJpaController cashOutBd;

    public ComplianceCashOutBdDAO() {
        emf = Persistence.createEntityManagerFactory("AlphaDirectKPIManagementPU");
        cashOutBd = new ComplianceCashOutBdJpaController(emf);
    }

    public void add(ComplianceCashOutBd object) {
        cashOutBd.create(object);
    }

    public void edit(ComplianceCashOutBd object) throws Exception {
        cashOutBd.edit(object);
    }

    public void delete(int id) throws NonexistentEntityException {
        cashOutBd.destroy(id);
    }
    
    public List<ComplianceCashOutBd> getSingularByTypeBetweenDates(String agent, String type, Date startDate, Date endDate) {
        return cashOutBd.getSingularByTypeBetweenDates(agent, type, startDate, endDate);
    }
    
    public double getAggregatedByTypeBetweenDates(String agent, String type, Date startDate, Date endDate){
        return cashOutBd.getAggregatedByTypeBetweenDates(agent, type, startDate, endDate);
    }
}
