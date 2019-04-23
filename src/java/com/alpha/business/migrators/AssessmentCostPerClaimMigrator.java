/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alpha.business.migrators;
import java.util.List;

import com.alpha.data.entities.AssessmentCostPerClaim;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tsotsoh
 */
public class AssessmentCostPerClaimMigrator extends Migrator{
    
    private static final String FILE = "data/Claim Bordereaux Payment Report.xls";
    private static final String REPORTED_DATE = "Reported Date";
    private static final String TRANS_SUB_TYPE = "Trans Sub Type";
    private static final String PAYMENT_AMOUNT = "Rserve / Payment Amt";
    
    public List<AssessmentCostPerClaim> getDataFromDumb(){
        List<AssessmentCostPerClaim> list = new ArrayList<>();
        try {
            HSSFSheet sheet = loadExcelFileIntoMemory(FILE);
            for (int row = 2; row < sheet.getLastRowNum(); row++) {
                AssessmentCostPerClaim object = AssessmentCostPerClaim(sheet, row);
                list.add(object);
            }
        } catch (IOException ex) {
            Logger.getLogger(TransactionReportMigrator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    private AssessmentCostPerClaim AssessmentCostPerClaim(HSSFSheet sheet, int row) {
        AssessmentCostPerClaim object = new AssessmentCostPerClaim();
        object.setReportedDate(getCellDateData(sheet, REPORTED_DATE, row));
        object.setRservePaymentAmt(new BigDecimal(getCellStringData(sheet, PAYMENT_AMOUNT, row)));
        object.setTransSubType(getCellStringData(sheet, TRANS_SUB_TYPE, row));
        return object;
    }
}
