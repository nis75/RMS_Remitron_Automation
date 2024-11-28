package com.automation.rms.remitron.pages;

import com.automation.rms.remitron.report.ReportTestManager;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class BasePage extends Page{
    public BasePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public String getPageTitle() {
        return driver.getTitle().trim();
    }

    @Override
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    @Override
    public WebElement getWebElement(By locator) {
        WebElement element = null;
        try {
            addInfo(locator + " going to operate");
            //addInfo("Appium is going to search " + locator + " Locator or Selector");
            element = driver.findElement(locator);
            //addInfo(selector.toString() + " already did successfully operation");

        }catch (Exception e)
        {
            System.out.println(locator.toString()+"Select or Locator is not found");
        }
        addInfo(locator + " Locator or Selector found");
        return element;
    }

    @Override
    public List<WebElement> getWebElements(By locator) {
        List<WebElement> elements = null;
        try {
            addInfo(locator + " going to operate");
            //addInfo("Appium is going to search " + locator + " Locator or Selector");
            elements = driver.findElements(locator);

        }catch (Exception e)
        {
            System.out.println(locator.toString()+"Select or Locator is not found");
        }
        addInfo(locator + " Locator or Selector found");
        return elements;
    }

    @Override
    public void waitForElement(By locator) {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        }catch (Exception e){
            System.out.println(locator.toString()+"Select or Locator is not found");
        }


    }
    public void addInfo(String message) {
        if (ReportTestManager.getTest() != null) {
            ReportTestManager.getTest().log(Status.INFO, message);
        }
    }
}
