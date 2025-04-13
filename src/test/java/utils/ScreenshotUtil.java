package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ScreenshotUtil {
    public static String takeScreenshot(WebDriver driver, String testName) {
	    File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	    String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fileName = testName + "_" + timestamp + ".png";
        String destPath = System.getProperty("user.dir") + "/screenshots/" + fileName;
	    try {
	        File destFile = new File(destPath);
	        FileUtils.copyFile(srcFile, destFile);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return destPath;
	}


}
