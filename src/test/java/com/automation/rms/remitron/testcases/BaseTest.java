package com.automation.rms.remitron.testcases;

import com.automation.rms.remitron.pages.BasePage;
import com.automation.rms.remitron.pages.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class BaseTest {
    WebDriver driver;
    Page page;
    Properties properties;
    protected BaseTest(){
        try {
            properties = new Properties();
            String filePath=System.getProperty("user.dir") + "/src/test/resources/config.properties";
            FileInputStream inputStream = new FileInputStream(filePath);
            properties.load(inputStream);
        } catch (FileNotFoundException e){
            throw new RuntimeException();

        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @BeforeMethod
    public void setUpBrowser() {
        String browserName = properties.getProperty("browserName");
        switch (browserName) {
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "firefox": driver=new FirefoxDriver();
                break;
            case "firefoxHeadless":
                FirefoxOptions foxOptions = new FirefoxOptions();
                foxOptions.addArguments("--headless");
                driver=new FirefoxDriver(foxOptions);
                break;
            case "chromeHeadless":
                ChromeOptions chromeoptions = new ChromeOptions();
                chromeoptions.addArguments("--headless");
                driver=new ChromeDriver(chromeoptions);
                break;
            default:
                System.out.println("please provide right browser name");
        }
        driver.get(properties.getProperty("url"));
        driver.manage().window().maximize();

        page=new BasePage(driver);
    }
    @AfterMethod
    public void tearDownBrowser() {
        //driver.quit();
    }
    public WebDriver getWebDriver() {
        return driver;
    }
    public  String getUserName(){
        return properties.getProperty("username");
    }
    public  String getPassword(){
        return properties.getProperty("password");
    }
}
