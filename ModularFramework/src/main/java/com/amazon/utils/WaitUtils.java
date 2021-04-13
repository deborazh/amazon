package com.amazon.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtils {

    private static long WAIT_TIME = 10;

    public static void untilPresent(WebDriver driver, String locator) {
        WebDriverWait wait = new WebDriverWait(driver, WAIT_TIME);
        WebElement element = driver.findElement(By.xpath(locator));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void untilPresent(WebDriver driver, WebElement webElement) {
        WebDriverWait wait = new WebDriverWait(driver, WAIT_TIME);
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }
}
