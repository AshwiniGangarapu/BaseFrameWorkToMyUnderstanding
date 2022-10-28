package com.utils;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	public XSSFWorkbook getWorkbook(String filePath) throws IOException {
		CommonUtils cu = new CommonUtils();
		FileInputStream fis = cu.readFile(filePath);
		return new XSSFWorkbook(fis);
	}

	public XSSFSheet getSheet(XSSFWorkbook workbook, String sheetName) throws IOException {
		return workbook.getSheet(sheetName);
	}
    //method overriding
	public XSSFSheet getSheet(String filePath, int sheetNumber) throws IOException {
		return getWorkbook(filePath).getSheetAt(sheetNumber);
	}

	public XSSFCell getCellValue(XSSFSheet sheet, int rowNo, int CellNo) {
		return sheet.getRow(rowNo).getCell(CellNo);
	}

	public int getRowByName(XSSFSheet sheet, String cellName) {
		int cellCount = sheet.getRow(0).getLastCellNum();
		int cellNumber = 0;
		for (int i = 0; i < cellCount; i++) {
			String name = sheet.getRow(0).getCell(i).getStringCellValue();
			if (name.equalsIgnoreCase(cellName)) {
				cellNumber = i;
				break;
			}
		}
		return cellNumber;
	}

	XSSFCell getDataByColumnName(XSSFSheet sheet, String cellName, int rowNumber) {
		int cellno = getRowByName(sheet, cellName);
		return sheet.getRow(rowNumber).getCell(cellno);
	}

	public String getDataAsString(XSSFSheet sheet, String cellName, int rowNumber) {
		return getDataByColumnName(sheet, cellName, rowNumber).getStringCellValue();
	}

	public double getDataAsNumeric(XSSFSheet sheet, String cellName, int rowNumber) {
		return getDataByColumnName(sheet, cellName, rowNumber).getNumericCellValue();
	}

	XSSFCell getDataByColumnIndex(XSSFSheet sheet, int cellNumber, int rowNumber) {
		return sheet.getRow(rowNumber).getCell(cellNumber);
	}

	public String getDataAsString(XSSFSheet sheet, int cellNumber, int rowNumber) {
		return getDataByColumnIndex(sheet, cellNumber, rowNumber).getStringCellValue();
	}

	public double getDataNumeric(XSSFSheet sheet, int cellNumber, int rowNumber) {
		return getDataByColumnIndex(sheet, cellNumber, rowNumber).getNumericCellValue();
	}
}
