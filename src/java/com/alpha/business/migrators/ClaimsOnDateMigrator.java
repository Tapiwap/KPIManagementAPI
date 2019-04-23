/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alpha.business.migrators;

import com.alpha.data.entities.ClaimAsOnDate;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

/**
 *
 * @author Tsotsoh
 */
public class ClaimsOnDateMigrator {

    private static final String CLAIM_AS_ON_DATE_XLS = "data/Claim As On Date Report.xls";

    private HSSFSheet loadExcelFileIntoMemory() throws IOException, FileNotFoundException {
        final FileInputStream stream = new FileInputStream(new File(CLAIM_AS_ON_DATE_XLS));
        HSSFWorkbook workBook = new HSSFWorkbook(stream);
        HSSFSheet sheet = workBook.getSheetAt(0);
        return sheet;
    }

    public String getCellStringData(HSSFSheet sheet, String fieldName, int rowNumber) {
        try {
            int columnNumber = -1;
            Row row = sheet.getRow(0); //set the first row i.e the column names row
            columnNumber = getColumnNumberByFieldName(row, fieldName, columnNumber);
            row = sheet.getRow(rowNumber - 1);
            if (isCellDataNumeric(row, columnNumber)) {
                return String.valueOf(row.getCell(columnNumber).getNumericCellValue());
            }
            return row.getCell(columnNumber).getStringCellValue();
        } catch (Exception ex) {
            printException(rowNumber, fieldName, ex);
            return "NaN";
        }
    }

    public Date getCellDateData(HSSFSheet sheet, String fieldName, int rowNumber) {
        try {
            int columnNumber = -1;
            Row row = sheet.getRow(0); //set the first row i.e the column names row
            columnNumber = getColumnNumberByFieldName(row, fieldName, columnNumber);
            row = sheet.getRow(rowNumber - 1);
            return row.getCell(columnNumber).getDateCellValue();
        } catch (Exception ex) {
            printException(rowNumber, fieldName, ex);
            return null;
        }
    }

    protected boolean isCellDataNumeric(Row row, int columnNumber) {
        if (row.getCell(columnNumber).getCellTypeEnum() == CellType.NUMERIC) {
            return true;
        }
        return false;
    }

    protected void printException(int rowNumber, String fieldName, Exception ex) {
        System.out.println("Exception caught at row " + rowNumber
                + " of column " + fieldName + "  :...... " + ex.getMessage());
    }

    protected int getColumnNumberByFieldName(Row row, String colName, int columnNumber) {
        for (int i = 0; i < row.getLastCellNum(); i++) {
            if (row.getCell(i).getStringCellValue().trim().equalsIgnoreCase(colName)) {
                columnNumber = i;
            }
        }
        return columnNumber;
    }

    private List<ClaimAsOnDate> marshalClaimsAsOnDateList(HSSFSheet sheet) {
        List<ClaimAsOnDate> claimsList = new ArrayList<>();
        for (int j = 2; j < sheet.getLastRowNum(); j++) {
            ClaimAsOnDate claim = marshalClaimAsOnDate(sheet, j);
            claimsList.add(claim);
        }
        return claimsList;
    }

    private ClaimAsOnDate marshalClaimAsOnDate(HSSFSheet sheet, int j) {
        //create a model object
        ClaimAsOnDate claim = new ClaimAsOnDate();
        //save each column data in the model field
        claim.setClaimNumber(getCellStringData(sheet, "Claim No", j));
        claim.setProductName(getCellStringData(sheet, "Product Name", j));
        claim.setStatusDate(getCellDateData(sheet, "Status Date", j));
        claim.setShortDescription(getCellStringData(sheet, "Short Desc.", j));
        claim.setReportedDate(getCellDateData(sheet, "Reported Date", j));
        claim.setClaimsAllocatedTo(getCellStringData(sheet, "Claims Allocated To", j));
        return claim;
    }

    public List<ClaimAsOnDate> getClaimsListFromDataDumb() {
        try {
            HSSFSheet sheet = loadExcelFileIntoMemory();
            return marshalClaimsAsOnDateList(sheet);
        } catch (IOException ex) {
            Logger.getLogger(ClaimsOnDateMigrator.class.getName()).log(Level.SEVERE, null, ex);
            return new ArrayList<>();
        }
    }

}
