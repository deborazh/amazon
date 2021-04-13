package com.amazon.tests;

import com.amazon.dto.Product;
import com.amazon.pages.CartPage;
import com.amazon.pages.ProductPage;
import com.amazon.pages.ProductResultsPage;
import com.amazon.utils.FilterUtils;
import com.aventstack.extentreports.Status;
import enums.amazon.Department;
import org.testng.annotations.Test;


public class CheckShoppingCartTests extends BaseTest {


    @Test
    public void verifyCartSubtotalWhenUserAddProductIntoIt() {
        reportUtils.createATestCase("Verify add products to cart functionality");
        reportUtils.addTestLog(Status.INFO, "Adding products to cart");

        FilterUtils filterUtils = new FilterUtils(driver);
        ProductResultsPage productResultsPage = new ProductResultsPage(driver);
        ProductPage productPage = new ProductPage(driver);
        CartPage cartPage = new CartPage(driver);


        String searchProduct = "Star Wars";
        int minPrice = 5;
        int maxPrice = 500;
        String brandName = "Lego";
        int pageNumber = 2;
        int productQuantity = 3;

        // Search for Product
        mainPage.searchForProduct(searchProduct);

        // Filter results by "Toys & Games"
        filterUtils.clickDepartmentFilterByText(Department.TOYS_AND_GAMES);

        // Set minPrice and maxPrice to filter the results
        filterUtils.setFilterByPrice(String.valueOf(minPrice), String.valueOf(maxPrice));

        // Filter results by brand - "LEGO"
        filterUtils.choseBrandByText(brandName);

        // Go to another page
        filterUtils.clickPageNumberByText(String.valueOf(pageNumber));

        // Get first product from list
        Product product = productResultsPage.getFirstProductFromResultsList();
        // Set quantity to ProductDTO
        product.setQuantity(productQuantity);

        // Get price, add product to card with quantity
        if (product.getPrice() == null) {
            productResultsPage.clickOnFirstProductFromResult();
            productPage.clickSeeAllBuyingChoicesButton();
            product.setPrice(productPage.getPriceFromProductList());
            productPage.addProductFromOfferListToCart();
            productPage.navigateToCart();
            cartPage.addProductQuantity(productQuantity);
        } else {
            productResultsPage.clickOnFirstProductFromResult();
            product.setQuantity(productPage.addProductQuantity(productQuantity));
            productPage.addProductToCardFromProductDetailsPage();
            productPage.navigateToCart();
        }
        cartPage.assertPageTitle();

        cartPage.verifyProductDetails(product);

    }

    @Test
    public void test100() {
        reportUtils.createATestCase("Verify add products to cart functionality2222");
        reportUtils.addTestLog(Status.INFO, "Adding products to cart22222");
        ProductPage productPage = new ProductPage(driver);
        driver.navigate().to("https://www.amazon.com/Princess-Summertime-Reindeer-Exclusive-LEGO/dp/B01MQL3AFL/ref=sr_1_25_sspa?dchild=1&keywords=Star+Wars&qid=1618255293&refinements=p_36%3A500-50000%2Cp_89%3ALEGO&rnid=2528832011&s=toys-and-games&sr=1-25-spons&psc=1&spLa=ZW5jcnlwdGVkUXVhbGlmaWVyPUExOTdQNkZKWFgxUFBQJmVuY3J5cHRlZElkPUEwNzc4MDkxVUY4N0VMTTZUVkRKJmVuY3J5cHRlZEFkSWQ9QTAzOTgxODUyUkgwQkQ0N1Q5Mzg4JndpZGdldE5hbWU9c3BfYXRmX25leHQmYWN0aW9uPWNsaWNrUmVkaXJlY3QmZG9Ob3RMb2dDbGljaz10cnVl");

        int productQuantity = 3;
        Product product = new Product("1Princess Lego Disney Castle Mini Sets Cinderella Fun Frozen Set + Lego Creator Seasonal Exclusive Set Lego Polybag Edition Building Set Elsa's Throne Winter");


        product.setQuantity(productPage.addProductQuantity(productQuantity));
        productPage.addProductToCardFromProductDetailsPage();
        productPage.navigateToCart();


        new CartPage(driver).verifyProductDetails(product);

    }
}
