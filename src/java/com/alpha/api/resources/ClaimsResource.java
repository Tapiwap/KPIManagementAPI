/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alpha.api.resources;

import com.alpha.data.dao.AssessmentCostPerClaimDAO;
import com.alpha.data.dao.ClaimAsOnDateDAO;
import com.alpha.data.dao.PercentageComplianceClaimsDAO;
import com.alpha.data.entities.AssessmentCostPerClaim;
import com.alpha.data.entities.PercentageComplianceClaims;
import com.alpha.presentation.beans.MonthCount;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.annotation.security.DenyAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Tsotsoh
 */
@Path("claims")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ClaimsResource {

    @Context
    private UriInfo context;

    private final ClaimAsOnDateDAO claimsDao;
    private final AssessmentCostPerClaimDAO assessmentCostPerClaimDAO;
    private final PercentageComplianceClaimsDAO compliaceDao;
    private static final String DATE_FORMAT = "dd-MM-yyyy hh:mm:ss";

    /**
     * Creates a new instance of ClaimsResource
     */
    public ClaimsResource() {
        claimsDao = new ClaimAsOnDateDAO();
        assessmentCostPerClaimDAO = new AssessmentCostPerClaimDAO();
        compliaceDao = new PercentageComplianceClaimsDAO();
    }

    /**
     * Retrieves the number of claims handled to completion by an agent between
     * status dates Dates must be formated dd-MM-yyyy hh:mm:ss
     *
     * @param startDate accepts the initial date of the date range
     * @param endDate accepts the last date of the date range
     * @param agent accepts the name of the agent
     * @return a JSON result with the number of days
     */
    @GET
    @Path("agent/{name}/status/{startDate}/{endDate}")
    @RolesAllowed({"JUNIOR_CLAIMS_UNDERWITER", "CLAIMS_SUPERVISOR", "CLAIMS_MANAGER"})
    public String getNumberOfClaimsByAgentBetweenStatusDates(@PathParam("startDate") String startDate, @PathParam("endDate") String endDate,
            @PathParam("name") String agent) {
        String response = "";
        try {
            SimpleDateFormat dateFormatter = new SimpleDateFormat(DATE_FORMAT);
            Date _startDate = dateFormatter.parse(startDate);
            Date _endDate = dateFormatter.parse(endDate);
            int numberOfClaims = claimsDao.getNumberOfClaimsByAgentBetweenStatusDates(_startDate, _endDate, agent);
            Map<String, Integer> numberOfClaimsMap = new HashMap<>();
            final String MAP_KEY = "numberOfClaims";
            numberOfClaimsMap.put(MAP_KEY, numberOfClaims);
            response = new ObjectMapper().writeValueAsString(numberOfClaimsMap);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(ClaimsResource.class.getName()).log(Level.SEVERE, null, ex);
            response = "Exception caught here... " + ex.getMessage();
        } catch (ParseException ex) {
            Logger.getLogger(ClaimsResource.class.getName()).log(Level.SEVERE, null, ex);
            response = "Exception caught here... " + ex.getMessage();
        }
        return response;
    }

    /**
     * Retrieves the number of claims handled by an agent to completion between
     * status dates and according to a specific description Dates must be
     * formated dd-MM-yyyy hh:mm:ss
     *
     * @param startDate accepts the initial date of the date range
     * @param endDate accepts the last date of the date range
     * @param agent accepts the name of the agent
     * @param desc accepts the short description of the claim
     * @return a JSON result with the number of days
     */
    @GET
    @Path("agent/{name}/desc/{descr}/status/{startDate}/{endDate}")
    @RolesAllowed({"JUNIOR_CLAIMS_UNDERWITER", "CLAIMS_SUPERVISOR", "CLAIMS_MANAGER"})
    public String getNumberOfClaimsByAgentAndDescBetweenStatusDates(@PathParam("startDate") String startDate, @PathParam("endDate") String endDate,
            @PathParam("name") String agent, @PathParam("descr") String desc) {
        String response = "";
        try {
            SimpleDateFormat dateFormatter = new SimpleDateFormat(DATE_FORMAT);
            Date _startDate = dateFormatter.parse(startDate);
            Date _endDate = dateFormatter.parse(endDate);
            int numberOfClaims = claimsDao.getNumberOfClaimsByAgentAndDescBetweenStatusDates(_startDate, _endDate, agent, desc);
            Map<String, Integer> numberOfClaimsMap = new HashMap<>();
            final String MAP_KEY = "numberOfClaims";
            numberOfClaimsMap.put(MAP_KEY, numberOfClaims);
            response = new ObjectMapper().writeValueAsString(numberOfClaimsMap);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(ClaimsResource.class.getName()).log(Level.SEVERE, null, ex);
            response = "Exception caught here... " + ex.getMessage();
        } catch (ParseException ex) {
            Logger.getLogger(ClaimsResource.class.getName()).log(Level.SEVERE, null, ex);
            response = "Exception caught here... " + ex.getMessage();
        }
        return response;
    }

    /**
     * This method calculates the average time (in days) to complete a certain
     * claim by a specific agent between two dates; reported date and status
     * date.
     *
     * @param agent
     * @param desc
     * @param startDate
     * @param endDate
     * @return
     */
    @GET
    @Path("average/{name}/{desc}/{startDate}/{endDate}")
    @RolesAllowed({"JUNIOR_CLAIMS_UNDERWITER", "CLAIMS_SUPERVISOR", "CLAIMS_MANAGER"})
    public String getAverageNumberOfDaysByAgentAndDescBetweenDates(@PathParam("name") String agent, @PathParam("desc") String desc,
            @PathParam("startDate") String startDate, @PathParam("endDate") String endDate) {
        String response = "";
        try {
            SimpleDateFormat dateFormatter = new SimpleDateFormat(DATE_FORMAT);
            Date _startDate = dateFormatter.parse(startDate);
            Date _endDate = dateFormatter.parse(endDate);
            List<Object> list = new ClaimAsOnDateDAO().getDaysToResolveClaimsByAgentAndDescBetweenDates(agent, desc, _startDate, _endDate);
            List<Integer> ints = list.stream().map(object -> ((Number) object).intValue()).collect(Collectors.toList());
            OptionalDouble average = ints.stream().mapToDouble(_int -> _int).average();
            Map<String, OptionalDouble> averageMap = new HashMap<>();
            final String MAP_KEY = "averageDays";
            averageMap.put(MAP_KEY, average);
            response = new ObjectMapper().writeValueAsString(averageMap);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(ClaimsResource.class.getName()).log(Level.SEVERE, null, ex);
            response = "Exception caught here... " + ex.getMessage();
        } catch (ParseException ex) {
            Logger.getLogger(ClaimsResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }

    /**
     * This method calculates the number of claims; between certain months,
     * handled by a certain agent. The claims are grouped by month of year; i.e.
     * MM/yyyy
     *
     * @param agent
     * @param desc
     * @param startDate
     * @param endDate
     * @return
     */
    @GET
    @Path("grouped/{name}/{desc}/{startDate}/{endDate}")
    @RolesAllowed({"JUNIOR_CLAIMS_UNDERWITER", "CLAIMS_SUPERVISOR", "CLAIMS_MANAGER"})
    public String getNumberOfClaimsByAgentAndDescGroupedByMonthAndYear(@PathParam("name") String agent, @PathParam("desc") String desc,
            @PathParam("startDate") String startDate, @PathParam("endDate") String endDate) {
        String response = "";
        try {
            SimpleDateFormat dateFormatter = new SimpleDateFormat(DATE_FORMAT);
            Date _startDate = dateFormatter.parse(startDate);
            Date _endDate = dateFormatter.parse(endDate);
            List<Object[]> groupedClaims = new ClaimAsOnDateDAO().getClaimsGroupedByMonth(agent, desc, _startDate, _endDate);
            List<MonthCount> mcList = marshalMonthCount(groupedClaims);
            Map<String, List<MonthCount>> groupedClaimsMap = new HashMap<>();
            groupedClaimsMap.put("GroupedClaims", mcList);
            response = new ObjectMapper().writeValueAsString(groupedClaimsMap);
        } catch (ParseException | JsonProcessingException ex) {
            Logger.getLogger(ClaimsResource.class.getName()).log(Level.SEVERE, null, ex);
            response = "Exception caught ... " + ex.getMessage();
        }
        return response;
    }

    private List<MonthCount> marshalMonthCount(List<Object[]> groupedClaims) {
        List<MonthCount> mcList = new ArrayList<>();
        for (Object[] array : groupedClaims) {
            int i = ((Long) array[0]).intValue();
            int j = (Integer) array[1];
            MonthCount mc = new MonthCount(i, j);
            mcList.add(mc);
        }
        return mcList;
    }
    
    /**
     * 
     * @param json
     * @return 
     */
    @POST
    @Path("percentageComplianceClaims")
    @RolesAllowed({"CLAIMS_SUPERVISOR", "CLAIMS_MANAGER"})
    public String savePercentageCompliace(String json){
        String response = "Could Not Save Entity.";
        try {
            PercentageComplianceClaims compliance = new ObjectMapper().readValue(json, PercentageComplianceClaims.class);
            compliaceDao.add(compliance);
            response = "Percentage Compliance Saved Successfully.";
        } catch (IOException ex) {
            Logger.getLogger(ClaimsResource.class.getName()).log(Level.SEVERE, "Error: ", ex);            
            response = "Error Encountered.";
        }
        return response;
    }
    
    @GET
    @Path("aggregate/percentageComplianceClaims/{agent}/{startDate}/{endDate}")
    @RolesAllowed({"JUNIOR_CLAIMS_UNDERWITER", "CLAIMS_SUPERVISOR", "CLAIMS_MANAGER"})
    public String getAveragePercentageCompliace(@PathParam("agent") String agent,
            @PathParam("startDate") String startDate, @PathParam("endDate") String endDate){
        String response = "No Result Found.";
        String actualPercentageTag = "actualPercentageComplianceClaims";
        String expectedPercentageTag = "expectedPercentageComplianceClaims";
        
        try {
            SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
            Date _startDate = format.parse(startDate);
            Date _endDate = format.parse(endDate);
            System.out.println(agent + " " + _startDate + "    " +  _endDate);
            List<PercentageComplianceClaims> list = compliaceDao.getPercentageBetweenRange(agent, _startDate, _endDate);
            OptionalDouble actualPercentage = list.parallelStream().mapToDouble(PercentageComplianceClaims::getActualPercentage).average();
            OptionalDouble expectedPercentage = list.parallelStream().mapToDouble(PercentageComplianceClaims::getExpectedPercentage).average();
            Map<String, Double> aggregateMap = new HashMap<>();
            aggregateMap.put(actualPercentageTag, actualPercentage.getAsDouble());
            aggregateMap.put(expectedPercentageTag, expectedPercentage.getAsDouble());
            response = new ObjectMapper().writeValueAsString(aggregateMap);
        }  catch (ParseException | JsonProcessingException ex) {
            response = "Server Error: " + ex.getMessage();
            Logger.getLogger(ClaimsResource.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
        return response;
    }
    
    @GET
    @Path("range/percentageComplianceClaims/{agent}/{startDate}/{endDate}")
    @RolesAllowed({"JUNIOR_CLAIMS_UNDERWITER", "CLAIMS_SUPERVISOR", "CLAIMS_MANAGER"})
    public String getPercentageCompliaceWithInRange(@PathParam("agent") String agent,
            @PathParam("startDate") String startDate, @PathParam("endDate") String endDate){
        String response = "No Result Found.";
        String tag = "percentageComplianceClaimsList";
        
        try {
            SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
            Date _startDate = format.parse(startDate);
            Date _endDate = format.parse(endDate);
            System.out.println(agent + " " + _startDate + "    " +  _endDate);
            List<PercentageComplianceClaims> list = compliaceDao.getPercentageBetweenRange(agent, _startDate, _endDate);
            Map<String, List> rangeMap = new HashMap<>();
            rangeMap.put(tag, list);
            response = new ObjectMapper().writeValueAsString(rangeMap);
        }  catch (ParseException | JsonProcessingException ex) {
            response = "Server Error: " + ex.getMessage();
            Logger.getLogger(ClaimsResource.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
        return response;
    }
    
    /**
     * This method retrieves the assessment cost per claim data over a period of
     * time; primarily for graph plotting purposes.
     *
     * @param startDate
     * @param endDate
     * @return
     */
    @GET
    @Path("range/assessmentCostPerClaim/{startDate}/{endDate}")
    @RolesAllowed("CLAIMS_MANAGER")
    public String getAssessmentCostPerClaimBetweenDates(@PathParam("startDate") String startDate,
            @PathParam("endDate") String endDate) {
        String response = "";
        try {
            SimpleDateFormat dateFormatter = new SimpleDateFormat(DATE_FORMAT);
            Date _startDate = dateFormatter.parse(startDate);
            Date _endDate = dateFormatter.parse(endDate);
            List<AssessmentCostPerClaim> list = assessmentCostPerClaimDAO.getBetweenDates(_startDate, _endDate);
            Map<String, List<AssessmentCostPerClaim>> map = new HashMap<>();
            map.put("assessmentCostPerClaim", list);
            response = new ObjectMapper().writeValueAsString(map);
        } catch (ParseException | JsonProcessingException ex) {
            Logger.getLogger(ClaimsResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }

    /**
     * This method retrieves an aggregate result of the
     *
     * @param startDate
     * @param endDate
     * @return
     */
    @GET
    @Path("count/assessmentCostPerClaim/{startDate}/{endDate}")
    @RolesAllowed("CLAIMS_MANAGER")
    public String getAssessmentCostPerClaimCountBetweenDates(@PathParam("startDate") String startDate,
            @PathParam("endDate") String endDate) {
        String response = "";
        try {
            SimpleDateFormat dateFormatter = new SimpleDateFormat(DATE_FORMAT);
            Date _startDate = dateFormatter.parse(startDate);
            Date _endDate = dateFormatter.parse(endDate);
            int count = assessmentCostPerClaimDAO.getCountBetweenDates(_startDate, _endDate);
            Map<String, Integer> map = new HashMap<>();
            map.put("assessmentCostPerClaim", count);
            response = new ObjectMapper().writeValueAsString(map);
        } catch (ParseException | JsonProcessingException ex) {
            Logger.getLogger(ClaimsResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }
}
