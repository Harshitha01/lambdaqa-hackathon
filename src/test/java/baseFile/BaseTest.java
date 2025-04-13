package baseFile;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import java.lang.reflect.Method;
import com.aventstack.extentreports.*;

import utils.ConfigReader;
import utils.DriverFactory;
import utils.ExtentManager;
import java.time.Duration;
import org.testng.ITestContext;

public class BaseTest {

    protected WebDriver driver;
    protected ExtentReports extent;
    protected ExtentTest test;

    @BeforeClass
    public void setup() {
        // Log runMode and browser to ensure they're being read correctly
        String runMode = ConfigReader.getProperty("runMode");
        String browser = ConfigReader.getProperty("browser");

        System.out.println("runMode from config: " + runMode);
        System.out.println("browser from config: " + browser);

        driver = DriverFactory.getDriver();  // Ensure this line is properly calling getDriver() to initialize WebDriver
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        ExtentManager.getExtentReports();
    }


    @BeforeMethod
    public void startTest(Method method, ITestContext context) {
    	test = ExtentManager.getExtentReports().createTest(method.getName());
        ExtentManager.setTest(test);
        System.out.println("Initialized ExtentTest for: " + method.getName());
        context.setAttribute("driver", driver);
    }
    
    @AfterMethod
    public void endTest() {
        // Ensure the test results are written to the report after each method
        ExtentManager.flush(); 
    }

	
	  @AfterClass 
	  public void tearDown() 
	  { if (driver != null) 
	  {
		  DriverFactory.quitDriver();  
		  } 
	  }
	   
	  }
	 



