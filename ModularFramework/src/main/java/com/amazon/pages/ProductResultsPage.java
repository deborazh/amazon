package com.amazon.pages;

import com.amazon.dto.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductResultsPage extends MainPage {

    @FindBy(css = ".sg-col-inner .s-main-slot > div[data-uuid]:first-of-type")
    private WebElement firstProduct;


    public ProductResultsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * Click on first product from product results list
     */
    public void clickOnFirstProductFromResult() {
        elementControl.clickElement(firstProduct);
    }

    /**
     * Get first Product from Product results list
     *
     * @return Product
     */
    public Product getFirstProductFromResultsList() {
        Product product = new Product(firstProduct
                .findElement(By.cssSelector("h2 a"))
                .getText().trim());
        String price = getPrice();
        if (price != null){
            product.setPrice(price);
        }
        return product;
    }

    /**
     * Get Price from Product results list
     *
     * @return string
     */
    private String getPrice() {
        String price = null;
        try {
            WebElement webElement = firstProduct
                    .findElement(By.cssSelector("span.a-price:not(.a-text-price)"));
            price = elementControl.getFormattedPrice(webElement);
        } catch (NoSuchElementException e) {
            System.out.println("Price not found");
        }
        return price;
    }
}
