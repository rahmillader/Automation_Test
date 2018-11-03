package ReusableObjects;


import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.IOException;

public class Reusable_Method_Loggers {

    //method for navigating ro a page
    public static void navigate(ExtentTest logger, WebDriver driver, String url) throws IOException {

        try{
            //System.out.print("Navigate to " + url);
            logger.log(LogStatus.INFO,"Navigate to " + url );
            driver.navigate().to(url);

        }catch (Exception error){
            //System.out.println("Unable to navigate to the url... " + error);
            logger.log(LogStatus.FAIL,"Unable to navigate to the url... " + error);
            getScreenshot(driver,logger,"URL Error");
        }

    }//end of navigate method

    //method for clicking on an element
        public static void ClickMethod(ExtentTest logger, WebDriver driver, String locator, String elementName) throws IOException {
            try {
                //System.out.println("Clicking on element " + elementName);
                logger.log(LogStatus.INFO,"Clicking on element " + elementName );
                //store the locator into webElement variable
                WebElement Clickbtn = driver.findElement(By.xpath(locator));
                Clickbtn.click();


            } catch (Exception error) {
                //System.out.println("Unable to click on element " + elementName + " " + error);
                logger.log(LogStatus.FAIL,"Unable to click on element " + elementName);
                getScreenshot(driver,logger,elementName);

            }//end of try and catch
        }//end of Click method

    //method for clearing on an element
    public static void ClearMethod(ExtentTest logger,WebDriver driver, String locator, String elementName) throws IOException {
        try {
            //System.out.println("Clearing on element " + elementName);
            logger.log(LogStatus.INFO,"Clearing element " +elementName);
            //store the locator into webElement variable
            WebElement Clrbtn = driver.findElement(By.xpath(locator));
            Clrbtn.clear();

        } catch (Exception error) {
            //System.out.println("Unable to clear on element " + elementName + " " + error);
            logger.log(LogStatus.FAIL,"Unable to clear element " + elementName);
            getScreenshot(driver,logger,elementName);

        }//end of try and catch
    }//end of clear method

            //method for clicking on an element by index
            public static void ClickMethodByIndex(ExtentTest logger, WebDriver driver, String locator, int indexNumber, String elementName) throws IOException {
                try {
                    //System.out.println("Clicking on element " + elementName);
                    logger.log(LogStatus.INFO,"Clicking on element " + elementName);
                    //store the locator into webElement variable
                    WebElement Clickbtn = driver.findElements(By.xpath(locator)).get(indexNumber);
                    Clickbtn.click();

                } catch (Exception error) {
                    //System.out.println("Unable to click on element " + elementName);
                    logger.log(LogStatus.FAIL,"Unable to click on element " + elementName);
                    getScreenshot(driver,logger,elementName);

                }//end of try and catch
            }//end of click method by index


            //select from dropdown list method
    public static void SelectMethod(ExtentTest logger,WebDriver driver,String locator, String userInput, String elementName) throws IOException {

        try{
            logger.log(LogStatus.INFO,"Selecting item from the drop down of " + elementName);
            WebElement listItem = driver.findElement(By.xpath(locator));
            Select itemDropdown = new Select(listItem);
            itemDropdown.selectByVisibleText(userInput);
        }catch (Exception err){
            logger.log(LogStatus.FAIL,"Unable to select from the " + elementName);
            getScreenshot(driver, logger,elementName);
        }//end of catch
    }//end of select method


        //method for submitting on an element
        public static void submitMethod (ExtentTest logger, WebDriver driver, String locator, String elementName) throws IOException {
            try {
                //System.out.println("Clicking on element " + elementName);
                logger.log(LogStatus.INFO,"Clicking on element " + elementName);
                //store the locator into webElement variable
                WebElement submitbtn = driver.findElement(By.xpath(locator));
                submitbtn.submit();

            } catch (Exception error) {
                //System.out.println("Unable to Submit on element " + elementName);
                logger.log(LogStatus.FAIL,"Unable to click on element " + elementName);
                getScreenshot(driver,logger,elementName);

            }//end of try and catch

        }//end of submit method

