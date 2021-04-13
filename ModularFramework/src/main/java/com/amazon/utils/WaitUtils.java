package com.amazon.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtils {

    private static long WAIT_TIME = 10;

    /**
     * Waiting element until present using string locator
     *
     * @param driver WebDriver
     * @param locator string
     */
    public static void untilPresent(WebDriver driver, String locator) {
        WebDriverWait wait = new WebDriverWait(driver, WAIT_TIME);
        WebElement element = driver.findElement(By.xpath(locator));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Waiting element until present using WebElement
     *
     * @param driver WebDriver
     * @param webElement WebElement
     */
    public static void untilPresent(WebDriver driver, WebElement webElement) {
        WebDriverWait wait = new WebDriverWait(driver, WAIT_TIME);
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    /**
     * Waiting element until clickable using WebElement
     *
     * @param driver WebDriver
     * @param webElement WebElement
     */
    public static void untilClickable(WebDriver driver, WebElement webElement) {
        WebDriverWait wait = new WebDriverWait(driver, WAIT_TIME);
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }
}
