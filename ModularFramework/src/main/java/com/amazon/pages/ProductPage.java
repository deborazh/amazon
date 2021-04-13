package com.amazon.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ProductPage extends MainPage {

    @FindBy(css = "#buybox-see-all-buying-choices")
    private WebElement seeAllBuyingChoicesButton;

    @FindBy(css = "#aod-offer:first-of-type span.a-price")
    private WebElement firstOfferPrice;

    @FindBy(css = "#aod-offer:first-of-type .a-button")
    private WebElement firstOfferAddToCartButton;

    @FindBy(css = "#quantity")
    private WebElement quantitySelect;

    @FindBy(css = "#add-to-cart-button")
    private WebElement addToCartButton;

    @FindBy(css = "#hlb-view-cart-announce")
    private WebElement cartButton;

    public ProductPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * Click on "See All Buying Choices" button
     */
    public void clickSeeAllBuyingChoicesButton() {
        elementControl.clickElement(seeAllBuyingChoicesButton);
    }

    /**
     * Get Price from Product list
     *
     * @return string
     */
    public String getPriceFromProductList() {
        return elementControl.getFormattedPrice(firstOfferPrice);
    }

    /**
     * Add product from Offer List to Cart
     */
    public void addProductFromOfferListToCart() {
        elementControl.clickElement(firstOfferAddToCartButton);
    }

    /**
     * Add product quantity
     *
     * @param quantity int
     * @return int
     */
    public int addProductQuantity(int quantity) {
        boolean isQualityDisplayed = elementControl.isPresented(quantitySelect);
        int newQty = quantity;

        if (isQualityDisplayed) {
            Select select = new Select(quantitySelect);
            if(select.getOptions().size() == 2) {
                newQty = 2;
            }
            select.selectByVisibleText(String.valueOf(newQty));
        } else {
            newQty = 1;
        }
        return newQty;
    }

    /**
     * Add product to card from Product Details page
     */
    public void addProductToCardFromProductDetailsPage() {
        elementControl.clickElement(addToCartButton);
    }

    /**
     * Navigate user to Cart page
     */
    public void navigateToCart() {
        elementControl.clickElement(cartButton);
    }
}