        //method for entering on an element
        public static void sendKeysMethods (ExtentTest logger, WebDriver driver, String locator, String userInput, String elementName) throws IOException {
            try {
                //System.out.println("Entering " + userInput + "in element" + elementName);
                logger.log(LogStatus.INFO,"Entering " + userInput + "in element" + elementName);
                //store the locator into webElement variable
                WebElement input = driver.findElement(By.xpath(locator));
                input.sendKeys(userInput);

            } catch (Exception error) {
                //System.out.println("Unable to send info on element " + elementName);
                logger.log(LogStatus.FAIL,"Unable to send info on element " + elementName);
                getScreenshot(driver,logger,elementName);

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
        public static String captureText(ExtentTest logger, WebDriver driver, String locator, int indexNumber, String elementName) throws IOException {
            String textValue = null;
            try{
                //System.out.println("Capturing Text " + elementName);
                logger.log(LogStatus.INFO,"Capturing Text " + indexNumber,"from" + elementName);
                textValue = driver.findElements(By.xpath(locator)).get(indexNumber).getText();

            }catch (Exception error){
                //System.out.println("Unable to capture text " + elementName + " " + error);
                logger.log(LogStatus.FAIL,"Unable to capture text " + elementName);
                getScreenshot(driver, logger,elementName);

            }//end of catch
            return textValue;

        }//end of capture text method


    public static String captureTextWithSplit(ExtentTest logger, WebDriver driver, String locator, String elementName) throws IOException {
        String [] arrText = null;
        try{
            //System.out.println("Capturing Text " + elementName);
            //logger.log(LogStatus.INFO,"Capturing Text from" + elementName);
            String text = driver.findElement(By.xpath(locator)).getText();
            arrText = text.split("");
            logger.log(LogStatus.PASS,"Capturing Text from" + arrText[1]);

        }catch (Exception error){
            //System.out.println("Unable to capture text " + elementName + " " + error);
            logger.log(LogStatus.FAIL,"Unable to capture text " + elementName);
            getScreenshot(driver, logger,elementName);

        }//end of catch
        return arrText[1];

    }//end of capture text method


    //get screen shot method
    public static void getScreenshot(WebDriver driver, ExtentTest logger, String screenshotName) throws IOException {
        // String path = "C:\\Users\\sumon.kashem\\Desktop\\Screenshots\\";
        String path = "src\\main\\java\\Report_Folder\\ScreenShots\\";
        String fileName = screenshotName + ".png";
        File sourceFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        //Now you can do whatever you need to do with, for example copy somewhere
        FileUtils.copyFile(sourceFile, new File(path + fileName));
        //String imgPath = directory + fileName;
        String image = logger.addScreenCapture("ScreenShots\\" + fileName);
        logger.log(LogStatus.FAIL, "", image);
    }//end of screenShot method


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

    //mouse Hover click method
    //mouse Hover method with index

    public static void mouseHoverByindex(ExtentTest logger, WebDriver driver, String locator,int indexNumber, String elementName) throws IOException {
        Actions mouseMovement = new Actions(driver);
        try{
            logger.log(LogStatus.INFO, "Hovering on element " + elementName);
            WebElement hover = driver.findElements(By.xpath(locator)).get(indexNumber);
            mouseMovement.moveToElement(hover)
                    .build()
                    .perform();
        }catch (Exception err){
            logger.log(LogStatus.FAIL, "Unable to click on element " + elementName);
            getScreenshot(driver,logger,elementName);
        }
    }//end of mouse hover by index number method

    //mouse Hover click method

    public static void mouseHoverClick(ExtentTest logger, WebDriver driver, String locator, String elementName) throws IOException {
        Actions mouseMovement = new Actions(driver);
        try{
            logger.log(LogStatus.INFO, "Hovering on element " + elementName);
            WebElement hover = driver.findElement(By.xpath(locator));
            mouseMovement.moveToElement(hover)
                    .click()
                    .build()
                    .perform();
        }catch (Exception err){
            logger.log(LogStatus.FAIL, "Unable to click on element " + elementName);
            getScreenshot(driver,logger,elementName);
        }
    }//end of mouse hover click method

    //method for comparing two data
    public static void compageMessages(ExtentTest logger, WebDriver driver, String locator, int indexNumber, String expectedMessage, String elementName) throws IOException {
        try {
            String actualMessage = driver.findElements(By.xpath(locator)).get(indexNumber).getText();
            if(expectedMessage.equalsIgnoreCase(actualMessage)){
                logger.log(LogStatus.PASS,"Expected matches with Actual for element " + elementName);
            }else{
                logger.log(LogStatus.FAIL,"Expected - " + expectedMessage + " doesn't match with actual - " + actualMessage);
                getScreenshot(driver,logger,elementName);
            }
        }catch (Exception err){
            logger.log(LogStatus.FAIL,"Unable to locate to element " + elementName);
            getScreenshot(driver,logger,elementName);
        }//end of catch

    }//end of comparing data method


        }//end of parent class

