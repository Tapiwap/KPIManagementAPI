/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alpha.business.migrators;

import com.alpha.data.entities.TransactionReportByProduct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;

/**
 *
 * @author Tsotsoh
 */
public class TransactionReportMigrator extends Migrator {

    private static final String FILE = "data/Transaction Report By Product.xls";
    private static final String POLICY_NO = "Policy No";
    private static final String TRANSACTION = "Transaction";
    private static final String AGENCY_SUB_AGENT_NAME = "AgencySubAgentName";

    public List<TransactionReportByProduct> getDataFromDumb() {
        List<TransactionReportByProduct> list = new ArrayList<>();
        try {
            HSSFSheet sheet = loadExcelFileIntoMemory(FILE);
            for (int row = 2; row < sheet.getLastRowNum(); row++) {
                TransactionReportByProduct object = marshalTransaction(sheet, row);
                list.add(object);
            }
        } catch (IOException ex) {
            Logger.getLogger(TransactionReportMigrator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    private TransactionReportByProduct marshalTransaction(HSSFSheet sheet, int row) {
        TransactionReportByProduct object = new TransactionReportByProduct();
        object.setPolicyNumber(getCellStringData(sheet, POLICY_NO, row));
        object.setTransaction(getCellStringData(sheet, TRANSACTION, row));
        object.setAgencySubAgentName(getCellStringData(sheet, AGENCY_SUB_AGENT_NAME, row));
        return object;
    }
}
