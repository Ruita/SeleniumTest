package com.Amazon;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ReusableMethods extends Driver {
	// test

	static XSSFSheet sh1;
	static Object[][] matrix;
	static Object[][] data;
	static Object[][] setValue;
	static String p_ath, sheetName;
	static XSSFWorkbook wb;
	static FileInputStream fis;

	static BufferedWriter bw = null;
	static BufferedWriter bw1 = null;
	static String htmlname;
	static String objType;
	static String objName;
	static String TestData;
	static String rootPath;
	static int report;

	static Date cur_dt = null;
	static String filenamer;
	static String TestReport;
	int rowcnt;
	static String exeStatus = "True";
	static int iflag = 0;
	static int j = 1;

	static String fireFoxBrowser;
	static String chromeBrowser;

	static String result;

	static int intRowCount = 0;
	static String dataTablePath;
	static int i;
	static String browserName;

	public static void initializeCancel() throws IOException {

		File src = new File("/Users/rui/Desktop/Selenium/Amazon.xlsx");

		fis = new FileInputStream(src);
		wb = new XSSFWorkbook(fis);

		// sh1 = wb.getSheet("Sheet3");
		sh1 = wb.getSheet("OR");

		// sh3 = wb.getSheet("Sheet6");

		// driver.get(sh1.getRow(1).getCell(0).getStringCellValue());
		// driver = new EventFiringWebDriver(driver);

	}

	public static Object[][] initialize() {
		p_ath = "/Users/rui/Desktop/Selenium/Amazon.xlsx";
		sheetName = "OR";
		setValue = readExcel(p_ath, sheetName);
		return setValue;

	}

	public static Object[][] readExcel(String p_ath, String sheet_Name) {

		try {
			File file = new File(p_ath);
			FileInputStream xf = new FileInputStream(file);
			XSSFWorkbook xwb = new XSSFWorkbook(xf);
			ArrayList<String> sNames = new ArrayList<String>();

			// retrieve all the sheet in a file
			for (int i = 0; i < xwb.getNumberOfSheets(); i++) {
				sNames.add(xwb.getSheetName(i));
			}

			// Iterate through each sheet and retrieves the data and stores it
			// in an arraylist

			XSSFSheet sheet = xwb.getSheet(sheet_Name);
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

	/*
	 * Name Of the method: enterText Brief Description: Enter the text value to
	 * the text box Arguments: obj --> Text box object, textVal --> value to be
	 * entered, objName --> name of the object Created by: Automation team
	 * Creation Date: Aug 23 2017 Last Modified: Aug 23 2017
	 */
	public static void enterText(WebElement obj, String textVal, String objName) throws IOException {
		if (obj.isDisplayed()) {
			obj.sendKeys(textVal);
			Update_Report("Pass", "enterText", textVal + " is entered in " + objName + " field", driver);
			System.out.println("Pass: " + textVal + " is entered in " + objName + " field");
		} else {
			System.out.println("Fail: " + objName + " field is not displayed,please check your application");

		}

	}
	/*
	 * Name Of the method: clickButton Brief Description: Click on button
	 * Arguments: obj --> web object, objName --> name of the object Created by:
	 * Automation team Creation Date: Aug 23 2017 Last Modified: Aug 23 2017
	 */

	public static void clickButton(WebElement obj, String objName) {

		if (obj.isDisplayed()) {
			obj.click();
			System.out.println("Pass: " + objName + " is clicked.");
		} else {
			System.out.println("Fail: " + objName + " field is not displayed,please check your application");

		}

	}

	/*
	 * Method Name: readXlSheet Method description:Read content from Xl sheet
	 * Arguments:dt_path --> Path of Xl sheet, sheetName --> Name of the sheet
	 * user is accessing
	 * 
	 * Created by:Automation Team Creation Date: July 26 2017 Last Modified:
	 * July 26 2017
	 */
	public static String[][] readXlSheet(String dt_path, String sheetName) throws IOException {

		/* Step 1: Get the XL Path */
		File xlFile = new File(dt_path);

		/* Step2: Access the Xl File */
		FileInputStream xlDoc = new FileInputStream(xlFile);

		/* Step3: Access the work book */
		XSSFWorkbook wb = new XSSFWorkbook(xlDoc);

		/* Step4: Access the Sheet */
		XSSFSheet sheet = wb.getSheet(sheetName);

		int iRowCount = sheet.getLastRowNum() + 1;
		int iColCount = sheet.getRow(0).getLastCellNum();

		String[][] xlData = new String[iRowCount][iColCount];

		for (int i = 0; i < iRowCount; i++) {
			for (int j = 0; j < iColCount; j++) {
				xlData[i][j] = sheet.getRow(i).getCell(j).getStringCellValue();

			}

		}

		return xlData;

	}

	/*
	 * public static void startReport(String scriptName, String ReportsPath)
	 * throws IOException {
	 * 
	 * String strResultPath = null;
	 * 
	 * String testScriptName = scriptName;
	 * 
	 * cur_dt = new Date(); DateFormat dateFormat = new
	 * SimpleDateFormat("yyyy-MM-dd-HH-mm-ss"); String strTimeStamp =
	 * dateFormat.format(cur_dt);
	 * 
	 * if (ReportsPath == "") {
	 * 
	 * ReportsPath = "C:\\"; }
	 * 
	 * if (ReportsPath.endsWith("\\")) { ReportsPath = ReportsPath + "\\"; }
	 * 
	 * strResultPath = ReportsPath + "Log" + "/" + testScriptName + "/"; File f
	 * = new File(strResultPath); f.mkdirs(); htmlname = strResultPath +
	 * testScriptName + "_" + strTimeStamp + ".html";
	 * 
	 * bw = new BufferedWriter(new FileWriter(htmlname));
	 * 
	 * bw.write(
	 * "<HTML><BODY><TABLE BORDER=0 CELLPADDING=3 CELLSPACING=1 WIDTH=100%>");
	 * bw.write(
	 * "<TABLE BORDER=0 BGCOLOR=BLACK CELLPADDING=3 CELLSPACING=1 WIDTH=100%>");
	 * bw.write(
	 * "<TR><TD BGCOLOR=#66699 WIDTH=27%><FONT FACE=VERDANA COLOR=WHITE SIZE=2><B>Browser Name</B></FONT></TD><TD COLSPAN=6 BGCOLOR=#66699><FONT FACE=VERDANA COLOR=WHITE SIZE=2><B>"
	 * + "FireFox " + "</B></FONT></TD></TR>"); bw.write(
	 * "<HTML><BODY><TABLE BORDER=1 CELLPADDING=3 CELLSPACING=1 WIDTH=100%>");
	 * bw.write(
	 * "<TR COLS=7><TD BGCOLOR=#BDBDBD WIDTH=3%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>SL No</B></FONT></TD>"
	 * +
	 * "<TD BGCOLOR=#BDBDBD WIDTH=10%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>Step Name</B></FONT></TD>"
	 * +
	 * "<TD BGCOLOR=#BDBDBD WIDTH=10%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>Execution Time</B></FONT></TD> "
	 * +
	 * "<TD BGCOLOR=#BDBDBD WIDTH=10%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>Status</B></FONT></TD>"
	 * +
	 * "<TD BGCOLOR=#BDBDBD WIDTH=47%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>Detail Report</B></FONT></TD></TR>"
	 * );
	 * 
	 * }
	 */

	/*
	 * Name of the Method: startReport Brief description : Creates HTML report
	 * template Arguments: scriptname:test script name to run,ReportsPath:HTML
	 * report path to create,browserName:browser the script is running Created
	 * by: Automation team Creation date : July 17 2017 last modified: July 17
	 * 2017
	 */
	public static void startReport(String scriptName, String ReportsPath, String browserName) throws IOException {
		j = 0;
		String strResultPath = null;
		String testScriptName = scriptName;

		cur_dt = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		String strTimeStamp = dateFormat.format(cur_dt);

		if (ReportsPath == "") {

			ReportsPath = "C:\\";
		}

		if (ReportsPath.endsWith("\\")) {
			ReportsPath = ReportsPath + "\\";
		}

		strResultPath = ReportsPath + "Log" + "/" + testScriptName + "/";
		File f = new File(strResultPath);
		f.mkdirs();
		htmlname = strResultPath + testScriptName + "_" + strTimeStamp + ".html";

		bw = new BufferedWriter(new FileWriter(htmlname));

		bw.write("<HTML><BODY><TABLE BORDER=0 CELLPADDING=3 CELLSPACING=1 WIDTH=100%>");
		bw.write("<TABLE BORDER=0 BGCOLOR=BLACK CELLPADDING=3 CELLSPACING=1 WIDTH=100%>");
		bw.write(
				"<TR><TD BGCOLOR=#66699 WIDTH=27%><FONT FACE=VERDANA COLOR=WHITE SIZE=2><B>Browser Name</B></FONT></TD><TD COLSPAN=6 BGCOLOR=#66699><FONT FACE=VERDANA COLOR=WHITE SIZE=2><B>"
						+ browserName + "</B></FONT></TD></TR>");
		bw.write("<HTML><BODY><TABLE BORDER=1 CELLPADDING=3 CELLSPACING=1 WIDTH=100%>");
		bw.write(
				"<TR COLS=7><TD BGCOLOR=#BDBDBD WIDTH=3%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>SL No</B></FONT></TD>"
						+ "<TD BGCOLOR=#BDBDBD WIDTH=10%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>Step Name</B></FONT></TD>"
						+ "<TD BGCOLOR=#BDBDBD WIDTH=10%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>Execution Time</B></FONT></TD> "
						+ "<TD BGCOLOR=#BDBDBD WIDTH=10%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>Status</B></FONT></TD>"
						+ "<TD BGCOLOR=#BDBDBD WIDTH=47%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>Detail Report</B></FONT></TD></TR>");

	}

	/*
	 * public static void Update_Report(String Res_type, String Action, String
	 * result) throws IOException { String str_time; Date exec_time = new
	 * Date(); DateFormat dateFormat = new
	 * SimpleDateFormat("yyyy-MM-dd-HH-mm-ss"); str_time =
	 * dateFormat.format(exec_time); if (Res_type.startsWith("Pass")) {
	 * bw.write(
	 * "<TR COLS=7><TD BGCOLOR=#EEEEEE WIDTH=3%><FONT FACE=VERDANA SIZE=2>" +
	 * (j++) +
	 * "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE=VERDANA SIZE=2>" +
	 * Action +
	 * "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE=VERDANA SIZE=2>" +
	 * str_time +
	 * "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE=VERDANA SIZE=2 COLOR = GREEN>"
	 * + "Passed" +
	 * "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=30%><FONT FACE=VERDANA SIZE=2 COLOR = GREEN>"
	 * + result + "</FONT></TD></TR>");
	 * 
	 * } else if (Res_type.startsWith("Fail")) { exeStatus = "Failed"; report =
	 * 1; bw.write(
	 * "<TR COLS=7><TD BGCOLOR=#EEEEEE WIDTH=3%><FONT FACE=VERDANA SIZE=2>" +
	 * (j++) +
	 * "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE=VERDANA SIZE=2>" +
	 * Action +
	 * "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE=VERDANA SIZE=2>" +
	 * str_time +
	 * "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE=VERDANA SIZE=2 COLOR = RED>"
	 * + "<a href= " + htmlname + "  style=\"color: #FF0000\"> Failed </a>"
	 * 
	 * +
	 * "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=30%><FONT FACE=VERDANA SIZE=2 COLOR = RED>"
	 * + result + "</FONT></TD></TR>");
	 * 
	 * } }
	 */
	public static void Update_Report(String Res_type, String Action, String result, WebDriver dr) throws IOException {
		Date exec_time = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		String str_time = dateFormat.format(exec_time);

		if (Res_type.startsWith("Pass")) {

			bw.write("<TR COLS=7><TD BGCOLOR=#EEEEEE WIDTH=3%><FONT FACE=VERDANA SIZE=2>" + (j++)
					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE=VERDANA SIZE=2>" + Action
					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE=VERDANA SIZE=2>" + str_time
					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE=VERDANA SIZE=2 COLOR = GREEN>" + "Passed"
					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=30%><FONT FACE=VERDANA SIZE=2 COLOR = GREEN>" + result
					+ "</FONT></TD></TR>");

		} else if (Res_type.startsWith("Fail")) {
			// To generate report in only single file

			String ss1Path = screenshot(dr);
			exeStatus = "Failed";
			report = 1;
			bw.write("<TR COLS=7><TD BGCOLOR=#EEEEEE WIDTH=3%><FONT FACE=VERDANA SIZE=2>" + (j++)
					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE=VERDANA SIZE=2>" + Action
					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE=VERDANA SIZE=2>" + str_time
					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE=VERDANA SIZE=2 COLOR = RED>" + "<a href= "
					+ ss1Path

					+ "  style=\"color: #FF0000\"> Failed </a>"

					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=30%><FONT FACE=VERDANA SIZE=2 COLOR = RED>" + result
					+ "</FONT></TD></TR>");

		}
	}

	public static String screenshot(WebDriver dr) throws IOException {

		Date exec_time = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		String str_time = dateFormat.format(exec_time);
		// String fileName =
		// "C:/Users/Sreeram/Desktop/WorkDayScreenShots/ss.png";
		String ss1Path = "C:/Users/Abhis_lw0caw1/Desktop/HTML/" + str_time + ".png";
		File scrFile = ((TakesScreenshot) dr).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(ss1Path));
		return ss1Path;
	}

}
