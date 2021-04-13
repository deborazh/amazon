package commonLibs.implementation;

import com.amazon.utils.WaitUtils;
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

    /**
     * Click on WebElement
     *
     * @param element WebElement
     */
    public void clickElement(WebElement element) {
        element.click();
    }

    /**
     * Clear text from WebElement
     *
     * @param element WebElement
     */
    public void clear(WebElement element) {
        element.clear();
    }

    /**
     * Set text to WebElement
     *
     * @param element WebElement
     * @param text string
     */
    public void setText(WebElement element, String text) {
        element.sendKeys(text);
    }

    /**
     * Return boolean value if the element is enabled or not
     *
     * @param element WebElement
     * @return boolean
     */
    public boolean isEnabled(WebElement element) {
        return element.isEnabled();
    }

    /**
     * Return boolean value if the element is displayed or not
     *
     * @param element WebElement
     * @return boolean
     */
    public boolean isDisplayed(WebElement element) {
        return element.isDisplayed();
    }

    /**
     * Return boolean value if the element is presented or not
     *
     * @param element WebElement
     * @return boolean
     */
    public boolean isPresented(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    /**
     * Get text from WebElement element
     *
     * @param element WebElement
     * @return string
     */
    public String getTextFromElement(WebElement element) {
        WaitUtils.untilPresent(driver, element);
        return element.getText();
    }

    /**
     * Select by visible text
     *
     * @param element WebElement
     * @param text string
     */
    public void selectViaVisibleText(WebElement element, String text) {
        Select selDropdown = new Select(element);

        selDropdown.selectByVisibleText(text);
    }

    /**
     * Click WebElement from list by locator
     *
     * @param locator string
     */
    public void clickElementFromList(String locator) {
        List<WebElement> WebElementList = driver.findElements(By.xpath(locator));
        for (WebElement element : WebElementList) {
            element.click();
        }
    }

    /**
     * Get formated Price as a string from WebElement
     *
     * @param element WebElement
     * @return string
     */
    public String getFormattedPrice(WebElement element) {
        return element.getText()
                .replace("$", "")
                .replaceAll("[$\\n]", ".").trim();
    }
}
