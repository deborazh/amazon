package com.amazon.pages;

import com.amazon.dto.Product;
import com.amazon.utils.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class CartPage extends BasePage {

    private String pageTitle = "Shopping Cart";

    @FindBy(css = "select[name='quantity']")
    private WebElement quantitySelect;

    @FindBy(css = "h1")
    private WebElement pageTitleLocator;

    @FindBy(css = ".sc-list-item-content span>a.sc-product-link")
    private WebElement productName;

    @FindBy(css = ".sc-product-price")
    private WebElement productPrice;

    @FindBy(css = "#sc-subtotal-label-activecart")
    private WebElement subtotalQuantity;

    @FindBy(css = "#sc-subtotal-amount-activecart")
    private WebElement subtotalValue;

    public CartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * Add Product Quantity
     *
     * @param quantity int
     */
    public void addProductQuantity(int quantity){
        Select select = new Select(quantitySelect);
        select.selectByVisibleText(String.valueOf(quantity));
        WaitUtils.untilClickable(driver, quantitySelect);
    }

    /**
     * Get selected item test from Quantity dropdown
     *
     * @return string
     */
    public String getSelectedItemText() {
        Select select = new Select(quantitySelect);
        return select.getFirstSelectedOption().getText();
    }

    /**
     * Assert Page title
     */
    public void assertPageTitle() {
        String actualText = elementControl.getTextFromElement(pageTitleLocator).trim();
        Assert.assertEquals(actualText, pageTitle);
    }

    /**
     * Verify Product details - Product title, Product price, Product quantity
     *
     * @param product Product
     */
    public void verifyProductDetails(Product product) {
        String actualText = elementControl.getTextFromElement(productName).trim();
        // Check product name
        Assert.assertTrue(assertProductTitle(actualText, product.getTitle()));

        // Check price
        Assert.assertEquals(elementControl.getFormattedPrice(productPrice), product.getPrice());

        // Check quantity
        Assert.assertEquals(getSelectedItemText(), String.valueOf(product.getQuantity()));
        String quantityFromSubtotal = elementControl.getFormattedPrice(subtotalQuantity).replaceAll("[A-z() :]+", "");
        Assert.assertEquals(quantityFromSubtotal, String.valueOf(product.getQuantity()));

        // Check subtotal
        String subtotal = elementControl.getFormattedPrice(subtotalValue);
        int productQuantity = product.getQuantity();
        double productPrice = Double.parseDouble(product.getPrice());
        double expectedSubtotal = productPrice * productQuantity;
        Assert.assertEquals(subtotal, String.valueOf(expectedSubtotal));
    }

    /**
     * Assert Product title on Card page
     *
     * @param actualProductTitle string
     * @param expectedProductTitle string
     * @return boolean
     */
    public boolean assertProductTitle(String actualProductTitle, String expectedProductTitle) {
        return actualProductTitle.contains(expectedProductTitle);
    }
}
