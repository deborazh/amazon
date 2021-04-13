package commonLibs.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class ScreenshotUtils {

    private TakesScreenshot camera;

    public ScreenshotUtils(WebDriver driver) {
        camera = (TakesScreenshot) driver;
    }

    public void captureAndSaveScreenshots(String fileName) {
        File imgFile, tmpFile;

        imgFile = new File(fileName.trim());

        if (imgFile.exists()) {
            System.out.println("imgFile already exists");
        }

        tmpFile = camera.getScreenshotAs(OutputType.FILE);

        try {
            FileUtils.moveFile(tmpFile, imgFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
