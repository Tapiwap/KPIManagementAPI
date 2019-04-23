/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alpha.api.resources;

import com.alpha.data.dao.ComplianceCashOutBdDAO;
import com.alpha.data.dao.PrecentageComplianceBdDAO;
import com.alpha.data.dao.PremiumBordereauxReportDAO;
import com.alpha.data.dao.RenewalRationBdDAO;
import com.alpha.data.dao.TransactionReportDAO;
import com.alpha.data.entities.ComplianceCashOutBd;
import com.alpha.data.entities.PrecentageComplianceBd;
import com.alpha.data.entities.RenewalRationBd;
import com.alpha.data.entities.TransactionReportByProduct;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
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
@Path("businessDevelopment")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BusinessDevelopmentResource {

    @Context
    private UriInfo context;
    private static String RESPONSE = "Empty Response";
    private static String TAG;
    private final RenewalRationBdDAO renewalRatioDAO;
    private final PrecentageComplianceBdDAO percentageComplianceDAO;
    private final ComplianceCashOutBdDAO cashOutDAO;
    private final TransactionReportDAO transactionDAO;
    private final PremiumBordereauxReportDAO premiumDAO;
    private static final String DATE_FORMAT = "dd-MM-yyyy hh:mm:ss";
    private static int count = 0;
    private static final String NEW_BUSINESS = "NEWBUSINESS";

    /**
     * Creates a new instance of BusinessDevelopmentResource
     */
    public BusinessDevelopmentResource() {
        renewalRatioDAO = new RenewalRationBdDAO();
        percentageComplianceDAO = new PrecentageComplianceBdDAO();
        cashOutDAO = new ComplianceCashOutBdDAO();
        transactionDAO = new TransactionReportDAO();
        premiumDAO = new PremiumBordereauxReportDAO();
    }

    @POST
    @Path("renewalRationBd")
    @RolesAllowed({"BD_AGENT", "BD_MANAGER"})
    public String saveRenewalRationBd(String json) {
        try {
            RenewalRationBd ratio = new ObjectMapper().readValue(json, RenewalRationBd.class);
            renewalRatioDAO.add(ratio);
            BusinessDevelopmentResource.RESPONSE = new ObjectMapper().writeValueAsString("Success");
        } catch (IOException ex) {
            BusinessDevelopmentResource.RESPONSE = "Server error: " + ex.getMessage();
            Logger.getLogger(BusinessDevelopmentResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return BusinessDevelopmentResource.RESPONSE;
    }

    @POST
    @Path("percentageComplianceBd")
    @RolesAllowed({"BD_AGENT", "BD_MANAGER"})
    public String savePercentageCompliance(String json) {
        try {
            PrecentageComplianceBd compliance = new ObjectMapper().readValue(json, PrecentageComplianceBd.class);
            percentageComplianceDAO.add(compliance);
            BusinessDevelopmentResource.RESPONSE = new ObjectMapper().writeValueAsString("Success");
        } catch (IOException ex) {
            BusinessDevelopmentResource.RESPONSE = "Server error: " + ex.getMessage();
            Logger.getLogger(BusinessDevelopmentResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return BusinessDevelopmentResource.RESPONSE;
    }

    @POST
    @Path("complianceCashOutBd")
    @RolesAllowed({"BD_AGENT", "BD_MANAGER"})
    public String saveComplianceCashOutBd(String json) {
        try {
            ComplianceCashOutBd compliance = new ObjectMapper().readValue(json, ComplianceCashOutBd.class);
            cashOutDAO.add(compliance);
            BusinessDevelopmentResource.RESPONSE = new ObjectMapper().writeValueAsString("Success");
        } catch (IOException ex) {
            BusinessDevelopmentResource.RESPONSE = "Server error: " + ex.getMessage();
            Logger.getLogger(BusinessDevelopmentResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return BusinessDevelopmentResource.RESPONSE;
    }

    /**
     *
     * Date format => dd-MM-yyyy hh:mm:ss
     *
     * @param type
     * @param startDate
     * @param endDate
     * @return
     */
    @GET
    @Path("aggregate/complianceCashOut/{agent}/{type}/{startDate}/{endDate}")
    @RolesAllowed({"BD_AGENT", "BD_MANAGER"})
    public String getAggregateComplianceCashOutByTypeAndDates(@PathParam("agent") String agent, @PathParam("type") String type,
            @PathParam("startDate") String startDate, @PathParam("endDate") String endDate) {
        String tag = "aggregateComplianceCashOutByTypeAndDates";
        try {
            SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
            Date _startDate = format.parse(startDate);
            Date _endDate = format.parse(endDate);
            List<ComplianceCashOutBd> list = cashOutDAO.getSingularByTypeBetweenDates(agent, type, _startDate, _endDate);
            OptionalDouble actualCompliance = list.parallelStream().mapToDouble(ComplianceCashOutBd::getActualComliance).average();
            OptionalDouble expectedCompliance = list.parallelStream().mapToDouble(ComplianceCashOutBd::getExpectedCompliance).average();
            Map<String, Double> aggregateMap = new HashMap<>();
            aggregateMap.put("actualCompliancePercentage", actualCompliance.getAsDouble());
            aggregateMap.put("expectedCompliancePercentage", expectedCompliance.getAsDouble());
            RESPONSE = new ObjectMapper().writeValueAsString(aggregateMap);
        } catch (ParseException | JsonProcessingException ex) {
            RESPONSE = "Server Error: " + ex.getMessage();
            Logger.getLogger(BusinessDevelopmentResource.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
        return RESPONSE;
    }

    @GET
    @Path("aggregate/percentageComplianceBd/{agent}/{type}/{startDate}/{endDate}")
    @RolesAllowed({"BD_AGENT", "BD_MANAGER"})
    public String getAggregatePercentageComplianceBdByTypeAndDates(@PathParam("agent") String agent, @PathParam("type") String type,
            @PathParam("startDate") String startDate, @PathParam("endDate") String endDate) {
        String actualPercentageTag = "actualPercentageComplianceBd";
        String expectedPercentageTag = "expectedPercentageComplianceBd";
        try {
            SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
            Date _startDate = format.parse(startDate);
            Date _endDate = format.parse(endDate);
            List<PrecentageComplianceBd> list = percentageComplianceDAO.getSingularByTypeBetweenDates(agent, type, _startDate, _endDate);
            OptionalDouble actualPercentage = list.parallelStream().mapToDouble(PrecentageComplianceBd::getActualPercentage).average();
            OptionalDouble expectedPercentage = list.parallelStream().mapToDouble(PrecentageComplianceBd::getExpectedPercentage).average();
            Map<String, Double> aggregateMap = new HashMap<>();
            aggregateMap.put(actualPercentageTag, actualPercentage.getAsDouble());
            aggregateMap.put(expectedPercentageTag, expectedPercentage.getAsDouble());
            RESPONSE = new ObjectMapper().writeValueAsString(aggregateMap);
        } catch (ParseException | JsonProcessingException ex) {
            RESPONSE = "Server Error: " + ex.getMessage();
            Logger.getLogger(BusinessDevelopmentResource.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
        return RESPONSE;
    }

    @GET
    @Path("aggregate/renewalRationBd/{agent}/{type}/{startDate}/{endDate}")
    @RolesAllowed({"BD_AGENT", "BD_MANAGER"})
    public String getAggregateRenewalRationBdByTypeAndDates(@PathParam("agent") String agent, @PathParam("type") String type,
            @PathParam("startDate") String startDate, @PathParam("endDate") String endDate) {
        String actualTag = "actualRenewalRationBd";
        String expectedTag = "expectedRenewalRationBd";
        try {
            SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
            Date _startDate = format.parse(startDate);
            Date _endDate = format.parse(endDate);
            List<RenewalRationBd> list = renewalRatioDAO.getSingularByTypeBetweenDates(agent, type, _startDate, _endDate);
            OptionalDouble actualRenewalRatio = list.parallelStream().mapToDouble(RenewalRationBd::getActualRatio).average();
            OptionalDouble expectedRenewalRatio = list.parallelStream().mapToDouble(RenewalRationBd::getExpectedRatio).average();
            System.out.println(actualRenewalRatio);
            Map<String, Double> aggregateMap = new HashMap<>();
            aggregateMap.put(actualTag, actualRenewalRatio.getAsDouble());
            aggregateMap.put(expectedTag, expectedRenewalRatio.getAsDouble());
            RESPONSE = new ObjectMapper().writeValueAsString(aggregateMap);
        } catch (ParseException | JsonProcessingException ex) {
            RESPONSE = "Server Error: " + ex.getMessage();
            Logger.getLogger(BusinessDevelopmentResource.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
        return RESPONSE;
    }

    @GET
    @Path("range/complianceCashOut/{agent}/{type}/{startDate}/{endDate}")
    @RolesAllowed({"BD_AGENT", "BD_MANAGER"})
    public String getSingularComplianceCashOutByTypeAndDates(@PathParam("agent") String agent, @PathParam("type") String type,
            @PathParam("startDate") String startDate, @PathParam("endDate") String endDate) {
        String tag = "complianceCashOutList";
        try {
            SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
            Date _startDate = format.parse(startDate);
            Date _endDate = format.parse(endDate);
            List<ComplianceCashOutBd> list = cashOutDAO.getSingularByTypeBetweenDates(agent, type, _startDate, _endDate);
            Map<String, List<ComplianceCashOutBd>> map = new HashMap<>();
            map.put(tag, list);
            RESPONSE = new ObjectMapper().writeValueAsString(map);
        } catch (ParseException | JsonProcessingException ex) {
            RESPONSE = "Server Error: " + ex.getMessage();
            Logger.getLogger(BusinessDevelopmentResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return RESPONSE;
    }

    @GET
    @Path("range/renewalRationBd/{agent}/{type}/{startDate}/{endDate}")
    @RolesAllowed({"BD_AGENT", "BD_MANAGER"})
    public String getSingularRenewalRationBdByTypeAndDates(@PathParam("agent") String agent, @PathParam("type") String type,
            @PathParam("startDate") String startDate, @PathParam("endDate") String endDate) {
        String tag = "renewalRationBdList";
        try {
            SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
            Date _startDate = format.parse(startDate);
            Date _endDate = format.parse(endDate);
            List<RenewalRationBd> list = renewalRatioDAO.getSingularByTypeBetweenDates(agent, type, _startDate, _endDate);
            Map<String, List<RenewalRationBd>> map = new HashMap<>();
            map.put(tag, list);
            RESPONSE = new ObjectMapper().writeValueAsString(map);
        } catch (ParseException | JsonProcessingException ex) {
            RESPONSE = "Server Error: " + ex.getMessage();
            Logger.getLogger(BusinessDevelopmentResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return RESPONSE;
    }

    @GET
    @Path("range/percentageComplianceBd/{agent}/{type}/{startDate}/{endDate}")
    @RolesAllowed({"BD_AGENT", "BD_MANAGER"})
    public String getSingularPercentageComplianceBdByTypeAndDates(@PathParam("agent") String agent, @PathParam("type") String type,
            @PathParam("startDate") String startDate, @PathParam("endDate") String endDate) {
        String tag = "percentageComplianceBdList";
        try {
            SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
            Date _startDate = format.parse(startDate);
            Date _endDate = format.parse(endDate);
            List<PrecentageComplianceBd> list = percentageComplianceDAO.getSingularByTypeBetweenDates(agent, type, _startDate, _endDate);
            Map<String, List<PrecentageComplianceBd>> map = new HashMap<>();
            map.put(tag, list);
            RESPONSE = new ObjectMapper().writeValueAsString(map);
        } catch (ParseException | JsonProcessingException ex) {
            RESPONSE = "Server Error: " + ex.getMessage();
            Logger.getLogger(BusinessDevelopmentResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return RESPONSE;
    }

    /**
     * Retrieves a list of % policies closed records for a specific agent
     *
     * @param name
     * @return
     */
    @GET
    @Path("policiesClosed/{agent}")
    @RolesAllowed({"BD_AGENT", "BD_MANAGER"})
    public String getPoliciesClosedByAgentName(@PathParam("agent") String name) {
        try {
            String tag = "policiesClosed";
            List<TransactionReportByProduct> list = transactionDAO.getBySubAgentName(name);
            Map<String, List<TransactionReportByProduct>> map = new HashMap<>();
            map.put(tag, list);
            RESPONSE = new ObjectMapper().writeValueAsString(map);
        } catch (JsonProcessingException ex) {
            RESPONSE = "Server error: " + ex.getMessage();
            Logger.getLogger(BusinessDevelopmentResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return RESPONSE;
    }

    /**
     * Calculates an aggregate of the % policies closed by a specific agent
     *
     * @param name
     * @return
     */
    @GET
    @Path("policiesClosed/count/{agent}")
    @RolesAllowed({"BD_AGENT", "BD_MANAGER"})
    public String getPoliciesClosedCountByAgentName(@PathParam("agent") String name) {
        try {
            String tag = "policiesClosed";
            int result = transactionDAO.getCountBySubAgentName(name);
            Map<String, Integer> map = new HashMap<>();
            map.put(tag, result);
            RESPONSE = new ObjectMapper().writeValueAsString(map);
        } catch (JsonProcessingException ex) {
            RESPONSE = "Server error: " + ex.getMessage();
            Logger.getLogger(BusinessDevelopmentResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return RESPONSE;
    }

    @GET
    @Path("count/motorNonMotorRatio/{groupCode}")
    @RolesAllowed({"BD_AGENT", "BD_MANAGER"})
    public String getCountOfMotorNonMotorBasedOnGroupCode(@PathParam("groupCode") String groupCode) {
        try {
            String tag = "countOfMotorNonMotorBasedOnGroupCode";
            int result = premiumDAO.getCountOfMotorNonMotorBasedOnGroupCode(groupCode);
            Map<String, Integer> map = new HashMap<>();
            map.put(tag, result);
            return RESPONSE = new ObjectMapper().writeValueAsString(map);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(BusinessDevelopmentResource.class.getName()).log(Level.SEVERE, null, ex);
            return RESPONSE = "Server error: " + ex.getMessage();
        }
    }

    @GET
    @Path("percentageOfPoliciesClosed/agent/{agentName}")
    @RolesAllowed({"BD_AGENT", "BD_MANAGER"})
    public String getPercentageOfPoliciesClosed(@PathParam("agentName") String name) {
        try {
            String tag = "percentageOfClosedPolicies";
            List<TransactionReportByProduct> list = transactionDAO.getBySubAgentName("Yvone V. Chali");
            double count_ = list.parallelStream()
                    .filter(policy -> policy.getTransaction().equalsIgnoreCase(NEW_BUSINESS))
                    .count();
            Double percentage = (count_ / (double) list.size()) * 100;
            Map<String, Double> percentageMap = new HashMap<>();
            percentageMap.put(tag, percentage);
            return RESPONSE = new ObjectMapper().writeValueAsString(percentageMap);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(BusinessDevelopmentResource.class.getName()).log(Level.SEVERE, null, ex);
            return RESPONSE = "Server error: " + ex.getMessage();
        }
    }
    
    @GET
    @Path("motorNonMotor/agent/{agentName}")
    @RolesAllowed({"BD_AGENT", "BD_MANAGER"})
    public String getMotorNonMotorRatioByAgent(@PathParam("agentName") String name) {
        try {
            List<Object[]> list = transactionDAO.getBySubAgentNameForMotorNonMotorRatio(name);
            double motorCount = list.parallelStream().filter(obj -> obj[2].toString().equalsIgnoreCase("MOTOR_COM")).count();
            double nonMotorCount = list.parallelStream().filter(obj -> !obj[2].toString().equalsIgnoreCase("MOTOR_COM")).count();
            Map<String, Double> ratioMap = new HashMap<>();
            ratioMap.put("motorCount", motorCount);
            ratioMap.put("nonMotorCount", nonMotorCount);
            return RESPONSE = new ObjectMapper().writeValueAsString(ratioMap);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(BusinessDevelopmentResource.class.getName()).log(Level.SEVERE, null, ex);
            return RESPONSE = "Error Calculating Motor/NonMotor Ratio ";
        }
    }
}
