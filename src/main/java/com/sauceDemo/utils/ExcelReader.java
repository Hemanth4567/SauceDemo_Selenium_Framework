package com.sauceDemo.utils;
import org.apache.poi.ss.usermodel.*;
import java.io.File;
import java.util.*;

public class ExcelReader {
	
	public List<Map<String, String>> getData(String excelFilePath, String sheetName) throws Exception
	{
		List<Map<String, String >> dataList = new ArrayList<>();
		Workbook workbook = WorkbookFactory.create(new File (excelFilePath));
		Sheet sheet = workbook.getSheet(sheetName);
		
		if(sheet == null)
		{
			throw new RuntimeException("Sheet with name "+sheetName+"not found");
		}
		Row headerRow = sheet.getRow(0);
		
		for(int i = 1; i <= sheet.getLastRowNum(); i++)
		{
			Row currentRow = sheet.getRow(i);
			Map<String, String> dataMap = new HashMap<>();
			for(int j = 0; j < currentRow.getLastCellNum(); j++)
			{
				String columnName = headerRow.getCell(j).getStringCellValue();
				String cellValue = currentRow.getCell(j).getStringCellValue();
				
				Cell cell = currentRow.getCell(j);
				String cell_value = "";
				if(cell != null)
				{
					DataFormatter formatter = new DataFormatter();
					cell_value = formatter.formatCellValue(cell);
				}
				
				dataMap.put(columnName, cellValue);
			}
			
			dataList.add(dataMap);
		}
		workbook.close();
		return dataList;	
	}

}
