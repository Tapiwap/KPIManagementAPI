/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alpha.data.dao;

import com.alpha.data.controllers.PercentageClosedPolicyUnderwittingJpaController;
import com.alpha.data.entities.PercentageClosedPolicyUnderwitting;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Tsotsoh
 */
public class PercentageClosedPolicyDAO {
    
    private final EntityManagerFactory emf;
    private final PercentageClosedPolicyUnderwittingJpaController controller;

    public PercentageClosedPolicyDAO() {
        emf = Persistence.createEntityManagerFactory("AlphaDirectKPIManagementPU");
        controller = new PercentageClosedPolicyUnderwittingJpaController(emf);
    }
    
    public void add(PercentageClosedPolicyUnderwitting pcpu){
        controller.create(pcpu);
    }
    
    public void edit(PercentageClosedPolicyUnderwitting pcpu) throws Exception{
        controller.edit(pcpu);
    }
    
    public void delete(int pcpu) throws Exception{
        controller.destroy(pcpu);
    }
    
    public double getAggregateBetweenDates(String agent, Date startDate, Date endDate){
        return controller.getAggregateBetweenDates(agent, startDate, endDate);
    }
    
    public List<PercentageClosedPolicyUnderwitting> getSingularPercentageClosedPolicyBetweenDates(String agent, Date startDate, Date endDate){
        return controller.getSingularPercentageClosedPolicyBetweenDates(agent, startDate, endDate);
    }
    
//    public static void main(String[] args) {
//        //new Date("1/5/2015  4:02:46 AM")
//        double result = new PercentageClosedPolicyDAO().getAggregateBetweenDates(new Date("07/21/2018 "), new Date("07/22/2018 22:15:59"));
//        System.out.println("The result is " + result);
//    }
}
