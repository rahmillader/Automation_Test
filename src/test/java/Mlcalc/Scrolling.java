/*
package Mlcalc;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Scrolling {
    ChromeDriver driver;
    @Test
    public void scrolling() throws InterruptedException {

        //define the driver path
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized", "--incognito");
        driver = new ChromeDriver(options);

        //implicit wait
        driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);

        //navigate to site
        driver.navigate().to("https://www.mortgagecalculator.net/");
        //defining the javascriptexecutor
        JavascriptException jse = (JavascriptException) driver;
        //scroll into the calculate element
        WebElement calculateBtn = driver.findElement(By.xpath("//*[@value= 'Calculate Now'"));
        driver.executeScript();
        //timer
        Thread.sleep(2000);
        //scroll number of times going down
        driver.executeScript();
        Thread.sleep(2000);
        //going up
        driver.executeScript();






    }


    private class WebDriver {
    }
}//
*/
