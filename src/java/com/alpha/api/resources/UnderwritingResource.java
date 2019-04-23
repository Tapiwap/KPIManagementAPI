/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alpha.api.resources;

import com.alpha.data.dao.DaysToIssueQuotesDAO;
import com.alpha.data.dao.PercentageClosedPolicyDAO;
import com.alpha.data.dao.PercentageComplianceDAO;
import com.alpha.data.dao.PercentageCostPerPulaGwpDAO;
import com.alpha.data.entities.Agent;
import com.alpha.data.entities.DaysToIssueQuotesUnderwriting;
import com.alpha.data.entities.PercentageClosedPolicyUnderwitting;
import com.alpha.data.entities.PercentageComplianceUnderwriting;
import com.alpha.data.entities.PercentageCostPerPulaGwp;
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
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Tsotsoh
 */
@Path("underwriting")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UnderwritingResource {

    @Context
    private UriInfo context;
    private static String RESPONSE = "Empty Response";
    private static String TAG;
    private final PercentageClosedPolicyDAO closedPolicyDao;
    private final DaysToIssueQuotesDAO daysToIssueDao;
    private final PercentageComplianceDAO complianceDao;
    private final PercentageCostPerPulaGwpDAO percentageCostDao;
    private static final String DATE_FORMAT = "dd-MM-yyyy hh:mm:ss";

    /**
     * Creates a new instance of UnderwritingResource
     */
    public UnderwritingResource() {
        closedPolicyDao = new PercentageClosedPolicyDAO();
        daysToIssueDao = new DaysToIssueQuotesDAO();
        complianceDao = new PercentageComplianceDAO();
        percentageCostDao = new PercentageCostPerPulaGwpDAO();
    }

    @POST
    @Path("agent")
    public String addAgent(String agent) {

        return null;
    }

    /**
     *
     * @param json
     * @return
     */
    @POST
    @Path("percentageClosedPolicy")
    @RolesAllowed({"UNDERWRITING_ASSOCIATE", "UNDERWRITING_MANAGER"})
    public String addPercentageClosedPolicy(String json) {
        try {
            PercentageClosedPolicyUnderwitting closedPolicy = new ObjectMapper().readValue(json, PercentageClosedPolicyUnderwitting.class);
            closedPolicyDao.add(closedPolicy);
            UnderwritingResource.RESPONSE = new ObjectMapper().writeValueAsString("Success");
        } catch (IOException ex) {
            UnderwritingResource.RESPONSE = "Server Error persisting Percentage Closed Policy: " + ex.getMessage();
            Logger.getLogger(UnderwritingResource.class.getName()).log(Level.SEVERE, UnderwritingResource.RESPONSE, ex);
        }
        return UnderwritingResource.RESPONSE;
    }

    /**
     *
     * Date format => dd-MM-yyyy hh:mm:ss
     *
     * @param startDate
     * @param endDate
     * @return
     */
    @GET
    @Path("aggregate/percentageClosedPolicy/{agent}/{startDate}/{endDate}")
    @RolesAllowed({"UNDERWRITING_ASSOCIATE", "UNDERWRITING_MANAGER"})
    public String getAggregatePercentageClosedPolicyBetweenDates(@PathParam("startDate") String startDate,
            @PathParam("endDate") String endDate, @PathParam("agent") String agent) {
        TAG = "aggregatePercentageClosedPolicy";
        String actualTag = "actualPercentage";
        String expectedTag = "expectedPercentage";
        try {
            SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
            Date _startDate = format.parse(startDate);
            Date _endDate = format.parse(endDate);
            List<PercentageClosedPolicyUnderwitting> list = closedPolicyDao.getSingularPercentageClosedPolicyBetweenDates(agent, _startDate, _endDate);
            OptionalDouble actualPercentage = getActualPercentageCLosedPolicy(list);
            OptionalDouble expectedPercentage = getExpectedPercentageClosedPolicy(list);
            Map<String, Double> aggregateMap = createMap(actualTag, actualPercentage, expectedTag, expectedPercentage);
            RESPONSE = new ObjectMapper().writeValueAsString(aggregateMap);
        } catch (ParseException | JsonProcessingException ex) {
            RESPONSE = "Server Error: " + ex.getMessage();
            Logger.getLogger(UnderwritingResource.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
        return RESPONSE;
    }

    @GET
    @Path("range/percentageClosedPolicy/{agent}/{startDate}/{endDate}")
    @RolesAllowed({"UNDERWRITING_ASSOCIATE", "UNDERWRITING_MANAGER"})
    public String getSingularPercentageClosedPolicyBetweenDates(@PathParam("startDate") String startDate,
            @PathParam("endDate") String endDate, @PathParam("agent") String agent) {
        TAG = "percentageClosedPolicyList";
        try {
            SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
            Date _startDate = format.parse(startDate);
            Date _endDate = format.parse(endDate);
            List<PercentageClosedPolicyUnderwitting> list = closedPolicyDao.getSingularPercentageClosedPolicyBetweenDates(agent, _startDate, _endDate);
            Map<String, List<PercentageClosedPolicyUnderwitting>> map = new HashMap<>();
            map.put("percentageClosedPolicyList", list);
            RESPONSE = new ObjectMapper().writeValueAsString(map);
        } catch (ParseException | JsonProcessingException ex) {
            RESPONSE = "Server Error: " + ex.getMessage();
            Logger.getLogger(UnderwritingResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return RESPONSE;
    }

    @POST
    @Path("avgDaysToIssueQuote")
    @RolesAllowed({"UNDERWRITING_ASSOCIATE", "UNDERWRITING_MANAGER"})
    public String addAvgDaysToIssueQuote(String json) {
        try {
            DaysToIssueQuotesUnderwriting daysToIssueQoutes = new ObjectMapper().readValue(json, DaysToIssueQuotesUnderwriting.class);
            daysToIssueDao.add(daysToIssueQoutes);
            UnderwritingResource.RESPONSE = new ObjectMapper().writeValueAsString("Success");
        } catch (IOException ex) {
            UnderwritingResource.RESPONSE = "Server Error persisting Average Days To Issue Qoutes: " + ex.getMessage();
            Logger.getLogger(UnderwritingResource.class.getName()).log(Level.SEVERE, UnderwritingResource.RESPONSE, ex);
        }
        return UnderwritingResource.RESPONSE;
    }

    @GET
    @Path("aggregate/avgDaysToIssueQuote/{agent}/{startDate}/{endDate}")
    @RolesAllowed({"UNDERWRITING_ASSOCIATE", "UNDERWRITING_MANAGER"})
    public String getAvgDaysToIssueQuoteAggregate(@PathParam("startDate") String startDate,
            @PathParam("endDate") String endDate, @PathParam("agent") String agent) {
        TAG = "avgDaysToIssueQuoteAggregate";
        String actualTag = "actualAvg";
        String expectedTag = "expectedAvg";

        try {
            SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
            Date _startDate = format.parse(startDate);
            Date _endDate = format.parse(endDate);
            List<DaysToIssueQuotesUnderwriting> list = daysToIssueDao.getAvgDaysToIssueQuoteSingular(agent, _startDate, _endDate);
            OptionalDouble actualAvg = list.parallelStream().mapToDouble(DaysToIssueQuotesUnderwriting::getActualDaysTaken).average();
            OptionalDouble expectedAvg = list.parallelStream().mapToDouble(DaysToIssueQuotesUnderwriting::getExpectedDaysTaken).average();
            Map<String, Double> aggregateMap = createMap(actualTag, actualAvg, expectedTag, expectedAvg);
            RESPONSE = new ObjectMapper().writeValueAsString(aggregateMap);
        } catch (ParseException | JsonProcessingException ex) {
            RESPONSE = "Server Error: " + ex.getMessage();
            Logger.getLogger(UnderwritingResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return RESPONSE;
    }

    @GET
    @Path("range/avgDaysToIssueQuote/{agent}/{startDate}/{endDate}")
    @RolesAllowed({"UNDERWRITING_ASSOCIATE", "UNDERWRITING_MANAGER"})
    public String getAvgDaysToIssueQuoteSingular(@PathParam("startDate") String startDate,
            @PathParam("endDate") String endDate, @PathParam("agent") String agent) {
        TAG = "avgDaysToIssueQuotesList";

        try {
            SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
            Date _startDate = format.parse(startDate);
            Date _endDate = format.parse(endDate);
            List<DaysToIssueQuotesUnderwriting> list = daysToIssueDao.getAvgDaysToIssueQuoteSingular(agent, _startDate, _endDate);
            Map<String, List<DaysToIssueQuotesUnderwriting>> map = new HashMap<>();
            map.put("avgDaysToIssueQuotesList", list);
            RESPONSE = new ObjectMapper().writeValueAsString(map);
        } catch (ParseException | JsonProcessingException ex) {
            RESPONSE = "Server Error: " + ex.getMessage();
            Logger.getLogger(UnderwritingResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return RESPONSE;
    }

    /**
     * Persists the percentage to workflow compliance record to the database
     *
     * @param json
     * @return
     */
    @POST
    @Path("percentageComplianceToWorkFlow")
    @RolesAllowed({"UNDERWRITING_MANAGER"})
    public String addPercentageComplianceToWorkFlow(String json) {
        try {
            PercentageComplianceUnderwriting pcu = new ObjectMapper().readValue(json, PercentageComplianceUnderwriting.class);
            complianceDao.add(pcu);
            UnderwritingResource.RESPONSE = new ObjectMapper().writeValueAsString("Success");
        } catch (IOException ex) {
            UnderwritingResource.RESPONSE = "Server Error persisting Percentage Compliance: " + ex.getMessage();
            Logger.getLogger(UnderwritingResource.class.getName()).log(Level.SEVERE, UnderwritingResource.RESPONSE, ex);
        }
        return UnderwritingResource.RESPONSE;
    }

    /**
     * Calculates an aggregate of the percentage compliance to workflow
     *
     * @param startDate
     * @param endDate
     * @return
     */
    @GET
    @Path("aggregate/percentageComplianceUnderwriting/{agent}/{startDate}/{endDate}")
    @RolesAllowed({"UNDERWRITING_ASSOCIATE", "UNDERWRITING_MANAGER"})
    public String getAggregatedPercentageComplianceUnderwritingBetweenRanges(@PathParam("startDate") String startDate,
            @PathParam("endDate") String endDate, @PathParam("agent") String agent) {
        String actualPercentageTag = "actualPercentage";
        String expectedPercentageTag = "expectedPercentage";
        try {
            SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
            Date _startDate = format.parse(startDate);
            Date _endDate = format.parse(endDate);
            List<PercentageComplianceUnderwriting> list = complianceDao.getPercentageBetweenRange(agent,_startDate, _endDate);
            OptionalDouble actualPercentage = list.parallelStream().mapToDouble(PercentageComplianceUnderwriting::getActualPercentage).average();
            OptionalDouble expectedPercentage = list.parallelStream().mapToDouble(PercentageComplianceUnderwriting::getExpectedPercentage).average();
            Map<String, Double> aggregateMap = createMap(actualPercentageTag, actualPercentage, expectedPercentageTag, expectedPercentage);
            RESPONSE = new ObjectMapper().writeValueAsString(aggregateMap);
        } catch (ParseException | JsonProcessingException ex) {
            RESPONSE = "Server Error: " + ex.getMessage();
            Logger.getLogger(UnderwritingResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return RESPONSE;
    }

    /**
     * Retrieves a series of the percentage compliance to workflow between
     * certain dates.
     *
     * @param startDate
     * @param endDate
     * @return
     */
    @GET
    @Path("range/percentageComplianceUnderwriting/{agent}/{startDate}/{endDate}")
    @RolesAllowed({"UNDERWRITING_ASSOCIATE", "UNDERWRITING_MANAGER"})
    public String getPercentageComplianceUnderwritingBetweenRangeSingular(@PathParam("startDate") String startDate,
            @PathParam("endDate") String endDate, @PathParam("agent") String agent) {
        TAG = "percentageComplianceUnderwritingSingular";
        try {
            SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
            Date _startDate = format.parse(startDate);
            Date _endDate = format.parse(endDate);

            List<PercentageComplianceUnderwriting> list = complianceDao.getPercentageBetweenRange(agent,_startDate, _endDate);
            Map<String, List<PercentageComplianceUnderwriting>> listMap = new HashMap<>();
            listMap.put("percentageComplianceList", list);
            RESPONSE = new ObjectMapper().writeValueAsString(listMap);
        } catch (ParseException | JsonProcessingException ex) {
            RESPONSE = "Server Error: " + ex.getMessage();
            Logger.getLogger(UnderwritingResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return RESPONSE;
    }

    /**
     * This method persists a Cost Per Pula Of GWP record to the database
     *
     * @param json
     * @return
     */
    @POST
    @Path("costPerPulaOfGWP")
    @RolesAllowed("UNDERWRITING_MANAGER")
    public String addCostPerPulaOfGWP(String json) {
        try {
            PercentageCostPerPulaGwp pcpp = new ObjectMapper().readValue(json, PercentageCostPerPulaGwp.class);
            percentageCostDao.add(pcpp);
            UnderwritingResource.RESPONSE = new ObjectMapper().writeValueAsString("Success");
        } catch (IOException ex) {
            UnderwritingResource.RESPONSE = "Server Error persisting Cost Per Pula Of GWP: " + ex.getMessage();
            Logger.getLogger(UnderwritingResource.class.getName()).log(Level.SEVERE, UnderwritingResource.RESPONSE, ex);
        }
        return UnderwritingResource.RESPONSE;
    }

    /**
     * Calculates an aggregate of the Cost Per Pula of GWP within a certain date
     * range.
     *
     * @param startDate
     * @param endDate
     * @return
     */
    @GET
    @Path("aggregate/costPerPulaOfGWP/{agent}/{startDate}/{endDate}")
    @RolesAllowed("UNDERWRITING_MANAGER")
    public String getAverageCostPerPulaOfGWPBetweenRanges(@PathParam("startDate") String startDate,
            @PathParam("endDate") String endDate, @PathParam("agent") String agent) {
        TAG = "aggregatedCostPerPulaOfGWP";
        try {
            SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
            Date _startDate = format.parse(startDate);
            Date _endDate = format.parse(endDate);            
            List<PercentageCostPerPulaGwp> list = percentageCostDao.getPercentageBetweenRange(agent, _startDate, _endDate);
            OptionalDouble actualCost = list.parallelStream().mapToDouble(PercentageCostPerPulaGwp::getActualPercentage).average();
            OptionalDouble expectedCost = list.parallelStream().mapToDouble(PercentageCostPerPulaGwp::getExpectedPercentage).average();            
            Map<String, Double> averagesMap = new HashMap<>();
            averagesMap.put("actualCost", actualCost.getAsDouble());
            averagesMap.put("expectedCost", expectedCost.getAsDouble());
            RESPONSE = new ObjectMapper().writeValueAsString(averagesMap);
        } catch (ParseException | JsonProcessingException ex) {
            RESPONSE = "Server Error: " + ex.getMessage();
            Logger.getLogger(UnderwritingResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return RESPONSE;
    }

    /**
     * Retrieves a series of Cost per pula between a certain data range; mainly
     * for graph plotting purposes.
     *
     * @param startDate
     * @param endDate
     * @return
     */
    @GET
    @Path("range/costPerPulaOfGWP/{agent}/{startDate}/{endDate}")
    @RolesAllowed("UNDERWRITING_MANAGER")
    public String getPercentageBetweenRangeSingular(@PathParam("startDate") String startDate,
            @PathParam("endDate") String endDate, @PathParam("agent") String agent) {
        TAG = "costPerPulaOfGWPSingular";
        try {
            SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
            Date _startDate = format.parse(startDate);
            Date _endDate = format.parse(endDate);
            List<PercentageCostPerPulaGwp> list = percentageCostDao.getPercentageBetweenRange(agent, _startDate, _endDate);
            Map<String, List<PercentageCostPerPulaGwp>> listMap = new HashMap<>();
            listMap.put(TAG, list);
            RESPONSE = new ObjectMapper().writeValueAsString(listMap);
        } catch (ParseException | JsonProcessingException ex) {
            RESPONSE = "Server Error: " + ex.getMessage();
            Logger.getLogger(UnderwritingResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return RESPONSE;
    }

    private Map<String, Double> createMap(String actualTag, OptionalDouble actualPercentage, String expectedTag, OptionalDouble expectedPercentage) {
        Map<String, Double> map = new HashMap<>();
        map.put(actualTag, actualPercentage.getAsDouble());
        map.put(expectedTag, expectedPercentage.getAsDouble());
        return map;
    }

    private OptionalDouble getExpectedPercentageClosedPolicy(List<PercentageClosedPolicyUnderwitting> list) {
        return list.parallelStream().mapToDouble(PercentageClosedPolicyUnderwitting::getExpectedPercentageClosedPolicy).average();
    }

    private OptionalDouble getActualPercentageCLosedPolicy(List<PercentageClosedPolicyUnderwitting> list) {
        return list.parallelStream().mapToDouble(PercentageClosedPolicyUnderwitting::getActualPercentageClosedPolicy).average();
    }
}
