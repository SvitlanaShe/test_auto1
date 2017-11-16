package com.auto1ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.*;

@FindBy(xpath = "//*[@class='car-item']")
public class CarPage {
    WebDriver driver;

    public CarPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public List<String[]> getOptions(String carName) throws Exception{
        List<String[]> options = new ArrayList<>();
        for(int i = 0; i<7; i++){
            String[] str = new String[]{ ((driver.findElement(By.xpath("//*[contains(text(),'" + carName + "')]/..//tr["+(i+1) + "]/td[1]"))).getText())
                    ,((driver.findElement(By.xpath("//*[contains(text(),'" + carName + "')]/..//tr["+(i+1) +"]/td[2]"))).getText())};
            options.add(new String[]{str[0], str[1]});
        }
        options.forEach( option -> {System.out.println(option[0] + " " + option[1]);});
        System.out.println ("options" + options.size());
         return options;
    }

    public List<WebElement> getTopNames() {
        return driver.findElements(By.xpath("//div[@class='car-name']"));
    }

    public List<WebElement>  getMainImages() {
        return driver.findElements(By.xpath("//*[@class='car-item']//img"));
    }
}
