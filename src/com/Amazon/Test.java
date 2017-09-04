package com.Amazon;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;

public class Test {
	static Object[][] matrix;
	static Object[][] data;
	static Object[][] recData;

	public static void main(String[] args) throws Exception {
		String locatorName = "firstName";
		// System.out.println(recData[1][3]);
		// getbjectLocator(locatorName);
		System.out.println(getbjectLocator(locatorName));
		// System.out.println(x);
		// System.out.println(readExcel[1][2]);

	}

	public static By getbjectLocator(String locatorName) {
		initialize();

		By locator = null;
		String locatorType = "Xpath";
		String locatorValue = (String) recData[1][3];
		System.out.println(locatorValue);
		switch (locatorType) {
		case "Id":
			locator = By.id(locatorValue);
			break;
		case "Name":
			locator = By.name(locatorValue);
			break;
		case "CssSelector":
			locator = By.cssSelector(locatorValue);
			break;
		case "LinkText":
			locator = By.linkText(locatorValue);
			break;
		case "PartialLinkText":
			locator = By.partialLinkText(locatorValue);
			break;
		case "TagName":
			locator = By.tagName(locatorValue);
			break;
		case "Xpath":
			locator = By.xpath(locatorValue);
			break;
		}
		return locator;
	}

	public static void initialize() {
		String path = "/Users/rui/Desktop/Selenium/Amazon.xlsx";
		String sheetName = "OR";
		recData = readExcel(path, sheetName);

	}

	public static Object[][] readExcel(String path, String sheetName) {

		try {
			File file = new File(path);
			FileInputStream xf = new FileInputStream(file);
			XSSFWorkbook xwb = new XSSFWorkbook(xf);
			ArrayList<String> sNames = new ArrayList<String>();

			// retrieve all the sheet in a file
			for (int i = 0; i < xwb.getNumberOfSheets(); i++) {
				sNames.add(xwb.getSheetName(i));
			}

			// Iterate through each sheet and retrieves the data and stores it
			// in an arraylist

			XSSFSheet sheet = xwb.getSheet(sheetName);
			int row = sheet.getLastRowNum() + 1;
			int col = sheet.getRow(0).getLastCellNum();
			// System.out.println(row + "---" + col);
			matrix = new Object[row][col];

			for (int i = 0; i < row; i++) {
				for (int j = 0; j < col; j++) {
					XSSFCell cell = sheet.getRow(i).getCell(j);
					if (cell != null) {
						String value = cell.getStringCellValue();
						matrix[i][j] = value;
						// System.out.println(matrix[i][j]);

					}
				}
			}
			// print(data,row,col);

			// xwb.close();
		} catch (Exception e) {

		}
		return matrix;
	}
}
