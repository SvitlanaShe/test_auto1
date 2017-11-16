package com.auto1ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class MainPage {

    WebDriver driver;
    WebDriverWait wait ;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    @FindBy(xpath = "//li[@class='select2-selection__choice']")
    public List<WebElement> getSelectedManufactures;

    public void selectCheckbox(String manufacture) {
        By by = By.xpath("//span[@class='car-icon " + manufacture.toLowerCase()+ "']/../div");
        wait = new WebDriverWait(driver, 200);
        wait.until(ExpectedConditions.presenceOfElementLocated(by));

        JavascriptExecutor jse = (JavascriptExecutor)driver;

        WebElement element = driver.findElement(by);
        jse.executeScript("arguments[0].scrollIntoView()", element);
        element.click();
    }

    public By getByFilterForManufacture(String manufacture) {
        return By.xpath("//li[@class='select2-selection__choice'][@title='" + manufacture + "']");
    }

    public void waitForFilterPresent(String manufacture){
        MainPage mainPage = new MainPage(driver);
        wait = new WebDriverWait(driver, 800);
        wait.until(ExpectedConditions.presenceOfElementLocated(mainPage.getByFilterForManufacture(manufacture)));
    }
}
