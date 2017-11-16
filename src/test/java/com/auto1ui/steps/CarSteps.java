package com.auto1ui.steps;

import com.auto1ui.pages.CarPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CarSteps {
    private WebDriver driver;
    private CarPage carPage;

    public CarSteps(WebDriver driver) {
        this.driver = driver;
        carPage = new CarPage(driver);
    }

    public List<String> getTopNames() {
        List <String> names = new ArrayList<>();
        carPage.getTopNames().forEach(name -> System.out.println(name.getText()));
        carPage.getTopNames().forEach(name -> names.add(name.getText()));
        return names;
    }

    public List<String> getCarsImages() {
        List<String> images = new ArrayList<>();
        carPage.getMainImages().forEach(name -> System.out.println("image " + name.getAttribute("src")));
        carPage.getMainImages().forEach(image -> images.add(image.getAttribute("src")));
        return images;
    }

    public List<String[]> getOptionsForCarName(String carName) throws Exception {
        return carPage.getOptions(carName);
    }

    public boolean optionsNotEmpty(String carName) throws Exception {
        List<String[]> options = getOptionsForCarName(carName);
        Assert.assertFalse(options.size()==0, " Empty options list!!!! ");
        boolean flag = true;
        for (int i = 0; i<options.size(); i++) {
            if( options.get(i)[0].length()== 0 ){
                System.out.println(i  + "the option description is missing! please review option number " + i);
                flag = false;
            }
            if( options.get(i)[1].length()== 0 ){
                System.out.println( options.get(i)[0] + "the option VALUE description is missing! please review option VALUE number " + i);
                flag = false;
            }
        }
        return flag;
    }
}
