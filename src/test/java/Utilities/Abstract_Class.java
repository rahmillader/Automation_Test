package Utilities;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

public class Abstract_Class {


    public static WebDriver driver = null;
    public static ExtentReports report = null;
    public static ExtentTest logger = null;
    public static JavascriptExecutor jse = null;

    @BeforeSuite
    public void openBrowser(){
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized", "--incognito");
        driver = new ChromeDriver(options);

        //implicit wait
        driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
        //define the report path
        //report = new ExtentReports("src\\main\\java\\Report_Folder\\ExtentReport" + UUID.randomUUID() + ".html", true);
        report = new ExtentReports("src\\main\\java\\Report_Folder\\ExtentReport.html", true);
        //define the JavaScript
        jse=(JavascriptExecutor)driver;
        //logger.log(LogStatus.INFO,"Automation test suite started....");

    }

    @BeforeMethod
    public void loggerSession(Method methodName){
        logger = report.startTest(methodName.getName());
        logger.log(LogStatus.INFO,"Automation test scenario started....");

    }

    @AfterMethod
    public void endLogger(){
        report.endTest(logger);
        logger.log(LogStatus.INFO,"Automation test scenario ended....");

    }


    @AfterSuite()
    public void closeBrowser(){
        report.flush();
        report.close();
        //driver.quit();
        //logger.log(LogStatus.INFO,"Automation Test suite Ended....");

    }


}
