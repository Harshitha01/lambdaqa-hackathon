package testFiles;

import baseFile.BaseTest;
import org.openqa.selenium.NoAlertPresentException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageFiles.LoginPage;
import utils.ConfigReader;
import utils.ExtentManager;

//import java.lang.reflect.Method;
//import org.testng.ITestContext;



public class LoginTest extends BaseTest {
	LoginPage loginPage;

	@BeforeMethod
    public void navigateToLoginPage() {
        driver.get(ConfigReader.getProperty("baseURL") + ConfigReader.getProperty("login.path"));
        loginPage = new LoginPage(driver);

    }

    @Test(retryAnalyzer = utils.RetryAnalyzer.class)
    public void testValidLogin() {
        
        ExtentManager.getTest().info("Starting test for valid login");

        loginPage.loginToHerokuapp(
        		ConfigReader.getProperty("validUsername"), 
        		ConfigReader.getProperty("validPassword")
        		);
        ExtentManager.getTest().info("Entered valid username and password");

        try {
            loginPage.assertFlashMessage(ConfigReader.getProperty("success.message"));
            ExtentManager.getTest().pass("Login success message verified");
        } catch (AssertionError e) {
        	ExtentManager.getTest().fail("Login success message did not match: " + e.getMessage());
            throw e;
        }

        try {
            driver.switchTo().alert().accept();
            ExtentManager.getTest().info("Alert accepted after login");
        } catch (NoAlertPresentException e) {
        	ExtentManager.getTest().info("No alert present after login");
        }
    }

    @Test(retryAnalyzer = utils.RetryAnalyzer.class)
    public void testInvalidUsernameOnly() {
        
        ExtentManager.getTest().info("Starting test with invalid username only");
        loginPage.loginToHerokuapp(
        	    ConfigReader.getProperty("invalidUsername"),
        	    ConfigReader.getProperty("validPassword")
        	);
        ExtentManager.getTest().info("Entered invalid username and valid password");

        try {
            loginPage.assertFlashMessage(ConfigReader.getProperty("username.invalid.message"));
            ExtentManager.getTest().pass("Invalid username message verified");
        } catch (AssertionError e) {
        	ExtentManager.getTest().fail("Username error message did not match: " + e.getMessage());
            throw e;
        }
    }

    @Test(retryAnalyzer = utils.RetryAnalyzer.class)
    public void testInvalidPasswordOnly() {
        
        ExtentManager.getTest().info("Starting test with invalid password only");

        loginPage.loginToHerokuapp(
        	    ConfigReader.getProperty("validUsername"),
        	    ConfigReader.getProperty("invalidPassword")
        	);

        ExtentManager.getTest().info("Entered valid username and invalid password");

        try {
            loginPage.assertFlashMessage(ConfigReader.getProperty("password.invalid.message"));
            ExtentManager.getTest().pass("Invalid password message verified");
        } catch (AssertionError e) {
        	ExtentManager.getTest().fail("Password error message did not match: " + e.getMessage());
            throw e;
        }
    }

    @Test(retryAnalyzer = utils.RetryAnalyzer.class)
    public void testBothInvalidUsernameAndPassword() {
        
        ExtentManager.getTest().info("Starting test with both invalid username and password");

        loginPage.loginToHerokuapp(
        	    ConfigReader.getProperty("invalidUsername"),
        	    ConfigReader.getProperty("invalidPassword")
        	);

        ExtentManager.getTest().info("Entered both invalid username and password");

        try {
            loginPage.assertFlashMessage(ConfigReader.getProperty("username.invalid.message"));
            ExtentManager.getTest().pass("Invalid credentials message verified");
        } catch (AssertionError e) {
        	ExtentManager.getTest().fail("Invalid login message did not match: " + e.getMessage());
            throw e;
        }
    }
}
