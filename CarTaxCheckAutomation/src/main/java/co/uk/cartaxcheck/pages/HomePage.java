package co.uk.cartaxcheck.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

import co.uk.cartaxcheck.base.CoreActions;

public class HomePage {
	public WebDriver driver;
	public CoreActions coreActions;

	@FindBy(xpath = ".//input[@id='vrm-input']")
	WebElement txtboxVehicleSearch;

	@FindBy(xpath = ".//button[text()='Free Car Check']")
	WebElement btnGetFreeCheck;

	public HomePage(WebDriver driver, ExtentTest extentTest) {
		this.driver = driver;
		coreActions = new CoreActions(driver, extentTest);
		PageFactory.initElements(driver, this);
	}

	public void enterVehicleRegistationNumber(String registrationNumber) {
		coreActions.click(txtboxVehicleSearch, "txtboxVehicleSearch");
		coreActions.type(txtboxVehicleSearch, registrationNumber, "txtboxVehicleSearch");
	}

	public void clickOnGetFreeCheck() {
		coreActions.click(btnGetFreeCheck, "btnGetFreeCheck");
	}

}
