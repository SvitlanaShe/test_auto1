package com.auto1ui;

import com.auto1ui.enums.Browser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.io.File;
import java.io.IOException;

public class WebDriverFactory {
    private static WebDriver driver;
    private static Browser browser = Browser.CHROME;

    public WebDriverFactory() throws IOException {
//        getBrowser();
        switch (getBrowser()){
            case "Chrome":
            case "chrome":
                this.browser = Browser.CHROME;
                break;
            case "IE":
            case "ie":
                this.browser = Browser.IE;
        }
        getDriver();

    }

public String getBrowser() {
    String browserStr = System.getProperty("BROWSER");
    if (browserStr == null) {
        browserStr = System.getenv("BROWSER");
        if (browserStr == null) {
            browserStr = "chrome";
        }
    }
    return browserStr;
}


    public static WebDriver getDriver() {

        if (driver == null) {
            return newWebDriver();
        }
        return driver;
    }

    private static WebDriver newWebDriver() {
        prepareDrivers();
        String driverPath = "";
        switch (browser) {
            case IE:
                driverPath = WebDriverFactory.class.getClassLoader().getResource("drivers/ie.exe").getPath();
                System.setProperty("webdriver.ie.driver",driverPath);
                driver = new InternetExplorerDriver();
                break;
            case CHROME:
                driverPath = WebDriverFactory.class.getClassLoader().getResource("drivers/chromedriver.exe").getPath();
                System.setProperty("webdriver.chrome.driver", driverPath);
                driver = new ChromeDriver();
                break;
            default:
                System.out.print("Incorrect browser name, please select IE or Chrome");
        }

        return driver;
    }

    private  static  void prepareDrivers(){
        String source = WebDriverFactory.class.getClassLoader().getResource("drivers").getPath();
        renameFileExtensions(new File(source + "/chromedriver"), "exe");
        //  ie driver could be added here . I do not add it, only for example - Svetlanashe
        //        renameFileExtensions(new File(source + "/ie"), "exe");
    }

    public static File renameFileExtensions(File file, String extension) {
        String filename = file.getName();

        if (filename.contains(".")) {
            filename = filename.substring(0, filename.lastIndexOf('.'));
        }
        filename += "." + extension;

        file.renameTo(new File(file.getParentFile(), filename));
        return file;
    }
}
