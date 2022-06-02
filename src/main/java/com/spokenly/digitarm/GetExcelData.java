package com.spokenly.digitarm;

import java.io.FileInputStream;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class GetExcelData {

	public static Object[][] getExcelData(String filePath, String sheetName) {
		Object[][] data = null;
		Workbook workbook = null;
		DataFormatter formatter = new DataFormatter();
		try {
			String fileExtensionName = FilenameUtils.getExtension(filePath);
			FileInputStream fileInput = new FileInputStream(filePath);
			if (fileExtensionName.equals("xlsx")) {
				workbook = new XSSFWorkbook(fileInput);
			} else if (fileExtensionName.equals("xls")) {
				workbook = new HSSFWorkbook(fileInput);
			}
			Sheet sheet = workbook.getSheet(sheetName);
			Row row = sheet.getRow(0);
			int rowNum = sheet.getPhysicalNumberOfRows();
			int colNum = row.getLastCellNum();
			Cell cell;
			data = new Object[rowNum][colNum];
			for (int i = 0; i < rowNum; i++) {
				for (int j = 0; j < colNum; j++) {
					row = sheet.getRow(i);
					cell = row.getCell(j);

					try {
						data[i][j] = formatter.formatCellValue(cell);
					} catch (Exception e) {
						data[i][j] = "";
					}

				}
			}
		}

		catch (Exception e) {
			System.out.println("The exception is: " + e.getMessage());
		}

		return data;
	}
}
