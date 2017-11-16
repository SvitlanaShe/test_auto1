package com.auto1ui.tests;

import com.auto1ui.JSWaiter;
import com.auto1ui.WebDriverFactory;

import com.auto1ui.pages.MainPage;
import cucumber.api.java.en.Then;
import org.junit.After;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    public static WebDriver driver;

    public  void openSite() throws IOException {
        openMainPage();
    }

     public BaseTest () {
         WebDriverFactory F = null;
         try {
             F = new WebDriverFactory();
         } catch (IOException e) {
             e.printStackTrace();
         }
         driver = F.getDriver();
    }

    public static WebDriver getDriver(){
        return WebDriverFactory.getDriver();
    }


     void openMainPage() throws IOException {

         Properties properties = new Properties();
        try(InputStream stream = getClass().getResourceAsStream("/url.properties")){
            properties.load(stream);
            driver.get( properties.getProperty("mainURI").toString() );
        }
         driver.manage().window().maximize();
         driver.manage().timeouts().implicitlyWait(100, TimeUnit.MILLISECONDS );
         try {
             Thread.sleep(1000);
         } catch (InterruptedException e) {
             e.printStackTrace();
         }
//         waitForPageToLoad();

    }

    public  void waitForPageToLoad() {
        JSWaiter.setDriver(driver);
        JSWaiter.waitForJQueryLoad();
        JSWaiter.waitJQueryAngular();
        JSWaiter.waitUntilJSReady();

    }

    public void shutDown(){
        if(driver!=null) {
            driver.close();
            driver.quit();
            driver = null;
        }
    }

}
