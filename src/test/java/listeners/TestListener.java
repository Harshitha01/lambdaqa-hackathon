package listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;
import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.MediaEntityBuilder;

import utils.ScreenshotUtil;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        // Get the WebDriver instance (from test context or elsewhere)
        WebDriver driver = (WebDriver) result.getTestContext().getAttribute("driver");

        try {
            // Capture screenshot
            String screenshotPath = ScreenshotUtil.takeScreenshot(driver, result.getMethod().getMethodName());
            System.out.println("Screenshot saved at: " + screenshotPath);
            
            // Attach screenshot to the report (using ExtentReports or similar)
            MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build();
            
            } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestStart(ITestResult result) {
        // Print test name and thread ID (optional)
        System.out.println("Running test: " + result.getMethod().getMethodName() + " on Thread: " + Thread.currentThread().getId());
    }


}