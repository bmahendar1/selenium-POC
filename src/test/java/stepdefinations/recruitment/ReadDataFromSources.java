package stepdefinations.recruitment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import config.initialization.DataFiles;

public class ReadDataFromSources {
	private File f;

	@Given("User has the excel file")
	public void user_has_the_excel_file() throws Exception {
		
		f= new File(DataFiles.BULK_DATA_FILE_PATH);
	}
	
	
	@When("The user reads the date, it reads properly")
	public void the_user_reads_the_date_it_reads_properly() throws Exception {
		
		FileInputStream fis= new FileInputStream(f);
		
		XSSFWorkbook workBook= new XSSFWorkbook(fis);
		XSSFSheet sheet= workBook.getSheet("Sheet1");
		
		int firstRowNum= sheet.getFirstRowNum();
		int lastRowNum= sheet.getLastRowNum();
		
		
		for(int r=firstRowNum+1; r<=lastRowNum; r++) {
			
			XSSFRow row = sheet.getRow(r);
			int firstCellNum = row.getFirstCellNum();
			int lastCellNum = row.getLastCellNum();
			
			for(int c=firstCellNum; c<lastCellNum; c++) {
				
				XSSFCell cell = row.getCell(c);
				CellType cellType = cell.getCellType();
				
				switch(cellType) {
				
				case STRING: 
					System.out.print(cell.getStringCellValue()+" ");
					break;
				case NUMERIC:
					System.out.print(cell.getNumericCellValue()+" ");
					break;
				case BOOLEAN:
					System.out.print(cell.getBooleanCellValue()+" ");
					break;
				case BLANK:
					System.out.print(cell.getStringCellValue()+" ");
					break;
				case FORMULA:
					System.out.print(cell.getCellFormula()+" ");
					break;
				case ERROR:
					System.out.print(cell.getErrorCellString()+" ");
					break;
				default:
					System.out.println("UNKNOWN CELL TYPE");
					break;
				}
			}
		}
		
		workBook.close();
		
	}
	
	
	@When("The user updates the execution date in the document")
	public void the_user_updates_the_execution_date_in_the_document() throws Exception {
		FileInputStream fis= new FileInputStream(f);
		
		XSSFWorkbook workBook= new XSSFWorkbook(fis);
		XSSFSheet sheet= workBook.getSheet("Sheet1");
		
		int firtRowNum= sheet.getFirstRowNum();
		int lastRowNum= sheet.getLastRowNum();
		
		for(int r=firtRowNum+1; r<=lastRowNum; r++) {
			
			XSSFRow row= sheet.getRow(r);
			
			XSSFCell cell= row.createCell(5);
			
			LocalDateTime currentDate= LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			String formattedDate= currentDate.format(formatter);
			
			cell.setCellValue(formattedDate);

			FileOutputStream fos= new FileOutputStream(new File(DataFiles.BULK_DATA_FILE_PATH));
			workBook.write(fos);
		}
		
		workBook.close();
	}
}
