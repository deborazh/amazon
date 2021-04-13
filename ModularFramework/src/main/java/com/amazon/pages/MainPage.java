package com.amazon.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends BasePage {

    @CacheLookup
    @FindBy(id="twotabsearchtextbox")
    private WebElement searchBar;

    @CacheLookup
    @FindBy(id="nav-search-submit-button")
    private WebElement searchButton;


    public MainPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void setTextOnSearchBar(String text) {
        elementControl.clear(searchBar);
        elementControl.setText(searchBar, text);
    }

    public void clickOnSearchButton() {
        elementControl.clickElement(searchButton);
    }

    public void searchForProduct(String productName) {
        setTextOnSearchBar(productName);
        clickOnSearchButton();
    }
}
