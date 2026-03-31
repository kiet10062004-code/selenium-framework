package framework.base;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import framework.factory.DriverFactory;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BaseTest {

    private ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public WebDriver getDriver() {
        return driver.get();
    }

    @Parameters({ "browser", "env" })
    @BeforeMethod
    public void setup(@Optional("chrome") String browser,
            @Optional("dev") String env) {

        WebDriver localDriver = DriverFactory.createDriver(browser);

        driver.set(localDriver);
        getDriver().manage().window().maximize();
        if (env.equals("dev")) {
            if (System.getenv("CI") != null) {
                getDriver().get("https://www.saucedemo.com/");
            } else {
                getDriver().get("http://localhost:8080");
            }
        }
    }

    @AfterMethod
    public void teardown(ITestResult result) {

        if (result.getStatus() == ITestResult.FAILURE) {
            takeScreenshot(result.getName());
        }

        if (getDriver() != null) {
            getDriver().quit();
            driver.remove();
        }
    }

    private void takeScreenshot(String testName) {
        TakesScreenshot ts = (TakesScreenshot) getDriver();
        File src = ts.getScreenshotAs(OutputType.FILE);

        String timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));

        String path = "target/screenshots/" + testName + "_" + timestamp + ".png";

        try {
            FileUtils.copyFile(src, new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}