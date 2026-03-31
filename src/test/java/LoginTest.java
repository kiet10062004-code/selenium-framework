import framework.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import framework.pages.LoginPage;

public class LoginTest extends BaseTest {

    @Test
    public void loginSuccess() {
        LoginPage login = new LoginPage(getDriver());
        Assert.assertTrue(
                login.login("standard_user", "secret_sauce")
                        .isLoaded());
    }

    @Test
    public void loginFail() {
        LoginPage login = new LoginPage(getDriver());
        login.loginExpectingFailure("wrong", "wrong");
        Assert.assertTrue(login.isErrorDisplayed());
    }

    @Test
    public void loginErrorMessage() {
        LoginPage login = new LoginPage(getDriver());
        login.loginExpectingFailure("wrong", "wrong");
        Assert.assertTrue(login.getErrorMessage().contains("Epic sadface"));
    }
}