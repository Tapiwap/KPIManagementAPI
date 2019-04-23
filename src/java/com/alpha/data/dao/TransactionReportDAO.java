/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alpha.data.dao;

import com.alpha.business.migrators.TransactionReportMigrator;
import com.alpha.data.controllers.TransactionReportByProductJpaController;
import com.alpha.data.entities.TransactionReportByProduct;
import java.util.ArrayList;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tsotsoh
 */
public class TransactionReportDAO {

    private final EntityManagerFactory emf;
    private final TransactionReportByProductJpaController controller;
    private final TransactionReportMigrator migrator;
    private static int count;

    public TransactionReportDAO() {
        emf = Persistence.createEntityManagerFactory("AlphaDirectKPIManagementPU");
        controller = new TransactionReportByProductJpaController(emf);
        migrator = new TransactionReportMigrator();
    }

    public void add(TransactionReportByProduct object) throws Exception {
        controller.create(object);
    }

    public void batchAdd(List<TransactionReportByProduct> objects) throws Exception {
        controller.batchCreate(objects);
    }
    
    public List<TransactionReportByProduct> getBySubAgentName(String name) {
        return controller.getBySubAgentName(name);
    }
    
    public int getCountBySubAgentName(String name) {
        return controller.getCountBySubAgentName(name);
    }
    
    public List<Object[]> getBySubAgentNameForMotorNonMotorRatio(String name){
        return controller.getBySubAgentNameForMotorNonMotorRatio(name);
    }
    

//    public static void main(String[] args) {
//        try {
//            List<Object[]> list = new TransactionReportDAO().getBySubAgentNameForMotorNonMotorRatio("Arun P. Iyer");
//            double motorCount = list.parallelStream().filter(obj -> obj[2].toString().equalsIgnoreCase("MOTOR_COM")).count();
//            double nonMotorCount = list.parallelStream().filter(obj -> !obj[2].toString().equalsIgnoreCase("MOTOR_COM")).count();
//            System.out.println("MotorCount : " + motorCount + " NonMotorCount: " + nonMotorCount);
//            int count = list.size();
//            System.out.println("Size is : " + count);
////            TransactionReportDAO dao = new TransactionReportDAO();
////            //List<TransactionReportByProduct> list = dao.migrator.getDataFromDumb();
////            //dao.batchAdd(list);
////            List<TransactionReportByProduct> list = dao.getBySubAgentName("Yvone V. Chali");
////            list.stream().forEach(policy -> {
////                if (policy.getTransaction().equalsIgnoreCase("NEWBUSINESS")){
////                    count++;
////                }
////            });
////            Double percentage = ((double)count / (double)list.size()) * 100;
////            System.out.println("Percentage Policies Closed is: " + percentage + "%");
//        } catch (Exception ex) {
//            Logger.getLogger(TransactionReportDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
}
