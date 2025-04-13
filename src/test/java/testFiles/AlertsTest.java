package testFiles;

import baseFile.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageFiles.AlertsPage;
import utils.ConfigReader;
import utils.ExtentManager;

public class AlertsTest extends BaseTest {
    AlertsPage alertsPage;

    @BeforeMethod
    public void navigateToAlertsPage() {
        driver.get(ConfigReader.getProperty("baseURL") + ConfigReader.getProperty("alerts.path"));
        alertsPage = new AlertsPage(driver);
    }

    @Test(retryAnalyzer = utils.RetryAnalyzer.class)
    public void testJSAlert() {

        alertsPage.triggerJSAlert();
        try {
            Assert.assertEquals(alertsPage.getResultText(), "You successfully clicked an alert");
            ExtentManager.getTest().pass("JS Alert triggered successfully.");
        } catch (AssertionError e) {
        	ExtentManager.getTest().fail("Test failed: " + e.getMessage());
        }
    }

    @Test(retryAnalyzer = utils.RetryAnalyzer.class)
    public void testJSConfirmAccept() {
        alertsPage.triggerJSConfirm(true);
        try {
            Assert.assertEquals(alertsPage.getResultText(), "You clicked: Ok");
            ExtentManager.getTest().pass("JS Confirm Accept worked correctly.");
        } catch (AssertionError e) {
        	ExtentManager.getTest().fail("Test failed: " + e.getMessage());

        }
    }

    @Test(retryAnalyzer = utils.RetryAnalyzer.class)
    public void testJSConfirmDismiss() {
        alertsPage.triggerJSConfirm(false);
        try {
            Assert.assertEquals(alertsPage.getResultText(), "You clicked: Cancel");
            ExtentManager.getTest().pass("JS Confirm Dismiss worked correctly.");
        } catch (AssertionError e) {
        	ExtentManager.getTest().fail("Test failed: " + e.getMessage());
        }
    }

    @Test(retryAnalyzer = utils.RetryAnalyzer.class)
    public void testJSPromptAccept() {
        String input = "Hello Prompt!";
        alertsPage.triggerJSPrompt(input, true);
        try {
            Assert.assertEquals(alertsPage.getResultText(), "You entered: " + input);
            ExtentManager.getTest().pass("JS Prompt Accept worked correctly.");
        } catch (AssertionError e) {
        	ExtentManager.getTest().fail("Test failed: " + e.getMessage());
        }
    }

    @Test(retryAnalyzer = utils.RetryAnalyzer.class)
    public void testJSPromptWithEmptyInputAndOk() {
        alertsPage.triggerJSPrompt("", true); // Enter empty, then click OK
        try {
            Assert.assertEquals(alertsPage.getResultText(), "You entered:");
            ExtentManager.getTest().pass("JS Prompt with empty input worked correctly.");
        } catch (AssertionError e) {
        	ExtentManager.getTest().fail("Test failed: " + e.getMessage());
        }
    }
    
    @Test(retryAnalyzer = utils.RetryAnalyzer.class)
    public void testJSPromptWithSomeTextAndCancel() {
        alertsPage.triggerJSPrompt("SomeText", false); // Enter text, then click Cancel
        try {
            Assert.assertEquals(alertsPage.getResultText(), "You entered: null");
            ExtentManager.getTest().pass("JS Prompt with some text and Cancel worked correctly.");
        } catch (AssertionError e) {
        	ExtentManager.getTest().fail("Test failed: " + e.getMessage());
        }
    }


}
