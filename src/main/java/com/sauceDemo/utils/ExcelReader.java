package com.sauceDemo.utils;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelReader {

	public synchronized List<Map<String, String>> getData(String excelFilePath, String sheetName) throws Exception
	{
		List<Map<String, String>> dataList = new ArrayList<>();
		try(FileInputStream fis = new FileInputStream(excelFilePath))
		{
			Workbook workbook = WorkbookFactory.create(fis);
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
		}

		return dataList;
	}

}
