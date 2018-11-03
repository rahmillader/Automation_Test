package automation_Practice;

import ReusableObjects.Reusable_Method;
import ReusableObjects.Reusable_Method_Loggers;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


public class ActionItem {


   /* @Test case 1
    you will define logger1 = report.startTest("Proceed to Check out for Tshirts"). you will use logger 1 for this
    1. navigate to http://automationpractice.com/index.php
    2. Verify the page title displays as My-Store //use if else with log.PASS and log.FAIL
    3. using mouseHover method hover over Women tab
    4. click on T-shirts link from there
    5. scroll down about 350 times on next page
    6. now hover over the picture with women in it
    7. click on add to cart button
    8. on the pop up using if else verify the message appears '
    Product successfully added to your shopping cart'
    9. click on proceed to checkout button
    10. change the quantity to 3 items
    11. click on proceed to check out*/


   /* now @Test case 2 will be depending on test case 1
    you will start another logger saying
    logger2 = report.startTest("Proceed to Checkout for Summer Dresses")
    1. Hover over Dresses tab
    2. click on Summer Dresses
    3. scroll down about 250 to 300 times
    4. hover over first picture of the dress
    5. click on More tab
    6. change the quantity to 4
    7. select a size from dropdown(S,M or L)
    8. click on 'Add to Car' button
    9. on pop up verify checkpoint says Product successfully added to your shopping cart using if else condition with logger.pass and fail
    10. click on proceed to checkout
    11. next page click on delete icon to delete the item
    12. on next page verify following message appears using if else
    Your shopping cart is empty. */


    //declare all the global variables before annotation methods
    WebDriver driver;
    ExtentReports report;
    ExtentTest logger1;
    ExtentTest logger2;
    JavascriptExecutor jse;



    @BeforeSuite
    public void openBrowser(){
        //calling driver from reusable method
        driver = Reusable_Method.chromedriver();
        //implicit wait
        driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
        //define the report path
        //report = new ExtentReports("src\\main\\java\\Report_Folder\\ExtentReport" + UUID.randomUUID() + ".html", true);
        report = new ExtentReports("src\\main\\java\\Report_Folder\\ExtentReport.html", true);
        jse=(JavascriptExecutor)driver;

    }//end of before suite


    @AfterSuite
    public void closeBrowser(){
        report.endTest(logger1);
        report.endTest(logger2);
        report.flush();
        report.close();
        //driver.quit;

    }//end of after suite


