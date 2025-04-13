package pageFiles;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import utils.ConfigReader;

import org.openqa.selenium.WebElement;

public class LoginPage {
	
	private WebDriver driver;
	String username = ConfigReader.getProperty("validUsername");
    String password = ConfigReader.getProperty("validPassword");
    String successMessage = ConfigReader.getProperty("success.message");
	String usernameInvalidMsg = ConfigReader.getProperty("username.invalid.message");
	String passwordInvalidMsg = ConfigReader.getProperty("password.invalid.message");

	
	private By usernameXpath = By.xpath("//input[@id='username']");
	private By passwordXpath = By.xpath("//input[@id='password']");
	private By loginButtonXpath = By.xpath("//i[@class='fa fa-2x fa-sign-in']");
	private By flashMessageId = By.id("flash");
	
	public LoginPage(WebDriver driver) {
        this.driver = driver;
    }
	
	public void loginToHerokuapp(String username, String password) {
		driver.findElement(usernameXpath).clear();
		driver.findElement(usernameXpath).sendKeys(username);
		driver.findElement(passwordXpath).clear();
		driver.findElement(passwordXpath).sendKeys(password);
		driver.findElement(loginButtonXpath).click();
	}
	
	
	public void assertFlashMessage(String expectedMessage) {
		WebElement flashMessage = driver.findElement(flashMessageId);
		String actualMessage = flashMessage.getText();
		Assert.assertTrue(actualMessage.contains(expectedMessage),
				"Expected: " + expectedMessage + " | But found: " + actualMessage);
	}

}
