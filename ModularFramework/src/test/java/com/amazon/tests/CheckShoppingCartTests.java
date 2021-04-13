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

        // Click on first product from product list
        productResultsPage.clickOnFirstProductFromResult();

        // Get price, add product to card with quantity
        if (product.getPrice() == null) {
            // Click on "See All Buying Choices" button
            productPage.clickSeeAllBuyingChoicesButton();
            // Set price
            product.setPrice(productPage.getPriceFromProductList());
            // Add product from Offer List to Card
            productPage.addProductFromOfferListToCart();
            // Navigate to Cart page
            productPage.navigateToCart();
            // Add product quantity
            cartPage.addProductQuantity(productQuantity);
        } else {
            // Set quantity/change quantity
            product.setQuantity(productPage.addProductQuantity(productQuantity));
            // Add product to card from Product Details page
            productPage.addProductToCardFromProductDetailsPage();
            // Navigate to Card page
            productPage.navigateToCart();
        }

        // Assert page title
        cartPage.assertPageTitle();
        // Assert product title, price and quantity
        cartPage.verifyProductDetails(product);
    }
}
