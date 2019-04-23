/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alpha.data.dao;

import com.alpha.business.migrators.AssessmentCostPerClaimMigrator;
import com.alpha.data.controllers.AssessmentCostPerClaimJpaController;
import com.alpha.data.controllers.exceptions.NonexistentEntityException;
import com.alpha.data.entities.AssessmentCostPerClaim;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tsotsoh
 */
public class AssessmentCostPerClaimDAO {

    private final EntityManagerFactory emf;
    private final AssessmentCostPerClaimJpaController controller;
    private final AssessmentCostPerClaimMigrator migrator;

    public AssessmentCostPerClaimDAO() {
        emf = Persistence.createEntityManagerFactory("AlphaDirectKPIManagementPU");
        controller = new AssessmentCostPerClaimJpaController(emf);
        migrator = new AssessmentCostPerClaimMigrator();
    }

    public void add(AssessmentCostPerClaim object) {
        controller.create(object);
    }

    public void batchAdd(List<AssessmentCostPerClaim> objects) throws Exception {
        controller.batchCreate(objects);
    }

    public void edit(AssessmentCostPerClaim object) throws Exception {
        controller.edit(object);
    }

    public void delete(int id) throws NonexistentEntityException {
        controller.destroy(id);
    }

    public int getCountBetweenDates(Date startDate, Date endDate) {
        return controller.getCountBetweenDates(startDate, endDate);
    }

    public List<AssessmentCostPerClaim> getBetweenDates(Date startDate, Date endDate) {
        return controller.getBetweenDates(startDate, endDate);
    }

//    public static void main(String[] args) {
//        try {
//            AssessmentCostPerClaimDAO dao = new AssessmentCostPerClaimDAO();
//            int count = dao.getCountBetweenDates(new Date("8/21/2017"), new Date("10/17/2017"));
//            System.out.println("Count: " + count);
//            List<AssessmentCostPerClaim> list = dao.getBetweenDates(new Date("8/21/2017"), new Date("10/17/2017"));
//            list.stream().forEach(claim -> System.out.println(claim.getTransSubType()));
////            List<AssessmentCostPerClaim> list = dao.migrator.getDataFromDumb();
////            dao.batchAdd(list);
//        } catch (Exception ex) {
//            Logger.getLogger(TransactionReportDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
}
