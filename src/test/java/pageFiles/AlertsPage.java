package pageFiles;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AlertsPage {
    WebDriver driver;

    private By jsAlertBtnXpath = By.xpath("//button[text()='Click for JS Alert']");
    private By jsConfirmBtnXpath = By.xpath("//button[text()='Click for JS Confirm']");
    private By jsPromptBtnXpath = By.xpath("//button[text()='Click for JS Prompt']");
    private By resultTextId = By.id("result");

    public AlertsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void triggerJSAlert() {
        driver.findElement(jsAlertBtnXpath).click();
        Alert alert = driver.switchTo().alert();
        alert.accept();  // Accept the JS Alert
    }

    public void triggerJSConfirm(boolean accept) {
        driver.findElement(jsConfirmBtnXpath).click();
        Alert alert = driver.switchTo().alert();
        if (accept) {
            alert.accept();  // Click OK
        } else {
            alert.dismiss(); // Click Cancel
        }
    }

    public void triggerJSPrompt(String inputText, boolean accept) {
        driver.findElement(jsPromptBtnXpath).click();
        Alert alert = driver.switchTo().alert();
        alert.sendKeys(inputText);
        if (accept) {
            alert.accept();
        } else {
            alert.dismiss();
        }
    }

    public String getResultText() {
        return driver.findElement(resultTextId).getText();
    }
}