package com.ydsworld.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ReadExcelController {
	static XSSFRow row;
	@RequestMapping(value="/excel", method=RequestMethod.GET)
	public String readExcel(Model model) throws InvalidFormatException, IOException{
		
		List<String> list = new ArrayList<String>();
		
		 FileInputStream fis = new FileInputStream(
			      new File("C:\\Users\\YDSWorld\\workspace\\ProjectV9\\src\\main\\resources\\Book1.xlsx"));
		 		 
		 		  SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		  		  XSSFWorkbook workbook = new XSSFWorkbook(fis);
			      XSSFSheet spreadsheet = workbook.getSheetAt(0);
			      Iterator < Row > rowIterator = spreadsheet.iterator();
			      while (rowIterator.hasNext()) 
			      {
			         row = (XSSFRow) rowIterator.next();
			         Iterator < Cell > cellIterator = row.cellIterator();
			         
			      
					 
			         while ( cellIterator.hasNext()) 
			         {
			            Cell cell = cellIterator.next();
			            
			            switch (cell.getCellType()) 
			            {
			               case Cell.CELL_TYPE_NUMERIC:
			            	   if (DateUtil.isCellDateFormatted(cell)) {
			            		   try {

			            			    list.add(sdf.format(cell.getDateCellValue()));
			            			    System.out.println(sdf.format(cell.getDateCellValue()));

			            			    } catch (Exception e) {
			            			            e.printStackTrace();
			            			    }
			                    } else {
			                        System.out.println(cell.getNumericCellValue() + " \t\t " );
			                        
			                    }
			                    break;
			               case Cell.CELL_TYPE_STRING:
			            	   list.add(cell.getStringCellValue());
			               System.out.print(cell.getStringCellValue() + " \t\t " );
			               
			            }
			         }
			         System.out.println();
			      }
			      fis.close();
			   		
		model.addAttribute("list", list);
		return "readExcel";
	}
}
