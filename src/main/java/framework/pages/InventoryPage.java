package framework.pages;

import framework.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class InventoryPage extends BasePage {

    private By inventoryList = By.className("inventory_list");
    private By cartBadge = By.className("shopping_cart_badge");
    private By items = By.className("inventory_item");
    private By addButtons = By.xpath("//button[contains(text(),'Add to cart')]");
    private By cartIcon = By.className("shopping_cart_link");

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoaded() {
        return isElementVisible(inventoryList);
    }

    public InventoryPage addFirstItemToCart() {
        List<WebElement> buttons = driver.findElements(addButtons);

        if (!buttons.isEmpty()) {
            buttons.get(0).click();
        }

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(cartBadge, "1"));

        return this;
    }

    public InventoryPage addItemByName(String name) {
        List<WebElement> itemList = driver.findElements(items);

        for (WebElement item : itemList) {
            if (item.getText().contains(name)) {
                WebElement btn = item.findElement(By.tagName("button"));
                btn.click();
                break;
            }
        }
        return this;
    }

    public int getCartItemCount() {
        try {
            return Integer.parseInt(
                    driver.findElement(cartBadge).getText());
        } catch (Exception e) {
            return 0;
        }
    }

    public CartPage goToCart() {
        waitAndClick(cartIcon);
        return new CartPage(driver);
    }
}