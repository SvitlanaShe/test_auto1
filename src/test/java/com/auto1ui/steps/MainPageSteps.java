package com.auto1ui.steps;

import com.auto1ui.pages.MainPage;
import com.auto1ui.tests.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class MainPageSteps {

    public WebDriver driver;
    private MainPage mainPage;

    public MainPageSteps(WebDriver driver) {
        this.driver = driver;
        mainPage = new MainPage(driver);
    }

    public void selectCheckbox(String manufacture) {
        mainPage.selectCheckbox(manufacture);
        waitForFilterPresent(manufacture);

    }

    public boolean manufactureFilterIs(String manufacture) {
        List<String> selectedManufactures = new ArrayList<>();
        (mainPage.getSelectedManufactures)
                .forEach(manuf -> selectedManufactures.add(manuf.getAttribute("title")));
        System.out.println(selectedManufactures);
        return selectedManufactures.contains(manufacture);
    }

    WebDriverWait wait;
    public void waitForFilterPresent(String manufacture){
        MainPage mainPage = new MainPage(driver);
        wait = new WebDriverWait(driver, 3000);
        wait.until(ExpectedConditions
                .presenceOfElementLocated(mainPage.getByFilterForManufacture(manufacture)));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
