package com.Amazon;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Driver {
	static WebDriver driver;

	public static void main(String[] args) throws Exception {
		String dt_path = "/Users/rui/Desktop/Selenium/Amazon.xlsx";
		String[][] recData = ReusableMethods.readXlSheet(dt_path, "TestSuite");

		for (int i = 1; i < recData.length; i++) {

			String execute = recData[i][1];

			if (execute.equalsIgnoreCase("Y")) {

				try {

					// System.setProperty("webdriver.gecko.driver",
					// "C:/Users/Abhis_lw0caw1/Google Drive/July 10 2017/July 10
					// 2017 Read Only/Framework/Lib/geckodriver.exe");
					// driver = new FirefoxDriver();
					if (i != 10) {

						System.setProperty("webdriver.gecko.driver", "/Users/rui/Desktop/selenium/geckodriver");
						driver = new FirefoxDriver();
					}

					String testCase = recData[i][2];

					ReusableMethods.startReport(testCase, "/Users/rui/Desktop/selenium/Report/", "Firefox");
					/* Java Reflection */
					Method tc = AutomationScripts.class.getMethod(testCase);
					tc.invoke(tc);
					if (i != 10) {
						driver.close();
					}

					ReusableMethods.bw.close();

				} catch (Exception e) {
					System.out.println(e);
				}

			}

		}

	}

}
