package co.uk.cartaxcheck.tests;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import co.uk.cartaxcheck.base.BaseTest;
import co.uk.cartaxcheck.base.CoreActions;
import co.uk.cartaxcheck.dataobjects.CarDetails;
import co.uk.cartaxcheck.pages.CarDetailsPage;
import co.uk.cartaxcheck.pages.HomePage;
import co.uk.cartaxcheck.utilities.TextFileUtility;

public class FreeCarCheckTests extends BaseTest {
	public HomePage homePage;
	public CarDetailsPage carDetailsPage;
	public CoreActions coreActions;

	@Test(dataProvider = "getCarRegistrtionData")
	public void searchCarAndVerifyDetailsFile1(String regNumber, CarDetails outOutExpectedData) throws InterruptedException {
		extentTest = extent.createTest("searchCarAndVerifyDetailsFile1", "searchCarAndVerifyDetailsFile1");
		homePage = new HomePage(driver, extentTest);
		coreActions = new CoreActions(driver, extentTest);
		homePage.enterVehicleRegistationNumber(regNumber);
		homePage.clickOnGetFreeCheck();
		carDetailsPage = new CarDetailsPage(driver, extentTest);
		coreActions.assertEquals(outOutExpectedData.registrationNumber, carDetailsPage.getRegistration(),
				"Registration number data mismatched");
		coreActions.assertEquals(outOutExpectedData.make, carDetailsPage.getManufacturer(), "Make data mismatched");
		coreActions.assertEquals(outOutExpectedData.model, carDetailsPage.getModel(), "Model data mismatched");
		coreActions.assertEquals(outOutExpectedData.colour, carDetailsPage.getColour(), "Colour data mismatched");
		coreActions.assertEquals(outOutExpectedData.year, carDetailsPage.getYear(), "Year data mismatched");

	}

	@DataProvider
	public Object[][] getCarRegistrtionData(ITestContext context) throws IOException {
		String inputFileData = TextFileUtility
				.getDataFromInputFile(context.getCurrentXmlTest().getParameter("inputFileName"));
		List<String> registrationNumbers = TextFileUtility.getRegistrationNumbers(inputFileData);
		Map<String, CarDetails> carDetails = TextFileUtility
				.getOutPutFileData(context.getCurrentXmlTest().getParameter("outputFileName"));
		
		Object[][] registrationData = new Object[registrationNumbers.size()][2];

		for (int i = 0; i < registrationNumbers.size(); i++) {
			registrationData[i][0] = registrationNumbers.get(i);
			registrationData[i][1] = carDetails.get(registrationNumbers.get(i).replace(" ", ""));
		}
		return registrationData;
	}

}
