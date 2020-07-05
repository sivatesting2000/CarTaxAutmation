package co.uk.cartaxcheck.base;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class BaseTest {

	public static WebDriver driver;
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;;
	public static ExtentTest extentTest;

	@BeforeSuite
	@Parameters("browser")
	public static void initiateReporting(String browser) {
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "\\Reports\\CartaxcheckTestReport_"+ new SimpleDateFormat("yyyyMMdd_HH_mm_ss").format(Calendar.getInstance().getTime())+".html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("OS", "WINDOWS");
		extent.setSystemInfo("Browser", browser.toUpperCase());
		htmlReporter.config().setChartVisibilityOnOpen(true);
		htmlReporter.config().setDocumentTitle("Cartaxcheck");
		htmlReporter.config().setReportName("CartaxcheckTestReport");
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter.config().setTheme(Theme.STANDARD);
		htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
	}

	@BeforeMethod
	@Parameters({"browser", "URL"})
	public void setUp(String browser, String URL) {
		driver = initiateBrowser(browser.toUpperCase().trim());
		driver.get(URL);
		driver.manage().timeouts().implicitlyWait(WaitTime.MINIMUM_WAIT, TimeUnit.SECONDS);

	}

	@AfterMethod
	public void logResult(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			extentTest.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " FAILED ", ExtentColor.RED)).addScreenCaptureFromPath(capture(driver,result.getMethod().getMethodName()));
			extentTest.fail(result.getThrowable());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			extentTest.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " PASSED ", ExtentColor.GREEN)).addScreenCaptureFromPath(capture(driver,result.getMethod().getMethodName()));
		} else {
			extentTest.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " SKIPPED ", ExtentColor.ORANGE));
			extentTest.skip(result.getThrowable());
		}
		driver.quit();
	}

	/**
	 * Invokes the browser
	 * 
	 * @param browser
	 */
	public WebDriver initiateBrowser(String browser) {
		String driversPath = System.getProperty("user.dir") + "\\drivers\\";
		switch (browser) {
		case "CHROME":
			System.setProperty("webdriver.chrome.driver", driversPath + "chromedriver.exe");
			driver = new ChromeDriver();
			break;
		}
		driver.manage().window().maximize();
		return driver;
	}

	public static String capture(WebDriver driver,String fileName) throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File Dest = new File(System.getProperty("user.dir")+"\\screenShots\\"+fileName+"_"+ new SimpleDateFormat("yyyyMMdd_HH_mm_ss").format(Calendar.getInstance().getTime()) + ".png");
		String errflpath = Dest.getAbsolutePath();
		FileUtils.copyFile(scrFile, Dest);
		return errflpath;
	}

	@AfterSuite
	public static void generateReport() {
		extent.flush();
	}

}
