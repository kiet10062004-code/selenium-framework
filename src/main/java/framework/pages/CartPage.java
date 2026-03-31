package framework.pages;

import framework.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class CartPage extends BasePage {

    private By cartItems = By.className("cart_item");
    private By removeBtn = By.cssSelector("button.cart_button");
    private By checkoutBtn = By.id("checkout");
    private By itemNames = By.className("inventory_item_name");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public int getItemCount() {
        return driver.findElements(cartItems).size();
    }

    public CartPage removeFirstItem() {
        List<WebElement> buttons = driver.findElements(removeBtn);

        if (!buttons.isEmpty()) {
            buttons.get(0).click();

            // ✅ WAIT sau khi remove để UI cập nhật
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.numberOfElementsToBe(cartItems, buttons.size() - 1));
        }

        return this;
    }

    public CartPage goToCheckout() {
        waitAndClick(checkoutBtn);
        return this;
    }

    public List<String> getItemNames() {
        return driver.findElements(itemNames)
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }
}