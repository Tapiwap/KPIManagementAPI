/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alpha.data.dao;

import com.alpha.business.migrators.MotorNonMotorMigrator;
import com.alpha.data.controllers.PremiumBordereauxReportJpaController;
import com.alpha.data.controllers.exceptions.NonexistentEntityException;
import com.alpha.data.entities.MotorNonMotorRatio;
import com.alpha.data.entities.PremiumBordereauxReport;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Tsotsoh
 */
public class PremiumBordereauxReportDAO {

    private final EntityManagerFactory emf;
    private final PremiumBordereauxReportJpaController controller;
    private final MotorNonMotorMigrator migrator;

    public PremiumBordereauxReportDAO() {
        emf = Persistence.createEntityManagerFactory("AlphaDirectKPIManagementPU");
        controller = new PremiumBordereauxReportJpaController(emf);
        migrator = new MotorNonMotorMigrator();
    }

    public void add(PremiumBordereauxReport object) throws Exception {
        controller.create(object);
    }

    public void batchAdd(List<PremiumBordereauxReport> list) throws Exception {
        controller.batchCreate(list);
    }

    public void edit(PremiumBordereauxReport object) throws Exception {
        controller.edit(object);
    }

    public void delete(String id) throws NonexistentEntityException {
        controller.destroy(id);
    }

    public int getCountOfMotorNonMotorBasedOnGroupCode(String groupCode) {
        return controller.getCountOfMotorNonMotorBasedOnGroupCode(groupCode);
    }

    public List<Object[]> testMethod(String groupCode) {
        return controller.testMethod(groupCode);
    }

//    public static void main(String[] args) {
//        System.out.println(new PremiumBordereauxReportDAO().getCountOfMotorNonMotorBasedOnGroupCode("PROPERTYANDBI_COM"));
//        System.out.println("Test method activated");
//        List<Object[]> list = new PremiumBordereauxReportDAO().testMethod("PROPERTYANDBI_COM");
//        list.stream()
//                .map(marshalMotorNonMotorRatioObject())
//                .forEach(printObject());
//        try {
//            PremiumBordereauxReportDAO dao = new PremiumBordereauxReportDAO();
//            List<PremiumBordereauxReport> list = dao.migrator.getDataFromPremiumBordereauxReportDumb().stream().distinct().collect(Collectors.toList());
//            
////        list.stream().forEach(object -> System.out.println(object.getPolicyNumber()));
//            dao.batchAdd(list);
//        } catch (Exception ex) {
//            Logger.getLogger(PremiumBordereauxReportDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

    private static Consumer<MotorNonMotorRatio> printObject() {
        return ratio -> System.out.println(" " + ratio.getPolicyNumber() + " " + ratio.getGroupCode() + " " + ratio.getAgencySubAgentName());
    }

    private static Function<Object[], MotorNonMotorRatio> marshalMotorNonMotorRatioObject() {
        return obj -> new MotorNonMotorRatio(obj[0].toString(), obj[1].toString(), obj[2].toString());
    }
}
