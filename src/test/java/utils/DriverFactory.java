package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.ConfigReader;
import java.net.URL;
import java.util.HashMap;

public class DriverFactory {
	private static WebDriver driver;

	@SuppressWarnings("serial")
	public static WebDriver getDriver() {

		if (driver == null) {
			 String runMode = "lamdatest"; // Temporarily hardcoded
			 String browser = "chrome"; // Temporarily hardcoded

			//String runMode = ConfigReader.getProperty("runMode").toLowerCase();
			//String browser = ConfigReader.getProperty("browser").toLowerCase();
			System.out.println("runMode from configReader: " + runMode);
			System.out.println("browser from configReader: " + browser);


			try {
				if (runMode.equals("lambdatest")) {
					String username = ConfigReader.getProperty("LT_USERNAME");
					String accessKey = ConfigReader.getProperty("LT_ACCESS_KEY");
					System.out.println("LT_USERNAME: " + username);
					System.out.println("LT_ACCESS_KEY (first 5 chars): " + accessKey.substring(0, 5) + "...");



					String gridURL = "https://" + username + ":" + accessKey + "@hub.lambdatest.com/wd/hub";

					// LambdaTest Configuration
					DesiredCapabilities capabilities = new DesiredCapabilities();

					HashMap<String, Object> ltOptions = new HashMap<>();
					ltOptions.put("user", username); // ✅ use from env
					ltOptions.put("accessKey", accessKey); // ✅ use from env
					ltOptions.put("build", "Heroku Test Suite - Java");
					ltOptions.put("name", "Heroku Login Test");
					ltOptions.put("platformName", "Windows 10"); // Use 'platformName' not 'platform'
					ltOptions.put("selenium_version", "4.18.1");

					// These are required for LambdaTest
					capabilities.setCapability("LT:Options", ltOptions);
					capabilities.setCapability("browserName", "Chrome");
					capabilities.setCapability("browserVersion", "135"); // Replace with actual version if needed

					driver = new RemoteWebDriver(new URL(gridURL), capabilities);
				} else {
					// Local browser setup as before
					switch (browser) {
					case "chrome":
						ChromeOptions chromeOptions = new ChromeOptions();
						chromeOptions.setExperimentalOption("prefs", new HashMap<String, Object>() {
							{
								put("credentials_enable_service", false);
								put("profile.password_manager_enabled", false);
							}
						});
						chromeOptions.addArguments("--disable-save-password-bubble");
						chromeOptions.addArguments("--disable-notifications");
						chromeOptions.addArguments("disable-infobars");
						chromeOptions.addArguments("--disable-popup-blocking");
						chromeOptions.addArguments("--disable-extensions");
						chromeOptions.addArguments("--incognito");
						chromeOptions.addArguments("--no-default-browser-check");
						chromeOptions.addArguments("--no-first-run");
						chromeOptions.addArguments("--disable-blink-features=AutomationControlled");

						driver = new ChromeDriver(chromeOptions);
						break;

					case "firefox":
						FirefoxOptions firefoxOptions = new FirefoxOptions();
						firefoxOptions.addPreference("dom.webnotifications.enabled", false); // Disable notifications
						firefoxOptions.addArguments("-private"); // Run in private browsing mode
						driver = new FirefoxDriver(firefoxOptions);
						break;

					case "edge":
						EdgeOptions edgeOptions = new EdgeOptions();
						edgeOptions.addArguments("--disable-notifications");
						edgeOptions.addArguments("--disable-extensions");
						edgeOptions.addArguments("--incognito");
						edgeOptions.addArguments("--disable-popup-blocking");
						driver = new EdgeDriver(edgeOptions);
						break;

					default:
						throw new RuntimeException("❌ Invalid browser name in config: " + browser);
					}
				}

				driver.manage().window().maximize();
				if (driver == null) {
				    System.out.println("❌ Driver is null after initialization!");
				} else {
				    System.out.println("✅ Driver initialized: " + driver);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return driver;
	}

	public static void quitDriver() {
		if (driver != null) {
			driver.quit();
			driver = null;
		}
	}
}
