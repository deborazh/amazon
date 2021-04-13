package com.amazon.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import java.io.IOException;

public class ReportUtils {

   ExtentHtmlReporter htmlReport;
   ExtentReports extentReports;
   ExtentTest extentTest;

   public ReportUtils(String htmlReportFileName) {
      htmlReportFileName = htmlReportFileName.trim();

      htmlReport = new ExtentHtmlReporter(htmlReportFileName.trim());
      extentReports = new ExtentReports();
      extentReports.attachReporter(htmlReport);
   }

   public void createATestCase(String testCaseName) {
      extentTest = extentReports.createTest(testCaseName);
   }

   public void addTestLog(Status status, String comment) {
      extentTest.log(status, comment);
   }

   public void attachScreenshotToReport(String fileName)  {
      try {
         extentTest.addScreenCaptureFromPath(fileName);
      } catch (IOException e) {
         System.out.println(e.getMessage());
         e.printStackTrace();
      }
   }

   public void flushReport() {
      extentReports.flush();;
   }
}
