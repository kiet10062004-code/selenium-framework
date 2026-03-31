package framework.pages;

import framework.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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
        List<org.openqa.selenium.WebElement> buttons = driver.findElements(removeBtn);
        if (!buttons.isEmpty()) {
            buttons.get(0).click();
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
                .map(e -> e.getText())
                .collect(Collectors.toList());
    }
}