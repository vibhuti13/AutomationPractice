package com.practice.base;

import com.practice.reporting.Report;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {


protected final WebDriver driver;
protected final WebDriverWait webDriverWait;
protected final Report report;

    protected BasePage(WebDriver driver, Report report) {
        this.driver = driver;
        this.webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.report = report;
    }
    /** Abstract: each page must provide an element that proves the page has loaded. */
    protected abstract By pageIdentifier();

    /** Abstract: a readable name used in reports. */

    protected abstract String pageName();

    /** Concrete: shared by all pages, built on the abstract method above. */
    public boolean isLoaded(){
try{
    webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(pageIdentifier()));
    report.pass(pageName() + " is displayed");
    return true;

}
catch(Exception e){
    report.fail(pageName() + " is displayed");
    return false;
}
    }

    protected WebElement waitForVisible(By locator){
        return webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected void click(By locator){
        report.step("Click " + locator);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    protected void type(By  locator, String value){
        report.step("Type " + value + " into " + locator);
        WebElement element = waitForVisible(locator);
        element.clear();
        element.sendKeys(value);
    }

    protected String textOf(By locator){
        return waitForVisible(locator).getText();
    }

    public String title(){
        return driver.getTitle();
    }

}
