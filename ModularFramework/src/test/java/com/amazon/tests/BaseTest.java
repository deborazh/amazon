package com.amazon.tests;

import com.amazon.pages.MainPage;
import com.amazon.utils.ConfigUtils;
import com.amazon.utils.ReportUtils;
import com.aventstack.extentreports.Status;
import commonLibs.implementation.CommonDriver;
import commonLibs.utils.ScreenshotUtils;
import enums.BrowserType;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.util.Properties;

public class BaseTest {

    CommonDriver cmnDriver;
    String url;
    WebDriver driver;
    MainPage mainPage;
    String currentWorkingDirectory;
    Properties configProperty;
    String configFileName;
    String reportFileName;
    ReportUtils reportUtils;
    ScreenshotUtils screenshot;

    @BeforeSuite
    public void preSetup()  {
        currentWorkingDirectory = System.getProperty("user.dir");
        configFileName = currentWorkingDirectory + "/config/config.properties";
        reportFileName = currentWorkingDirectory + "/reports/amazonTestReport.html";
        configProperty = ConfigUtils.readProperties(configFileName);
        reportUtils = new ReportUtils(reportFileName);

    }

    @BeforeClass
    public void setUp()  {
        url = configProperty.getProperty("baseUrl");
        cmnDriver = new CommonDriver(BrowserType.CHROME);
        driver = cmnDriver.getDriver();
        mainPage = new MainPage(driver);
        screenshot = new ScreenshotUtils(driver);
        cmnDriver.navigateToURL(url);
    }

    @AfterMethod
    public void postTestAction(ITestResult result) {
        String testCaseName = result.getName();
        long executionTime = System.currentTimeMillis();
        String screenshotFileName = currentWorkingDirectory + "/screenshots/" + testCaseName + executionTime + ".jpeg";

        if (result.getStatus() == ITestResult.FAILURE) {
            reportUtils.addTestLog(Status.FAIL, "One or more steps failed");
            screenshot.captureAndSaveScreenshots(screenshotFileName);
            reportUtils.attachScreenshotToReport(screenshotFileName);
        }
    }

    @AfterClass
    public void tearDown() {
        cmnDriver.closeAllBrowsers();
    }

    @AfterSuite
    public void postTearDown() {
        reportUtils.flushReport();
    }
}
