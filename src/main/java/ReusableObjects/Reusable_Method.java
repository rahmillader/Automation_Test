package ReusableObjects;


import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.io.IOException;

import static ReusableObjects.Reusable_Method_Loggers.getScreenshot;

public class Reusable_Method {

    //setting up webdriver
    public static WebDriver chromedriver(){

        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized", "--incognito");
        WebDriver driver = new ChromeDriver(options);
        return driver;
    }//end of webdriver method

    //method for navigating ro a page
    public static void navigate(WebDriver driver, String url){

        try{
            System.out.print("Navigate to " + url);
            driver.navigate().to(url);

        }catch (Exception error){
            System.out.println("Unable to navigate to the url... " + error);

        }

    }//end of navigate method

    //method for clicking on an element
        public static void ClickMethod(WebDriver driver, String locator, String elementName) {
            try {
                System.out.println("Clicking on element " + elementName);
                //store the locator into webElement variable
                WebElement Clickbtn = driver.findElement(By.xpath(locator));
                Clickbtn.submit();

            } catch (Exception error) {
                System.out.println("Unable to click on element " + elementName + " " + error);

            }//end of try and catch
        }//end of Click method

    //method for clearing on an element
    public static void ClearMethod(WebDriver driver, String locator, String elementName) {
        try {
            System.out.println("Clearing on element " + elementName);
            //store the locator into webElement variable
            WebElement Clrbtn = driver.findElement(By.xpath(locator));
            Clrbtn.clear();

        } catch (Exception error) {
            System.out.println("Unable to clear on element " + elementName + " " + error);

        }//end of try and catch
    }//end of clear method

            //method for clicking on an element by index
            public static void ClickMethodByIndex(WebDriver driver, String locator, int indexNumber, String elementName) {
                try {
                    System.out.println("Clicking on element " + elementName);
                    //store the locator into webElement variable
                    WebElement Clickbtn = driver.findElements(By.xpath(locator)).get(indexNumber);
                    Clickbtn.submit();

                } catch (Exception error) {
                    System.out.println("Unable to click on element " + elementName);

                }//end of try and catch
            }//end of click method by index
        //method for submitting on an element
        public static void submitMethod (WebDriver driver, String locator, String elementName){
            try {
                System.out.println("Clicking on element " + elementName);
                //store the locator into webElement variable
                WebElement submitbtn = driver.findElement(By.xpath(locator));
                submitbtn.submit();

            } catch (Exception error) {
                System.out.println("Unable to Submit on element " + elementName);

            }//end of try and catch

        }//end of submit method

        //method for entering on an element
        public static void sendKeysMethods (WebDriver driver, String locator, String userInput, String elementName){
            try {
                System.out.println("Entering " + userInput + "in element" + elementName);
                //store the locator into webElement variable
                WebElement input = driver.findElement(By.xpath(locator));
                input.sendKeys(userInput);

            } catch (Exception error) {
                System.out.println("Unable to send info on element " + elementName);

            }//end of try and catch

        }//end of sendKey method

        //dropdown method by visible text
        public static void selectByText(WebDriver driver, String locator, String value, String elementName){

        try{
            System.out.println("Selecting " + value + "from dropdown " + elementName);
            //define the web element
            WebElement element = driver.findElement(By.xpath(locator));
            //define the select command
            Select select = new Select(element);
            //select by visible text
            select.selectByVisibleText(value);


        }catch(Exception error){
            System.out.println("Unable to select a value from dropdown " + elementName + " " + error);

        }//end of catch

        }//end of dropdown select

        //method for getText
        public static String captureText(WebDriver driver, String locator, int indexNumber, String elementName){
            String textValue = null;
            try{
                System.out.println("Capturing Text " + elementName);
                textValue = driver.findElements(By.xpath(locator)).get(indexNumber).getText();

            }catch (Exception error){
                System.out.println("Unable to capture text " + elementName + " " + error);
            }//end of catch
            return textValue;

        }//end of capture text method


    //mouse Hover method

    public static void mouseHover(ExtentTest logger, WebDriver driver, String locator, String elementName) throws IOException {
        Actions mouseMovement = new Actions(driver);
        try{
            logger.log(LogStatus.INFO, "Hovering on element " + elementName);
            WebElement hover = driver.findElement(By.xpath(locator));
            mouseMovement.moveToElement(hover)
                    .build()
                    .perform();
        }catch (Exception err){
            logger.log(LogStatus.FAIL, "Unable to click on element " + elementName);
            getScreenshot(driver,logger,elementName);
        }
    }//end of mouse hover method


        }//end of parent class

