package com.auto1ui.tests.smoke;
import com.auto1ui.steps.CarSteps;
import com.auto1ui.steps.MainPageSteps;
import com.auto1ui.tests.BaseTest;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;

public class SmokeTests extends BaseTest {
    WebDriver driver = getDriver();
    MainPageSteps mainPage = new MainPageSteps(driver);
    CarSteps carSteps = new CarSteps(driver);

    @Then("^close driver and browser$")
    public void close_driver_and_browser() throws Throwable {
        shutDown();
    }

    @Given("on main page")
    public void on_main_page() throws IOException, InterruptedException {
        openSite();
    }

    @When("^wait for page to be loaded$")
    public void wait_for_page_to_be_downoaded() throws Throwable {
        waitForPageToLoad();
    }

    @When("^filter by manufacture \"([^\"]*)\" applyed$")
    public void filter_by_manufacture_applyed(String manufacture)  {
        Assert.assertTrue(mainPage.manufactureFilterIs(manufacture),
                " Filter for " + manufacture + " was not applyed!");
    }


    @When("^click checkbox \"([^\"]*)\"$")
    public void click_checkbox(String manufacture)  {
        mainPage.selectCheckbox(manufacture);

    }

    @Then("^all car's top names contains \"([^\"]*)\"$")
    public void all_car_s_top_names_contains(String manufacture)  {
        carSteps.getTopNames().forEach(
                topName-> Assert.assertTrue(topName.contains(manufacture)
                ));
    }

    @Then("^each car has image$")
    public void each_car_has_image() throws Throwable {
        carSteps.getCarsImages().forEach(
                imageLink-> Assert.assertTrue(imageLink.contains("//img-pa.auto1.com/img")
                ));
        Assert.assertEquals(carSteps.getCarsImages().size(),20);
    }

//    @Given("^login with credentionals \"([^\"]*)\" \"([^\"]*)\"$")
//    public void login_with_credentionals(String login, String password) throws Throwable {
//        Thread.sleep(6000);
//        loginSteps.clickLogIn();
//        loginSteps.enterLogin(login);
//        loginSteps.enterPassword(password);
//        loginSteps.clickOk();
//    }

    @Then("^no empty data in car's description$")
    public void no_empty_data_in_car_s_description()  {
        List<String> carNames = carSteps.getTopNames();
        carNames.forEach(carName -> {
            System.out.println("&&&&&&&&&&____________" + carName);
            try {
                Assert.assertTrue(carSteps.optionsNotEmpty(carName));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }


}
