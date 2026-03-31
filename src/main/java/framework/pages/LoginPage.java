package framework.pages;

import framework.base.BasePage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    private By username = By.id("user-name");
    private By password = By.id("password");
    private By loginBtn = By.id("login-button");
    @FindBy(css = "h3[data-test='error']")
    WebElement errorMsg;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public InventoryPage login(String user, String pass) {
        waitAndType(username, user);
        waitAndType(password, pass);
        waitAndClick(loginBtn);
        return new InventoryPage(driver);
    }

    public LoginPage loginExpectingFailure(String user, String pass) {
        waitAndType(username, user);
        waitAndType(password, pass);
        waitAndClick(loginBtn);
        return this;
    }

    private By errorMessage = By.cssSelector("h3[data-test='error']");

    public String getErrorMessage() {
        return getText(errorMessage);
    }

    public boolean isErrorDisplayed() {
        return errorMsg.isDisplayed();
    }
}
