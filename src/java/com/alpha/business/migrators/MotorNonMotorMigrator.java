/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alpha.business.migrators;

import com.alpha.data.entities.MotorNonMotorRatio;
import com.alpha.data.entities.PremiumBordereauxReport;
import com.alpha.presentation.beans.TransationReportTemp;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.poi.hssf.usermodel.HSSFSheet;

/**
 *
 * @author Tsotsoh
 */
public class MotorNonMotorMigrator extends Migrator {

    private static final String TRANSACTION_REPORT = "data/Transaction Report By Product.xls";
    private static final String PREMIUM_BORDEREAUX = "data/Premium Bordereaux Report.xls";
    private static final String POLICY_NUMBER = "Policy No";
    private static final String P_POLICY_NUMBER = "POLICY NO";
    private static final String GROUP_CODE = "GROUP CODE";
    private static final String AGENT_NUAME = "AgencySubAgentName";

//    public static void main(String[] args) {
//        new MotorNonMotorMigrator().getMotorNonMotorData();
//    }

    public void getMotorNonMotorData() {
        List<TransationReportTemp> transactionList = getDataFromTransactionReportDumb();
        List<PremiumBordereauxReport> premiumList = getDataFromPremiumBordereauxReportDumb();
        Set<TransationReportTemp> transactionSet = new HashSet<>(transactionList);
        Set<PremiumBordereauxReport> premiumSet = new HashSet<>(premiumList);
        transactionSet.parallelStream().forEach(object -> System.out.println(object.getAgencySubAgentName()));
        System.out.println("            ......                      ");
        premiumSet.parallelStream().forEach(object ->  System.out.println(object.getGroupCode()));
    }

    public List<TransationReportTemp> getDataFromTransactionReportDumb() {
        List<TransationReportTemp> list = new ArrayList<>();
        try {
            HSSFSheet sheet = loadExcelFileIntoMemory(TRANSACTION_REPORT);
            for (int row = 2; row < sheet.getLastRowNum(); row++) {
                TransationReportTemp object = buildTransactionReportObject(sheet, row);
                list.add(object);
            }
        } catch (IOException ex) {
            Logger.getLogger(TransactionReportMigrator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<PremiumBordereauxReport> getDataFromPremiumBordereauxReportDumb() {
        List<PremiumBordereauxReport> list = new ArrayList<>();
        try {
            HSSFSheet sheet = loadExcelFileIntoMemory(PREMIUM_BORDEREAUX);
            for (int row = 2; row < sheet.getLastRowNum(); row++) {
                PremiumBordereauxReport object = buildPremiumBordereuaxReportObject(sheet, row);
                list.add(object);
            }
        } catch (IOException ex) {
            Logger.getLogger(TransactionReportMigrator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    private TransationReportTemp buildTransactionReportObject(HSSFSheet sheet, int row) {
        TransationReportTemp object = new TransationReportTemp();
        object.setPolicyNumber(getCellStringData(sheet, POLICY_NUMBER, row));
        object.setAgencySubAgentName(getCellStringData(sheet, AGENT_NUAME, row));
        return object;
    }

    private PremiumBordereauxReport buildPremiumBordereuaxReportObject(HSSFSheet sheet, int row) {
        PremiumBordereauxReport object = new PremiumBordereauxReport();
        object.setPolicyNumber(getCellStringData(sheet, P_POLICY_NUMBER, row));
        object.setGroupCode(getCellStringData(sheet, GROUP_CODE, row));
        return object;
    }
}
