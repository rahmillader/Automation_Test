package Utilities;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

public class Abstract_Class_parallel {


    public static WebDriver driver = null;
    public static ExtentReports report = null;
    public static ExtentTest logger = null;
    public static JavascriptExecutor jse = null;


    @BeforeSuite
    public void openBrowser() {

        //define the report path
        //report = new ExtentReports("src\\main\\java\\Report_Folder\\ExtentReport" + UUID.randomUUID() + ".html", true);
        report = new ExtentReports("src\\main\\java\\Report_Folder\\ExtentReport.html", true);
        //define the JavaScript
        jse = (JavascriptExecutor) driver;
        //logger.log(LogStatus.INFO,"Automation test suite started....");

    }

    @Parameters("browser")
    @BeforeMethod
    public void loggerSession(String browser, Method methodName) {
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
        System.setProperty("webdriver.gecko.driver","src\\main\\resources\\geckodriver.exe");
        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("start-maximized", "incognito");
            driver = new ChromeDriver(options);

        } else if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
            driver.navigate().to("https://www.google.com");
            driver.manage().window().maximize();
        }

        //implicit wait
        driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
        jse = (JavascriptExecutor) driver;
        logger = report.startTest(browser.toUpperCase() +"-" + methodName.getName());
        logger.log(LogStatus.INFO, "Automation test scenario started....");

    }

    @AfterMethod
    public void endLogger() {
        report.endTest(logger);
        logger.log(LogStatus.INFO, "Automation test scenario ended....");

    }


    @AfterSuite()
    public void closeBrowser() {
        report.flush();
        report.close();
        //driver.quit();
        //logger.log(LogStatus.INFO,"Automation Test suite Ended....");

    }


}
