/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alpha.data.dao;

import com.alpha.data.controllers.ClaimAsOnDateJpaController;
import com.alpha.data.entities.ClaimAsOnDate;
import java.util.Date;
import java.util.List;
import java.util.OptionalDouble;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Tsotsoh
 */
public class ClaimAsOnDateDAO {

    private final EntityManagerFactory emf;
    private final ClaimAsOnDateJpaController claimsCOntroller;

    public ClaimAsOnDateDAO() {
        emf = Persistence.createEntityManagerFactory("AlphaDirectKPIManagementPU");
        claimsCOntroller = new ClaimAsOnDateJpaController(emf);
    }

    public void add(ClaimAsOnDate claim) throws Exception {
        claimsCOntroller.create(claim);
    }

    public void batchAdd(List<ClaimAsOnDate> claimsList) throws Exception {
        claimsCOntroller.batchCreate(claimsList);
    }

    public void getClaimsByAgent(String agentName) {

    }

    public int getNumberOfClaimsHandledByAgent(String claimsAllocatedTo) {
        return claimsCOntroller.getNumberOfClaimsHandledByAgent(claimsAllocatedTo);
    }

    public int getNumberOfClaimsByAgentAndDesc(String claimsAllocatedTo, String shortDesc) {
        return claimsCOntroller.getNumberOfClaimsByAgentAndDescription(claimsAllocatedTo, shortDesc);
    }

    public int getTotalNumberOfClaimsByDesc(String shortDesc) {
        return claimsCOntroller.getNumberOfClaimsByShortDesc(shortDesc);
    }

    public int getNumberOfClaimsByReportedDate(Date date) {
        return claimsCOntroller.getNumberOfClaimsByReportedDate(date);
    }

    public int getNumberOfClaimsByStatusDate(Date date) {
        return claimsCOntroller.getNumberOfClaimsByStatusDate(date);
    }

    public int getNumberOfClaimsByReportedDateAndAgent(Date date, String allocatedTo) {
        return claimsCOntroller.getNumberOfClaimsByReportedDateAndAgent(date, allocatedTo);
    }

    public int getNumberOfClaimsByAgentBetweenStatusDates(Date startDate, Date endDate, String allocatedTo) {
        return claimsCOntroller.getNumberOfClaimsByAgentBetweenStatusDates(startDate, endDate, allocatedTo);
    }
    
    public int getNumberOfClaimsByAgentAndDescBetweenStatusDates(Date startDate, Date endDate, String allocatedTo, String desc){
        return claimsCOntroller.getNumberOfClaimsByAgentAndDescBetweenStatusDates(startDate, endDate, allocatedTo, desc);
    }

    public int getNumberOfClaimsByAgentBetweenReportedDates(Date startDate, Date endDate, String allocatedTo) {
        return claimsCOntroller.getNumberOfClaimsByAgentBetweenReportedDates(startDate, endDate, allocatedTo);
    }

    public List<Object> getDaysToResolveClaimsByAgent(String allocatedTo) {
        return claimsCOntroller.getDaysToResolveClaimsByAgent(allocatedTo);
    }

    public List<Object> getDaysToResolveClaimsByAgentAndDesc(String allocatedTo, String desc) {
        return claimsCOntroller.getDaysToResolveClaimsByAgentAndDesc(allocatedTo, desc);
    }
    
    public List<Object> getDaysToResolveClaimsByAgentAndDescBetweenDates(String allocatedTo, String desc,
            Date startDate, Date endDate){
        return claimsCOntroller.getDaysToResolveClaimsByAgentAndDescBetweenStatusDates(allocatedTo, desc, startDate, endDate);
    }
    
    public List<Object[]> getClaimsGroupedByMonth(String allocatedTo, String desc, Date startDate, Date endDate){
        return claimsCOntroller.getClaimsGroupedByMonth(allocatedTo, desc, startDate, endDate);
    }

//    public static void main(String[] args) {
//        List<ClaimAsOnDate> claimList = new ClaimsOnDateMigrator().getClaimsListFromDataDumb();
//        try {
//            int i = new ClaimAsOnDateDAO().getNumberOfClaimsHandledByAgent("Bogadi Makuku");
//            System.out.println("Total Number of Claims for Bogadi is " + i + " in total");
//
//            int j = new ClaimAsOnDateDAO().getNumberOfClaimsByAgentAndDesc("Bogadi Makuku", "Glass Claim");
//            System.out.println("Number of Glass Claims for Bogadi is " + j);
//
//            int k = new ClaimAsOnDateDAO().getTotalNumberOfClaimsByDesc("Glass Claim");
//            System.out.println("Totsl Number of Glass Claims is " + k);
//
//            int l = new ClaimAsOnDateDAO().getNumberOfClaimsByReportedDate(new Date("1/5/2015  4:02:46 AM"));
//            System.out.println("Totsl Number of Reported Claims By date is " + l);
//
//            int m = new ClaimAsOnDateDAO().getNumberOfClaimsByStatusDate(new Date("7/9/2015  4:41:49 PM"));
//            System.out.println("Totsl Number of Status Claims By date is " + m);
//
//            int n = new ClaimAsOnDateDAO().getNumberOfClaimsByReportedDateAndAgent(new Date("3/14/2016  9:50:26 AM"), "Gofaone Letshwao");
//            System.out.println("Totsl Number of Reported Claims By date and Agent For Gofa is " + n);
//
//            int o = new ClaimAsOnDateDAO().getNumberOfClaimsByAgentBetweenStatusDates(new Date("7/31/2015  1:39:57 PM"), new Date("11/5/2015  9:33:30 AM"), "Bogadi Makuku");
//            System.out.println("Totsl Number of Claims By status date range and Agent For Bogadi is " + o);
//
//            int p = new ClaimAsOnDateDAO().getNumberOfClaimsByAgentBetweenReportedDates(new Date("9/10/2015  10:32:06 AM"), new Date("9/25/2015  10:00:23 AM"), "Bogadi Makuku");
//            System.out.println("Totsl Number of Claims By reported date range and Agent For Bogadi is " + p);
//            
//            List<Object> list = new ClaimAsOnDateDAO().getDaysToResolveClaimsByAgent("Bojang K. Tamasiga");
//            List<Integer> ints = list.stream().map(object -> ((Number)object).intValue()).collect(Collectors.toList());
//            ints.stream().forEach(_int -> System.out.println("Days taken "+ _int));
//            OptionalDouble optional = ints.stream().mapToDouble(_int -> _int).average();
//            System.out.println(" Average ints is " + optional.getAsDouble());
//            
//            List<Object> a_c_list = new ClaimAsOnDateDAO().getDaysToResolveClaimsByAgentAndDesc("Bogadi Makuku", "Motor Accident Claim");
//            List<Integer> a_c_Ints = a_c_list.stream().map(object -> ((Number)object).intValue()).collect(Collectors.toList());
//            a_c_Ints.stream().forEach(_int -> System.out.println("Days taken "+ _int));
//            OptionalDouble a_c_optional = a_c_Ints.stream().mapToDouble(_int -> _int).average();
//            System.out.println(" Average a_c_Ints is " + a_c_optional.getAsDouble());
//            
//        } catch (Exception ex) {
//            Logger.getLogger(ClaimAsOnDateDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//          List<Object[]> cL = new ClaimAsOnDateDAO().getClaimsGroupedByMonth("Bogadi Makuku", "Motor Accident Claim", new Date("2/10/2015  10:32:06 AM") , new Date("6/25/2016  10:00:23 AM"));
//          cL.stream().forEach(claim -> System.out.println(claim[0] + " " + claim[1]));
//    }
}
