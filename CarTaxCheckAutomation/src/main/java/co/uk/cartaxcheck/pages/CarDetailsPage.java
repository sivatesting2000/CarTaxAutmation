package co.uk.cartaxcheck.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

import co.uk.cartaxcheck.base.CoreActions;

public class CarDetailsPage {
	public WebDriver driver;
	public CoreActions coreActions;
	
	@FindBy(xpath = ".//dt[text()='Registration']/following-sibling::dd")
	WebElement labelRegistration;

	@FindBy(xpath = ".//dt[text()='Make']/following-sibling::dd")
	WebElement labelManufacturer;

	@FindBy(xpath = ".//dt[text()='Model']/following-sibling::dd")
	WebElement labelModel;

	@FindBy(xpath = ".//dt[text()='Colour']/following-sibling::dd")
	WebElement labelColour;

	@FindBy(xpath = ".//dt[text()='Year']/following-sibling::dd")
	WebElement labelYear;

	@FindBy(xpath = "(.//a[text()='Get a Quote'])[1]")
	WebElement waitElement;

	public CarDetailsPage(WebDriver driver, ExtentTest extentTest) {
		this.driver = driver;
		coreActions = new CoreActions(driver, extentTest);
		PageFactory.initElements(driver, this);
		coreActions.waitForVisibilityOfElement(waitElement, "waitElement");
	}

	public String getRegistration() throws InterruptedException {
		Thread.sleep(2000);
		return coreActions.getText(labelRegistration, "labelRegistration").toUpperCase();
	}

	public String getManufacturer() {
		return coreActions.getText(labelManufacturer, "labelManufacturer").toUpperCase();
	}

	public String getModel() {
		return coreActions.getText(labelModel, "labelModel").toUpperCase();
	}

	public String getColour() {
		return coreActions.getText(labelColour, "labelColour").toUpperCase();
	}

	public String getYear() {
		return coreActions.getText(labelYear, "labelYear").toUpperCase();
	}

}
