package commonLibs.implementation;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ElementControl {

    WebDriver driver;

    public ElementControl(WebDriver driver) {
        this.driver = driver;
    }

    public void clickElement(WebElement element) {
        element.click();
    }

    public void clear(WebElement element) {
        element.clear();
    }

    public void setText(WebElement element, String text) {
        element.sendKeys(text);
    }

    public boolean isEnabled(WebElement element) {
        return element.isEnabled();
    }

    public boolean isDisplayed(WebElement element) {
        return element.isDisplayed();
    }

    public boolean isPresented(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public String getTextFromElement(WebElement element) {
        return element.getText();
    }

    public void selectViaVisibleText(WebElement element, String text) {
        Select selDropdown = new Select(element);

        selDropdown.selectByVisibleText(text);
    }

    public void clickElementFromList(String locator) {
        List<WebElement> WebElementList = driver.findElements(By.xpath(locator));
        for (WebElement element : WebElementList) {
            element.click();
        }
    }

    public String getFormattedPrice(WebElement element) {
        return element.getText()
                .replace("$", "")
                .replaceAll("[$\\n]", ".").trim();
    }
}
