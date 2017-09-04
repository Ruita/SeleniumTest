package com.Amazon;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.AssertJUnit;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AutomationScripts extends ReusableMethods {
	// static Object[][] recData;

	public static void searchIphone() throws IOException {
		try { // setValue = readExcel(path, sheetName);
			initialize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			driver.get("http://www.amazon.com/");
			// System.out.println(setValue[1][3]);
			enterText(driver.findElement(By.xpath((String) setValue[1][3])), "iphone 6", (String) setValue[1][1]);
			clickButton(driver.findElement(By.xpath((String) setValue[2][3])), (String) setValue[2][1]);
			clickButton(driver.findElement(By.xpath((String) setValue[3][3])), (String) setValue[3][1]);
			Thread.sleep(3000);
			clickButton(driver.findElement(By.xpath((String) setValue[4][3])), (String) setValue[4][1]);

			Set<String> winIds = driver.getWindowHandles();
			System.out.println("Total windows -> " + winIds.size());
			if (winIds.size() == 2) {
				Iterator<String> iter = winIds.iterator();
				String popupWinID = iter.next();
				driver.switchTo().window(popupWinID);
				driver.close();
				driver.switchTo().defaultContent();

			}
			Thread.sleep(3000);

			boolean result = driver.findElement(By.xpath((String) setValue[5][3])).isDisplayed();
			if (result == true) {
				clickButton(driver.findElement(By.xpath((String) setValue[5][3])), (String) setValue[5][1]);
			}
			String message = driver.findElement(By.xpath((String) setValue[6][3])).getText();
			// System.out.println(message);

			AssertJUnit.assertEquals("Item Not added into the cart", message.contentEquals("1"));

			// setValue[1][1]);

		} catch (Exception e) {

			System.out.println(e.getMessage());

		}
	}

	public static void checkMainTab() throws IOException {
		try { // setValue = readExcel(path, sheetName);
			initialize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			driver.get("http://www.amazon.com/");
			Actions act = new Actions(driver);
			act.moveToElement(driver.findElement(By.xpath((String) setValue[7][3]))).build().perform();
			// act.moveToElement(driver.findElement(By.xpath((String)
			// setValue[8][3]))).build().perform();

			Thread.sleep(4000);
			List<String> list = new ArrayList<String>();
			// act.moveToElement(driver.findElement(By.xpath((String)
			// setValue[8][3]))).build().perform();
			act.moveToElement(driver.findElement(By.xpath("//*[@id='nav-your-amazon']"))).build().perform();
			String yourAmazon = driver.findElement(By.linkText((String) setValue[8][3])).getAttribute("href");
			list.add(0, yourAmazon);

			Thread.sleep(4000);
			String deals = driver.findElement(By.linkText((String) setValue[9][3])).getAttribute("href");
			list.add(1, deals);

			for (int i = 0; i < list.size(); i++) {
				try {
					URL url = new URL(list.get(i));

					HttpURLConnection httpURLConnect = (HttpURLConnection) url.openConnection();

					httpURLConnect.setConnectTimeout(3000);

					httpURLConnect.connect();

					if (httpURLConnect.getResponseCode() == 200) {
						System.out.println(list.get(i) + " - " + httpURLConnect.getResponseMessage());
					}
					if (httpURLConnect.getResponseCode() == HttpURLConnection.HTTP_NOT_FOUND) {
						System.out.println(list.get(i) + " - " + httpURLConnect.getResponseMessage() + " - "
								+ HttpURLConnection.HTTP_NOT_FOUND);
					}
				} catch (Exception e) {

				}
			}

		} catch (Exception e) {

			System.out.println(e.getMessage());

		}
	}

	public static void validateDepartment() throws IOException {
		try { // setValue = readExcel(path, sheetName);
			initialize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			driver.get("http://www.amazon.com/");
			Actions act = new Actions(driver);
			act.moveToElement(driver.findElement(By.xpath((String) setValue[7][3]))).build().perform();
			// act.moveToElement(driver.findElement(By.xpath((String)
			// setValue[8][3]))).build().perform();

			Thread.sleep(4000);
			int count = 0;
			String[] exp = { "Amazon Video", "Amazon Music", "Appstore for Android", "Kindle E-readers & Books",
					"Fire Tablets", "Fire TV", "Echo & Alexa", "AmazonFresh NEW", "Books & Audible",
					"Movies, Music & Games", "Electronics, Computers & Office", "Home, Garden & Tools",
					"Restaurants, Food & Grocery", "Beauty, Health & Household", "Toys, Kids & Baby",
					"Clothing, Shoes & Jewelry", "Handmade", "Sports & Outdoors", "Automotive & Industrial",
					"Home Services", "Credit & Payment Products", "Full Store Directory" };
			System.out.println(exp.length);
			String pathOne = "//*[@id='nav-flyout-shopAll']/div[2]/span[";
			List<String> list = new ArrayList<>();

			for (int i = 1; i <= exp.length; i++) {
				if (i != 22) {
					Thread.sleep(1000);
					String element = driver.findElement(By.xpath(pathOne + i + "]/span")).getText();
					System.out.println(i + "------" + element);
					if (element.equals(exp[i - 1])) {
						count++;
					}
				} else if (i == 22) {
					String lastElement = driver.findElement(By.xpath("//*[@id='nav-flyout-shopAll']/div[2]/a/span"))
							.getText();
					System.out.println(i + "------" + lastElement);
					if (lastElement.equals(exp[i - 1]))
						count++;
				}
			}

		} catch (Exception e) {

			System.out.println(e.getMessage());

		}
	}

	public static void validateAccountDropDown() throws IOException {
		try { // setValue = readExcel(path, sheetName);
			initialize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			driver.get("http://www.amazon.com/");
			Actions act = new Actions(driver);
			act.moveToElement(driver.findElement(By.xpath((String) setValue[12][3]))).build().perform();
			Thread.sleep(2000);
			String newCustomer = driver.findElement(By.xpath((String) setValue[14][3])).getText();
			List<String> yourOrders = new ArrayList<String>();
			yourOrders.add(newCustomer);
			System.out.println(newCustomer);
			String pathOne = (String) setValue[15][3];
			for (int i = 1; i <= 20; i++) {
				String element = driver.findElement(By.xpath(pathOne + i + (String) setValue[16][3])).getText();
				yourOrders.add(element);
				System.out.println(element);
			}
			String listPathOne = (String) setValue[17][3];
			for (int i = 1; i <= 9; i++) {
				String element = driver.findElement(By.xpath(listPathOne + i + (String) setValue[18][3])).getText();
				yourOrders.add(element);
				System.out.println(element);
			}

		} catch (Exception e) {

			System.out.println(e.getMessage());

		}
	}

	public static void validateAllDropDown() throws IOException {
		try { // setValue = readExcel(path, sheetName);
			initialize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			driver.get("http://www.amazon.com/");
			Thread.sleep(3000);

			WebElement customApp = driver.findElement(By.xpath((String) setValue[19][3]));
			Select select = new Select(customApp);
			List<WebElement> options = driver.findElements(By.tagName("option"));
			for (int i = 0; i < options.size(); i++) {
				System.out.println(options.get(i).getText());
			}

			Thread.sleep(3000);
		} catch (Exception e) {

			System.out.println(e.getMessage());

		}
	}

	public static void emptyCartOne() throws IOException {
		try { // setValue = readExcel(path, sheetName);
			initialize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			driver.get("http://www.amazon.com/");
			// System.out.println(setValue[1][3]);
			/* locate search textbox */
			enterText(driver.findElement(By.xpath((String) setValue[1][3])), "iphone 6s", (String) setValue[1][1]);
			/* locate search button */
			clickButton(driver.findElement(By.xpath((String) setValue[2][3])), (String) setValue[2][1]);
			/* locate iphone 6s */
			clickButton(driver.findElement(By.xpath((String) setValue[20][3])), (String) setValue[20][1]);
			/* locate add to cart */
			clickButton(driver.findElement(By.xpath((String) setValue[21][3])), (String) setValue[21][1]);
			Thread.sleep(3000);
			/* locate cart */
			clickButton(driver.findElement(By.xpath((String) setValue[22][3])), (String) setValue[21][1]);
			/* locate delete */
			clickButton(driver.findElement(By.xpath((String) setValue[23][3])), (String) setValue[21][1]);
			/* locate cart */
			clickButton(driver.findElement(By.xpath((String) setValue[22][3])), (String) setValue[21][1]);
			String message = driver.findElement(By.xpath((String) setValue[24][3])).getText();
			AssertJUnit.assertEquals("Your Shopping Cart is empty.", message);
			Thread.sleep(3000);

		} catch (Exception e) {

			System.out.println(e.getMessage());

		}
	}

	public static void helpMenu() throws IOException {
		try { // setValue = readExcel(path, sheetName);
			initialize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			driver.get("http://www.amazon.com/");
			// System.out.println(setValue[1][3]);
			/* locate search textbox */
			// enterText(driver.findElement(By.xpath((String) setValue[1][3])),
			// "iphone 6s", (String) setValue[1][1]);
			/* locate search button */
			clickButton(driver.findElement(By.linkText("Help")), (String) setValue[25][1]);
			String message = driver.findElement(By.tagName("h1")).getText();
			Assert.assertEquals("Hello. What can we help you with?", message);
			List<WebElement> list = driver.findElements(By.xpath((String) setValue[27][3]));

			for (int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i).getText());
			}
			String findSolutions = driver.findElement(By.xpath((String) setValue[28][3])).getText();
			Assert.assertTrue("Text not found!", findSolutions.contains("Find more solutions"));
			boolean textBox = driver.findElement(By.xpath((String) setValue[29][3])).isDisplayed();
			boolean solutionSearch = driver.findElement(By.xpath((String) setValue[30][3])).isDisplayed();
			if (textBox == false && solutionSearch == false) {
				System.out.println("Value not displayed, check your application");
			}

		} catch (Exception e) {

			System.out.println(e.getMessage());

		}
	}

	@BeforeTest
	public static void addQuantity() throws IOException {
		try { // setValue = readExcel(path, sheetName);
			initialize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			driver.get("http://www.amazon.com/");
			// System.out.println(setValue[1][3]);
			/* locate search textbox */
			WebElement customApp = driver.findElement(By.xpath((String) setValue[19][3]));
			Select select = new Select(customApp);
			select.selectByVisibleText("Books");
			enterText(driver.findElement(By.xpath((String) setValue[1][3])), "Head First Java",
					(String) setValue[1][1]);
			/* locate search button */
			clickButton(driver.findElement(By.xpath((String) setValue[2][3])), (String) setValue[2][1]);
			/* locate head first java 2nd addition */
			clickButton(driver.findElement(By.xpath((String) setValue[31][3])), (String) setValue[31][1]);
			clickButton(driver.findElement(By.xpath((String) setValue[32][3])), (String) setValue[32][1]);
			Thread.sleep(3000);
			clickButton(driver.findElement(By.xpath((String) setValue[33][3])), (String) setValue[33][1]);
			clickButton(driver.findElement(By.xpath((String) setValue[4][3])), (String) setValue[4][1]);
			Thread.sleep(3000);
			String cartQuantity = driver.findElement(By.xpath((String) setValue[6][3])).getText();
			Assert.assertTrue("Items not addded to the cart", cartQuantity.contentEquals("5"));

		} catch (Exception e) {

			System.out.println(e.getMessage());

		}
	}

	@Test
	public static void editQuantity() throws IOException {
		try {

			clickButton(driver.findElement(By.xpath((String) setValue[22][3])), (String) setValue[22][1]);
			clickButton(driver.findElement(By.xpath((String) setValue[34][3])), (String) setValue[34][1]);
			/* locate change quantity */
			clickButton(driver.findElement(By.xpath((String) setValue[35][3])), (String) setValue[35][1]);
			Thread.sleep(3000);
			/* locate save for later */
			clickButton(driver.findElement(By.xpath((String) setValue[36][3])), (String) setValue[36][1]);
			/* save for later message */
			Actions action = new Actions(driver);
			WebElement we = driver.findElement(By.xpath((String) setValue[38][3]));
			action.moveToElement(we).build().perform();
			String saveForLaterMessage = we.getText();
			System.out.println(saveForLaterMessage);
			Assert.assertTrue("Text not Displayed!",
					saveForLaterMessage.contains(" Head First Java, 2nd Edition has been moved to Save for Later."));

		} catch (Exception e) {

			System.out.println(e.getMessage());

		}
		driver.close();
	}

	public static void checkDisplay() throws IOException {
		try { // setValue = readExcel(path, sheetName);
			initialize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			driver.get("http://www.amazon.com/");
			// System.out.println(setValue[1][3]);
			/* locate search textbox */
			enterText(driver.findElement(By.xpath((String) setValue[1][3])), "Iphone", (String) setValue[1][1]);
			String firstdropDown = driver.findElement(By.xpath((String) setValue[39][3])).getText();
			String firstdropDownSubPath = driver.findElement(By.xpath((String) setValue[40][3])).getText();
			String checkFirstDropDown = firstdropDown + " " + firstdropDownSubPath;
			System.out.println(firstdropDown + " " + firstdropDownSubPath);
			Assert.assertTrue("Not Displayed!", checkFirstDropDown.contains("iphone charger in Electronics"));
			String secondropDownSubPath = driver.findElement(By.xpath((String) setValue[41][3])).getText();
			String checksecondDropDown = firstdropDown + " " + secondropDownSubPath;
			System.out.println(firstdropDown + " " + secondropDownSubPath);
			Assert.assertTrue("Not Displayed!",
					checksecondDropDown.contains("iphone charger in Cell Phones & Accessories"));
			String thirddropDownSubPath = driver.findElement(By.xpath((String) setValue[43][3])).getText();
			Assert.assertTrue("Not Displayed!", thirddropDownSubPath.contains("iphone 7 plus case"));

		} catch (Exception e) {

			System.out.println(e.getMessage());

		}
	}

}
