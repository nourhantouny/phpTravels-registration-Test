package com.phptravels.Utilties;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;



import com.phptravels.base.TestBase;

public class TestUtils  extends TestBase{

	public TestUtils() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}
    public static void TakeSnapShot() throws IOException {
    	 File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		 FileUtils.copyFile(srcFile, new File("D:\\Eclips_workspace\\phpTravel\\Snapshots\\Registration Fail .png"));
    }
    
 public static Object[][] GetDataFromExcelSheet( String SheetName) throws Exception  {
    	
    	File file = new File("C:\\Users\\TOSHIBA\\Desktop\\Tdata.xlsx"); 
		FileInputStream fls = new FileInputStream(file); //loading the file
		XSSFWorkbook workbook = new XSSFWorkbook(fls);
		Sheet sheet= workbook.getSheet(SheetName);
		
		int Rows = sheet.getLastRowNum();
		int Columns = sheet.getRow(0).getLastCellNum();
		
		
		Object [][] Data = new Object[Rows][Columns];
		for (int i =0 ; i<Rows;i++) {
			
			for(int j=0; j<Columns;j++) {
				
				Data[i][j] = sheet.getRow(i).getCell(j).toString();
				
			}
		}
		return Data;
		
		
    }
	
}
