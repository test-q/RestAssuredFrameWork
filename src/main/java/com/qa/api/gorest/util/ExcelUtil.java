package com.qa.api.gorest.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {
	
	private static String TESTDATA_SHEETPATH = "src/main/java/com/qa/api/gorest/testdata/GiRest_TestData.xlsx";
	private static Workbook book;
	private static Sheet sheet;
	
	public static Object[][] getTestData(String sheetName) {
	 
			try {
				FileInputStream fi = new FileInputStream(TESTDATA_SHEETPATH);
				book  = WorkbookFactory.create(fi);
				sheet = book.getSheet(sheetName);
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (InvalidFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
			
			for(int row=0; row<sheet.getLastRowNum(); row++) {
				for(int col=0; col<sheet.getRow(0).getLastCellNum(); col++) {
					data[row][col] = sheet.getRow(row + 1).getCell(col).toString();
				}
			}
					
		return data;
	}

}
