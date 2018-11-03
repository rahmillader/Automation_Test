package TestSuite;

import ReusableObjects.Reusable_Method_Loggers;
import Utilities.Abstract_Class_parallel;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class Yahoo_Search extends Abstract_Class_parallel {

    @Test
    public void YahooSearchResult() throws InterruptedException, IOException {

        //navigate to yahoo homepage
        Reusable_Method_Loggers.navigate(logger, driver, "https://www.yahoo.com");
        //verify the home page title
        String yahooTitle = driver.getTitle();
        if (yahooTitle.equalsIgnoreCase("Yahoo")) {
            logger.log(LogStatus.PASS, "The yahoo title matches");
        } else {
            logger.log(LogStatus.FAIL, "The yahoo title doesn't match " + yahooTitle);
        }//end of else

        //verify the count of text links exist on home page
        List<WebElement> linkCount = driver.findElements(By.xpath("//*[contains (@class, 'Mstart(')]"));
        logger.log(LogStatus.INFO,"The link count is " + linkCount.size());

        //enter a keyword on search field
        Reusable_Method_Loggers.sendKeysMethods(logger,driver,"//*[@name='p']","Brooklyn","Search Field");

        //click on search
        Reusable_Method_Loggers.ClickMethod(logger,driver,"//*[@id='uh-search-button']","Search Icon");

        //define javascript executor
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        //scroll to the bottom of the page
        logger.log(LogStatus.INFO,"Scrolling to bottom of the search result page");
        Thread.sleep(1500);
        jse.executeScript("scroll(0,3000)");
        //capture search result




    }//end of test


}//end of parent class


