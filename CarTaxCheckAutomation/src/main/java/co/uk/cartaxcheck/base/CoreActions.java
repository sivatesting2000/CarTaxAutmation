package co.uk.cartaxcheck.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class CoreActions {
	public WebDriver driver;
	public ExtentTest extentTest;

	public CoreActions(WebDriver driver, ExtentTest extentTest) {
		this.driver = driver;
		this.extentTest = extentTest;
	}

	/**
	 * Enter data into a field
	 * 
	 * @param element
	 * @param data
	 * @param elementName
	 */
	public void type(WebElement element, String data, String elementName) {
		try {
			waitForVisibilityOfElement(element, "elementName");
			element.clear();
			element.sendKeys(data);
			extentTest.log(Status.PASS, "Successfully entered data in: " + elementName);
		} catch (Exception e) {
			extentTest.log(Status.FAIL, "Failedd to enter data in: " + elementName);
			extentTest.fail(e.getMessage());
		}
	}

	/**
	 * Click on element
	 * 
	 * @param element
	 * @param elementName
	 */
	public void click(WebElement element, String elementName) {
		try {
			waitForVisibilityOfElement(element, elementName);
			element.click();
			extentTest.log(Status.PASS, "Successfully clicked on: " + elementName);
		} catch (Exception e) {
			extentTest.log(Status.FAIL, "Failed to click on: " + elementName);
			extentTest.fail(e.getMessage());
		}
	}

	/**
	 * Wait for visibility of element
	 * 
	 * @param element
	 * @param elementName
	 */
	public void waitForVisibilityOfElement(WebElement element, String elementName) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, WaitTime.MAX_WAIT);
			wait.until(ExpectedConditions.visibilityOf(element));
		} catch (Exception e) {
			extentTest.log(Status.FAIL, "Failed to wait for the element: " + elementName);
			extentTest.fail(e.getMessage());
		}
	}

	/**
	 * Verifies the messages
	 * 
	 * @param expected
	 * @param actual
	 * @param message
	 */
	public void assertEquals(String expected, String actual, String message) {
		try {
			Assert.assertEquals(expected, actual, message);
			extentTest.log(Status.PASS, "Successfully verified the text :" + expected);

		} catch (Exception e) {
			extentTest.log(Status.FAIL, "Failed to verify the text :" + expected);
			extentTest.fail(e.getMessage());
		}
	}

	/**
	 * Verify if text present
	 * 
	 * @param expected
	 * @param actual
	 * @param message
	 */
	public void assertContains(String expected, String actual, String message) {
		try {
			Assert.assertEquals(true, actual.contains(expected), message);
			extentTest.log(Status.PASS, "Successfully verified the text :" + expected);

		} catch (Exception e) {
			extentTest.log(Status.FAIL, "Failed to verify the text :" + expected);
			extentTest.fail(e.getMessage());
		}
	}

	/**
	 * Retrives the text of the element
	 * @param element
	 * @param elementName
	 * @return
	 */
	public String getText(WebElement element, String elementName) {
		String text = "";
		try {
			waitForVisibilityOfElement(element, elementName);
			text = element.getText();
			extentTest.log(Status.PASS, "Successfully retrived the text of: " + elementName + " is: " + text);
		} catch (Exception e) {
			extentTest.log(Status.FAIL, "Failed retrive the text of: " + elementName);
			extentTest.fail(e.getMessage());
		}
		return text;
	}
}
