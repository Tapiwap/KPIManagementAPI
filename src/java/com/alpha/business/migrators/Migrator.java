/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alpha.business.migrators;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

/**
 *
 * @author Tsotsoh
 */
public class Migrator {
    
    protected HSSFSheet loadExcelFileIntoMemory(String fileName) throws IOException, FileNotFoundException {
        final FileInputStream stream = new FileInputStream(new File(fileName));
        HSSFWorkbook workBook = new HSSFWorkbook(stream);
        HSSFSheet sheet = workBook.getSheetAt(0);
        return sheet;
    }
    
    protected String getCellStringData(HSSFSheet sheet, String fieldName, int rowNumber) {
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
    
    protected Date getCellDateData(HSSFSheet sheet, String fieldName, int rowNumber) {
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
}