    @Test
    public void TShirts() throws IOException, InterruptedException {

        //start the test
        logger1 = report.startTest("Proceed to Check out for Tshirts");
        //1. navigate to http://automationpractice.com/index.php
        Reusable_Method_Loggers.navigate(logger1, driver,"http://automationpractice.com/index.php");
        //2. Verify the page title displays as My-Store //use if else with log.PASS and log.FAIL
        String practiceTitle = driver.getTitle();
        if (practiceTitle.equalsIgnoreCase("My Store")){
            logger1.log(LogStatus.PASS,"my store title matches");

        }else {
            logger1.log(LogStatus.FAIL,"my store title doesn't match " + practiceTitle);

        }//end of else

        //3. using mouseHover method hover over Women tab
        Reusable_Method_Loggers.mouseHover(logger1,driver,"//*[@title='Women']","Women Tab");
        Thread.sleep(1500);
        //4. click on T-shirts link from there
        Reusable_Method_Loggers.mouseHoverClick(logger1,driver,"//*[@title='T-shirts']","T-Shirt");
        //5. scroll down about 350 times on next page
        //define javascript executor

        //scroll to the bottom of the page
        logger1.log(LogStatus.INFO,"Scroll down about 350 on next page");
        Thread.sleep(1500);
        jse.executeScript("scroll(0,350)");

        //6. now hover over the picture with women in it
        Thread.sleep(1500);
        Reusable_Method_Loggers.mouseHover(logger1,driver,"//*[@title='Faded Short Sleeve T-shirts']","picture");
        Thread.sleep(1500);
        //7. click on add to cart button
        Reusable_Method_Loggers.mouseHoverClick(logger1,driver,"//*[@title='Add to cart']","Add to Cart button");
        Thread.sleep(1500);
        //8. on the pop up using if else verify the message appears 'Product successfully added to your shopping cart'
        String popUpMessage = driver.findElement(By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[1]/h2")).getText();
        if (popUpMessage.equalsIgnoreCase("Product successfully added to your shopping cart")) {
            logger1.log(LogStatus.PASS, "Product successfully added to your shopping cart matches");
        } else {
            logger1.log(LogStatus.FAIL, "Product successfully added to your shopping cart doesn't match " + popUpMessage);
        }//end of else

        Thread.sleep(3000);
        //9. click on proceed to checkout button
        Reusable_Method_Loggers.ClickMethod(logger1,driver,"//*[@id=\"layer_cart\"]/div[1]/div[2]/div[4]/a/span/i","Checkout button");
        //10. change the quantity to 3 items
        Thread.sleep(1500);
        Reusable_Method_Loggers.ClearMethod(logger1,driver,"//*[@class='cart_quantity_input form-control grey']","clear the items");
        Reusable_Method_Loggers.sendKeysMethods(logger1,driver,"//*[@class='cart_quantity_input form-control grey']","3","quantity");
        //11. click on proceed to check out
        Thread.sleep(3000);
        jse.executeScript("scroll(0,350)");
        Thread.sleep(3000);
        Reusable_Method_Loggers.ClickMethodByIndex(logger1,driver,"//*[@title='Proceed to checkout']",1,"Proceed to checkout");



        }//end of test

    @Test (dependsOnMethods = "TShirts")
    public void TestCase2() throws IOException, InterruptedException {
        //start the test
        logger2 = report.startTest("Proceed to Checkout for Summer Dresses");
        Reusable_Method_Loggers.navigate(logger2,driver,"http://automationpractice.com/index.php");
        Thread.sleep(1500);
        //1. Hover over Dresses tab
        Reusable_Method_Loggers.mouseHoverByindex(logger2,driver,"//*[@class='sf-with-ul']",3,"Dresses Tab");
        //2. click on Summer Dresses
        Reusable_Method_Loggers.mouseHoverByindex(logger2,driver,"//*[@title='Summer Dresses']",1,"summer dresses");
        //3. scroll down about 250 to 300 times
        //define javascript executor
        //scroll to the bottom of the page
        logger1.log(LogStatus.INFO,"Scroll down about 330 on next page");
        Thread.sleep(1500);
        jse.executeScript("scroll(0,300)");
        //4. hover over first picture of the dress
        Reusable_Method_Loggers.mouseHover(logger2,driver,"//*[@alt='Printed Summer Dress']","Hover over the first picture");
        //5. click on More tab
        Reusable_Method_Loggers.mouseHoverClick(logger2,driver,"//*[@class='button lnk_view btn btn-default']","More Tab");

        //6. change the quantity to 4
        Reusable_Method_Loggers.ClearMethod(logger2,driver,"//*[@id='quantity_wanted']","clear the items");
        Thread.sleep(1500);
        Reusable_Method_Loggers.sendKeysMethods(logger2,driver,"//*[@id='quantity_wanted']","4","quantity");
        //7. select a size from dropdown(S,M or L)
        Reusable_Method_Loggers.SelectMethod(logger2,driver,"//*[@class='selector']","M","dropdown");
        //8. click on 'Add to Cart' button
        Reusable_Method_Loggers.ClickMethod(logger2,driver,"//*[@class='exclusive']","Add to Cart button");
        Thread.sleep(1500);
        //9. on pop up verify checkpoint says Product successfully added to your shopping cart using if else condition with logger.pass and fail
        String popUpMessage = driver.findElement(By.xpath("//*[@class='icon-ok']")).getText();
        if (popUpMessage.equalsIgnoreCase("Product successfully added to your shopping cart")){
            logger2.log(LogStatus.PASS,"Product successfully added to your shopping cart title matches");

        }else {
            logger2.log(LogStatus.FAIL,"Product successfully added to your shopping cart title doesn't match " + popUpMessage);

        }//end of else

        //10. click on proceed to checkout
        Reusable_Method_Loggers.ClickMethod(logger2,driver,"//*[@title='Proceed to checkout']","CheckOut");
        //11. next page click on delete icon to delete the item
        Reusable_Method_Loggers.ClickMethod(logger2,driver,"//*[@class='icon-trash']","Delete Icon");
        //12. on next page verify following message appears using if else
        //    Your shopping cart is empty. */
        String empty = driver.getTitle();
        if (empty.equalsIgnoreCase("Your shopping cart is empty.")) {
            logger1.log(LogStatus.PASS, "Your shopping cart is empty. matches");
        } else {
            logger1.log(LogStatus.FAIL, "Your shopping cart is empty. doesn't match " + empty);
        }//end of else



    }//end of testcase2



}//end of public class
