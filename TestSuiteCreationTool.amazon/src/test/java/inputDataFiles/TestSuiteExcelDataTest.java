package inputDataFiles;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import jira.LaunchJiraTest;

public class TestSuiteExcelDataTest extends UtilitiesData{

	XSSFWorkbook workbook;
	XSSFSheet sheet;
	XSSFRow rows;
	XSSFCell cellvalue;
	int rowcount;
	int columncount;
	DataFormatter format = new DataFormatter();
	//LaunchJiraTest obj1 = new LaunchJiraTest();
	
	@DataProvider(name = "ExcelData")
	public Object[][] inputdata() {
		String datafile = prop.getProperty("ExcelFile"); 
		//String datafile = "C:\\Users\\nharinib\\Desktop\\TestCyclesData.xlsx";
		//System.out.println(datafile);
		try {
			workbook = new XSSFWorkbook(datafile);
			sheet = workbook.getSheet("Sheet1");

			rowcount = sheet.getPhysicalNumberOfRows();
			columncount = sheet.getRow(0).getLastCellNum();
			System.out.println(rowcount);
			System.out.println(columncount);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		Object values[][] = new Object[rowcount-1][columncount];

		for (int r = 0; r < rowcount-1; r++) {
			rows = sheet.getRow(r+1);

			for (int c = 0; c < columncount; c++) {
				cellvalue = rows.getCell(c);
				//String value = cellvalue.getStringCellValue();
				String value= format.formatCellValue(cellvalue);
				values[r][c]=value;
			}
		}
		return values;

	}

	/*
	 * @Test(dataProvider = "ExcelData") public void testdata(Object CycleName,
	 * Object Testcases) { System.out.println(CycleName+"-"+Testcases); }
	 */

	/*@Test(enabled = false)
	public String getCellData(int rowNum, int colNum) {
		XSSFRow Rows = sheet.getRow(rowNum);
		XSSFCell Cells = Rows.getCell(colNum);
		//String CellValue = Cells.getStringCellValue();
		String finalCellValue = format.formatCellValue(Cells);
		return finalCellValue;}*/

}
