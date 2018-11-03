package Mlcalc;

import ReusableObjects.Reusable_Method;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ActionItem_captureMortgage_payment {
    //declare webdriver here since it's a global variable
    WebDriver driver;
    //declare all readable & writable excel workbook and worksheet here since its global variable
    Workbook readable;
    Sheet readableSheet;
    WritableWorkbook writable;
    WritableSheet writableSheet;
    int rows;
    SoftAssert softAssert = new SoftAssert();


    @BeforeSuite
    public void openBrowser() throws IOException, BiffException {
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized", "--incognito");
        driver = new ChromeDriver(options);


        //implicit wait
        driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);

        //defining excel readable path


        //defining readable sheet

        //defining writable excel path
        readable = Workbook.getWorkbook(new File("src\\main\\resources\\jxl_mlcalc.xls"));
        readableSheet = readable.getSheet(0);
        //defining the writable excel sheet
        writable = Workbook.createWorkbook(new File("src\\main\\resources\\jxl_result.xls"),readable);
        writableSheet = writable.getSheet(0);
        //define the row count
        rows = readableSheet.getRows();

    }//end of beforesuite

    @AfterSuite
    public void closeBrowser() throws IOException, WriteException {
        writable.write();
        writable.close();
        readable.close();
        //driver.quit();


    }//end of aftersuite

    @Test
    public void testScenario() throws WriteException {

        for (int i = 1; i < rows; i++) {
            String purchasePrice = readableSheet.getCell(0, i).getContents();
            String downPayment = readableSheet.getCell(1, i).getContents();
            String interestRate = readableSheet.getCell(2, i).getContents();
            String zipCode = readableSheet.getCell(3, i).getContents();
            String payMonth = readableSheet.getCell(4, i).getContents();
            String payYear = readableSheet.getCell(5, i).getContents();

            //navigating to mlcalc
            Reusable_Method.navigate(driver, "https://www.mlcalc.com");

            //verify the expected title using Hard Assert
            //Assert.assertEquals("Mortgage Calc", driver.getTitle());

            //verify the expected title using soft Assert
            softAssert.assertEquals("Mortgage Loan Calculator", driver.getTitle());



            //clear purchase field
            Reusable_Method.ClearMethod(driver, "//*[@name='ma']", "Purchase Price");
            Reusable_Method.sendKeysMethods(driver, "//*[@name='ma']", purchasePrice, "Purchase Price");
            //clear down payment field
            Reusable_Method.ClearMethod(driver, "//*[@name='dp']", "Down Payment");
            //enter information to purchase price
            Reusable_Method.sendKeysMethods(driver, "//*[@name='dp']", downPayment, "Down Payment");

            //clear Interest Rate field
            Reusable_Method.ClearMethod(driver, "//*[@name='ir']", "Interest Rate");
            //enter information to purchase price
            Reusable_Method.sendKeysMethods(driver, "//*[@name='ir']", interestRate, "Interest Rate");

            //clear Zip Code field
            Reusable_Method.ClearMethod(driver, "//*[@name='zipCode']", "Zip Code");
            //enter information to purchase price
            Reusable_Method.sendKeysMethods(driver, "//*[@name='zipCode']", zipCode, "Zip Code");

            //select pay month
            Reusable_Method.selectByText(driver, "//*[@name='sm']", payMonth, "Month");

            //select pay year
            Reusable_Method.selectByText(driver, "//*[@name='sy']", payYear, "Year");

            //click on Calculate button
            Reusable_Method.ClickMethod(driver, "//*[@alt='Calculate']", "Calculate");

            String monthlyPayment = Reusable_Method.captureText(driver, "//*[@class='big']", 0, "Monthly Payment");

            String payoffDate = Reusable_Method.captureText(driver, "//*[@class='big']", 2, "Pay Off Date");

            Label label1 = new Label(6, i, monthlyPayment);
            writableSheet.addCell(label1);

            //mortgage title
            String payOffTitle = driver.findElements(By.xpath("//*[@nowrap='nowrap']")).get(2).getText();
            if (payOffTitle.equalsIgnoreCase("Mortgage payoff date")) {
                //capture payoff date
                String payOffDate = Reusable_Method.captureText(driver, "//*[@class='big']", 2, "Payoff Date");
                //add label for payoff date
                Label label2 = new Label(7, i, payOffDate);
                writableSheet.addCell(label2);
            } else {
                String payOffDate = Reusable_Method.captureText(driver, "//*[@class='big']", 3, "Payoff Date");
                //add label for payoff date
                Label label2 = new Label(7, i, payOffDate);
                writableSheet.addCell(label2);
            }


        }//end of for loop

        //assertAll using soft assert will handle and catch your exception and show it on your logs
        softAssert.assertAll();


    }//end of test execution
}


