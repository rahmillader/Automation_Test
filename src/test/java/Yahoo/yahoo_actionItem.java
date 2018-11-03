package Yahoo;

import ReusableObjects.Reusable_Method_Loggers;
import Utilities.Abstract_Class_parallel;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class yahoo_actionItem extends Abstract_Class_parallel {


    @Test
    public void yahooSearchResult() throws IOException, InterruptedException {

        //navigate to yahoo homepage
        Reusable_Method_Loggers.navigate(logger,driver,"https://yahoo.com");
        //verify the page title is "Yahoo"
        String actualTitle = driver.getTitle();
        if (actualTitle.equals("Yahoo")){
            logger.log(LogStatus.PASS,"Title matches as Yahoo");
        }else{
            logger.log(LogStatus.FAIL,"Title does't --- " + actualTitle);
        }//end of if else
        //display the count of the links
        List<WebElement>ListCount = driver.findElements(By.xpath("//*[contains(@class,'D(ib) Mstart(21px) Mend(13px)')]"));
        logger.log(LogStatus.INFO,"Link count is " + ListCount.size());
        System.out.println("Link count is " + ListCount.size());

        //enter keywork 'Nutrition' into search field
        Reusable_Method_Loggers.sendKeysMethods(logger,driver,"//*[@name='p']","Nutrition","enter Nutrition on the search field");
        Reusable_Method_Loggers.ClickMethod(logger,driver,"//*[@id='uh-search-button']","click button");
        //wait statement
        jse.executeScript("scroll(0,5000)");
        Thread.sleep(3000);

        //display the search count
        Reusable_Method_Loggers.captureTextWithSplit(logger,driver,"//*[@class='compPagination']","Search Result");
        //scroll up to sign in and then click on it
        Thread.sleep(1000);
        jse.executeScript("scroll(0,-5000)");
        Reusable_Method_Loggers.ClickMethod(logger,driver,"//*[@id='yucs-login_signIn']","SignIn button");

        //verify by default the checkbox is selected
        Thread.sleep(2000);
        boolean checkBoxState = driver.findElement(By.xpath("//*[@id='persistent']")).isSelected();
        if (checkBoxState ==true){
            logger.log(LogStatus.PASS,"checkbox is selected by default");
        }else {
            logger.log(LogStatus.FAIL,"checkbox is not selected by default");
            Reusable_Method_Loggers.getScreenshot(driver,logger,"Check box State");
        }

        //enter invalid email
        Reusable_Method_Loggers.sendKeysMethods(logger,driver,"//*[@name='username']","abcd1234","email field");
        //click on the next button
        Reusable_Method_Loggers.ClickMethod(logger,driver,"//*[@name='signin']","Next button");

        //compare the error message
        Thread.sleep(5000);
        Reusable_Method_Loggers.compageMessages(logger,driver,"//*[@id='username-error']",0,"Sorry, we don't recognize this email.","error message");



    }//end of test class


}//end of public class
