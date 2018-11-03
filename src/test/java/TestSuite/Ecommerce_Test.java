package TestSuite;

import ReusableObjects.Reusable_Method_Loggers;
import Utilities.Abstract_Class_parallel;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import java.io.IOException;

public class Ecommerce_Test extends Abstract_Class_parallel {

    @Test
    public void TShirts() throws IOException, InterruptedException {

        //1. navigate to http://automationpractice.com/index.php
        Reusable_Method_Loggers.navigate(logger, driver,"http://automationpractice.com/index.php");
        //2. Verify the page title displays as My-Store //use if else with log.PASS and log.FAIL
        String practiceTitle = driver.getTitle();
        if (practiceTitle.equalsIgnoreCase("My Store")){
            logger.log(LogStatus.PASS,"my store title matches");

        }else {
            logger.log(LogStatus.FAIL,"my store title doesn't match " + practiceTitle);

        }//end of else

        //3. using mouseHover method hover over Women tab
        Reusable_Method_Loggers.mouseHover(logger,driver,"//*[@title='Women']","Women Tab");
        Thread.sleep(1500);
        //4. click on T-shirts link from there
        Reusable_Method_Loggers.mouseHoverClick(logger,driver,"//*[@title='T-shirts']","T-Shirt");
        //5. scroll down about 350 times on next page
        //define javascript executor

        //scroll to the bottom of the page
        logger.log(LogStatus.INFO,"Scroll down about 350 on next page");
        Thread.sleep(1500);
        jse.executeScript("scroll(0,350)");

        //6. now hover over the picture with women in it
        Thread.sleep(1500);
        Reusable_Method_Loggers.mouseHover(logger,driver,"//*[@title='Faded Short Sleeve T-shirts']","picture");
        Thread.sleep(1500);
        //7. click on add to cart button
        Reusable_Method_Loggers.mouseHoverClick(logger,driver,"//*[@title='Add to cart']","Add to Cart button");
        Thread.sleep(1500);
        //8. on the pop up using if else verify the message appears 'Product successfully added to your shopping cart'
        String popUpMessage = driver.findElement(By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[1]/h2")).getText();
        if (popUpMessage.equalsIgnoreCase("Product successfully added to your shopping cart")) {
            logger.log(LogStatus.PASS, "Product successfully added to your shopping cart matches");
        } else {
            logger.log(LogStatus.FAIL, "Product successfully added to your shopping cart doesn't match " + popUpMessage);
        }//end of else

        Thread.sleep(3000);
        //9. click on proceed to checkout button
        Reusable_Method_Loggers.ClickMethod(logger,driver,"//*[@id=\"layer_cart\"]/div[1]/div[2]/div[4]/a/span/i","Checkout button");
        //10. change the quantity to 3 items
        Thread.sleep(1500);
        Reusable_Method_Loggers.ClearMethod(logger,driver,"//*[@class='cart_quantity_input form-control grey']","clear the items");
        Reusable_Method_Loggers.sendKeysMethods(logger,driver,"//*[@class='cart_quantity_input form-control grey']","3","quantity");
        //11. click on proceed to check out
        Thread.sleep(3000);
        jse.executeScript("scroll(0,350)");
        Thread.sleep(3000);
        Reusable_Method_Loggers.ClickMethodByIndex(logger,driver,"//*[@title='Proceed to checkout']",1,"Proceed to checkout");



    }//end of test



}
