package com.amazon.utils;

import com.amazon.pages.BasePage;
import enums.amazon.Department;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;


public class FilterUtils extends BasePage {

    private String departmentLocator = "//span[text()='%s']";
    private String brandLocator = "//span[text()='%s']/..//i";
    private String brandCheckedLocator = "//span[text()='%s']/..//input[@type='checkbox' and @checked]";
    private String pageNumberLocator = "//ul[contains(@class, 'a-pagination')]/li/a[contains(text(),'%s')]";

    @FindBy(css = "#nav-subnav")
    private WebElement subNavigationBar;

    @FindBy(id="low-price")
    private WebElement minPriceLocator;

    @FindBy(id="high-price")
    private WebElement maxPriceLocator;

    @FindBy(css = "input.a-button-input")
    private WebElement goButton;

    @FindBy(xpath = "//ul[contains(@class, 'a-pagination')]/li[contains(@class, 'a-selected')]")
    private WebElement currentPageNumber;


    public FilterUtils(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * Click Department Filter by text using Department enum
     *
     * @param department Department enum
     */
    public void clickDepartmentFilterByText(Department department) {
        String formattedLocator = String.format(this.departmentLocator, department.getText());
        driver.findElement(By.xpath(formattedLocator)).click();
        Assert.assertTrue(verifySubNavBarContainsText(Department.TOYS_AND_GAMES));
    }

    /**
     * Verify Sub-navigation bar contains text using Department enum
     *
     * @param department Department enum
     * @return boolean
     */
    private boolean verifySubNavBarContainsText(Department department) {
        return this.subNavigationBar.getText().toLowerCase().contains(department.getText().toLowerCase());
    }

    /**
     * Set filter by price
     *
     * @param minPrice string
     * @param maxPrice string
     */
    public void setFilterByPrice(String minPrice, String maxPrice){
        elementControl.clear(minPriceLocator);
        elementControl.clear(maxPriceLocator);

        elementControl.setText(minPriceLocator, minPrice);
        elementControl.setText(maxPriceLocator, maxPrice);

        elementControl.clickElement(goButton);
    }

    /**
     * Choose Brand by text
     *
     * @param text string
     */
    public void choseBrandByText(String text) {
        String formattedLocator = String.format(this.brandLocator, text.toUpperCase());
        driver.findElement(By.xpath(formattedLocator)).click();
        String formattedCheckedLocator = String.format(this.brandCheckedLocator, text.toUpperCase());
        WaitUtils.untilPresent(driver, formattedCheckedLocator);
    }

    /**
     * Assert page link is displayed by text
     *
     * @param text string
     */
    public void assertPageLinkIsDisplayedByText(String text) {
        String formattedLocator = String.format(this.pageNumberLocator, text);
        WebElement pageNumberElement = driver.findElement(By.xpath(formattedLocator));
        Assert.assertTrue(elementControl.isDisplayed(pageNumberElement));
    }

    /**
     * Assert Current Page number
     *
     * @param expectedText string
     */
    public void assertCurrentPageNumber(String expectedText) {
        String currentNumber = currentPageNumber.getText().trim();
        Assert.assertEquals(currentNumber, expectedText);
    }

    /**
     * Click Page number by text
     *
     * @param pageNumberText string
     */
    public void clickPageNumberByText(String pageNumberText) {
        assertPageLinkIsDisplayedByText(pageNumberText);
        String formattedLocator = String.format(this.pageNumberLocator, pageNumberText);
        WebElement webElement = driver.findElement(By.xpath(formattedLocator));
        elementControl.clickElement(webElement);

        assertCurrentPageNumber(pageNumberText);
    }
}
