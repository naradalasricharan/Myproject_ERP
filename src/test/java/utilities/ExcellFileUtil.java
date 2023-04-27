package utilities;


import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcellFileUtil 
{
	Workbook wb;
	//constructor for reading excel path

	public ExcellFileUtil(String ExcelPath)throws Throwable

	{

		FileInputStream fi = new FileInputStream(ExcelPath);

		wb = WorkbookFactory.create(fi);

	}

	//counting no of rows in sheet

	public int rowCount(String SheetName)

	{

		return wb.getSheet(SheetName).getLastRowNum();

	}

	//read cell data

	public String getCellData(String SheetName,int row,int column)

	{

		String data="";

		if(wb.getSheet(SheetName).getRow(row).getCell(column).getCellType()==Cell.CELL_TYPE_NUMERIC)

		{

			//read integer type cell data

			int celldata =(int)wb.getSheet(SheetName).getRow(row).getCell(column).getNumericCellValue();

			data =String.valueOf(celldata);

		}

		else

		{

			data =wb.getSheet(SheetName).getRow(row).getCell(column).getStringCellValue();

		}

		return data;

	}

	//method for set cell data

	public void setCelldata(String sheetName,int row,int column,String status,String WriteExcel)throws Throwable

	{

		//get sheet from wb

		Sheet ws =wb.getSheet(sheetName);

		//get row from sheet

		Row rowNum =ws.getRow(row);

		//create cell in a row

		Cell cell = rowNum.createCell(column);

		//write status

		cell.setCellValue(status);

		if(status.equalsIgnoreCase("pass"))

		{

			CellStyle style = wb.createCellStyle();

			Font font=wb.createFont();

			//colour text green

			font.setColor(IndexedColors.GREEN.getIndex());

			font.setBold(true);

			style.setFont(font);

			font.setBoldweight(Font.BOLDWEIGHT_BOLD);

			rowNum.getCell(column).setCellStyle(style);

		}

		else if(status.equalsIgnoreCase("Fail"))

		{

			CellStyle style = wb.createCellStyle();

			Font font = wb.createFont();

			//colour text green

			font.setColor(IndexedColors.RED.getIndex());

			font.setBold(true);

			style.setFont(font);

			font.setBoldweight(Font.BOLDWEIGHT_BOLD);

			rowNum.getCell(column).setCellStyle(style);

		}

		else if(status.equalsIgnoreCase("Blocked"))

		{

			CellStyle style = wb.createCellStyle();

			Font font = wb.createFont();

			//colour text green

			font.setColor(IndexedColors.BLUE.getIndex());

			font.setBold(true);

			style.setFont(font);

			font.setBoldweight(Font.BOLDWEIGHT_BOLD);

			rowNum.getCell(column).setCellStyle(style);

		}

		FileOutputStream fo = new FileOutputStream(WriteExcel);

		wb.write(fo);

	}

	public static void main(String[] args) throws Throwable{

		ExcellFileUtil xl=new ExcellFileUtil("C://Users//CHARAN//Desktop//Testing//companydatademo.xlsx");


		int rc =xl.rowCount("EMPdata");

		System.out.println(rc);

		for(int i=1;i<=rc;i++)

		{

			String fname =xl.getCellData("EMPdata", i, 0);

			String mname =xl.getCellData("EMPdata", i, 1);

			String lname = xl.getCellData("EMPdata", i, 2);

			String eid =xl.getCellData("EMPdata", i, 3);

			System.out.println(fname+"   "+mname+"   "+lname+"   "+eid);

			//xl.setCelldata("EMPdata", i, 4, "Pass", "C://Users//CHARAN//Desktop//Testing//Results.xlsx");

			//xl.setCelldata("EMPdata", i, 4, "Fail", "C://Users//CHARAN//Desktop//Testing//Results.xlsx");

			xl.setCelldata("EMPdata", i, 4, "Blocked", "C://Users//CHARAN//Desktop//Testing//Results.xlsx");

		}

	}

}
