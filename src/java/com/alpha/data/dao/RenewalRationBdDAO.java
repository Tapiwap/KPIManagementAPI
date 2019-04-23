/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alpha.data.dao;

import com.alpha.data.controllers.RenewalRationBdJpaController;
import com.alpha.data.controllers.exceptions.NonexistentEntityException;
import com.alpha.data.entities.RenewalRationBd;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Tsotsoh
 */
public class RenewalRationBdDAO {
    
    private final EntityManagerFactory emf;
    private final RenewalRationBdJpaController controller;

    public RenewalRationBdDAO() {
        emf = Persistence.createEntityManagerFactory("AlphaDirectKPIManagementPU");
        controller = new RenewalRationBdJpaController(emf);
    }
    
    public void add(RenewalRationBd object) {
        controller.create(object);
    }

    public void edit(RenewalRationBd object) throws Exception {
        controller.edit(object);
    }

    public void delete(int id) throws NonexistentEntityException {
        controller.destroy(id);
    }
    
    public List<RenewalRationBd> getSingularByTypeBetweenDates(String agent, String type, Date startDate, Date endDate) {
        return controller.getSingularByTypeBetweenDates(agent, type, startDate, endDate);
    }
    
    public double getAggregatedByTypeBetweenDates(String agent, String type, Date startDate, Date endDate){
        return controller.getAggregatedByTypeBetweenDates(agent, type, startDate, endDate);
    }
}
