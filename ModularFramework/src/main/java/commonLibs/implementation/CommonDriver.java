package commonLibs.implementation;

import enums.BrowserType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class CommonDriver {

    private WebDriver driver;
    private int pageLoadTimeout;
    private int elementDetectionTimeout;
    private String currentWorkingDirectory;

    public CommonDriver(BrowserType browserType){

        pageLoadTimeout = 10;
        elementDetectionTimeout = 10;
        currentWorkingDirectory = System.getProperty("user.dir");

        if (browserType == BrowserType.CHROME) {
            System.setProperty("webdriver.chrome.driver", currentWorkingDirectory + "/drivers/chromedriver.exe");
            driver = new ChromeDriver();
        }

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
    }

    public void navigateToURL(String url) {
        driver.manage().timeouts().pageLoadTimeout(pageLoadTimeout, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(elementDetectionTimeout, TimeUnit.SECONDS);

        driver.get(url);
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setPageLoadTimeout(int pageLoadTimeout) {
        this.pageLoadTimeout = pageLoadTimeout;
    }

    public void closeAllBrowsers() {
        driver.quit();
    }
}
