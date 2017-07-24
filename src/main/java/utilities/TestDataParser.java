package utilities;

import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TestDataParser {
	// Parses the given XLSX file having a header row and followed by values
	// and returns a 2D array of String type with sheet values stored in it
	public Object[][] ParseXLSX(String filename, String sheetname){
		String[][] tabArray = null;
		try{
			FileInputStream fis = new FileInputStream(filename);
	        // Access the required test data sheet
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sheet;
			// If the user doesn't provide which sheet to process, then go with first sheet
			if(sheetname == null){
				// Get the first sheet name to process further
	        	sheetname = wb.getSheetName(0);
			}
	        sheet = wb.getSheet(sheetname);

	        // Rows are counted from 0..n
	        // If you have 4 rows in your sheet, then totalRows will be '3' (0,1,2,3)
	        int totalRows = sheet.getLastRowNum();

	        // Columns are counted using number of Cells in first row from 1..n
	        // If you have 4 columns in a row, then totalColumns will have '4'
	        int totalColumns = GetColumnTotal(sheet);

			// We are not going to consider first row as it will be the header row
			// So create the size of 2D array respectively
	        tabArray = new String[totalRows][totalColumns];

	        // Loop through all rows in the sheet
	        // Start at row 1 as row 0 is our header row
	        for(int i = 1;i<=totalRows;i++){
	        	for(int j = 0;j<totalColumns;j++){
	        		tabArray[i-1][j] = sheet.getRow(i).getCell(j).toString();
	        	}
	        }	        
		}catch (Exception e){
			// Do something
			System.out.println("Exception : " + e.getMessage());
		}
		return tabArray;
	}

	// Utility to count the number of columns in the first row of given Excel sheet
	private static int GetColumnTotal(XSSFSheet sheet) {
		// Count the number of columns in first row
		XSSFRow row_temp = sheet.getRow(0);
		// getLastCellNum()- Gets the index of the last cell contained in this row **PLUS ONE**.
		return row_temp.getLastCellNum();
	}
}

