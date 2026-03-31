package framework.pages;

import framework.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class InventoryPage extends BasePage {

    @FindBy(className = "inventory_list")
    WebElement inventoryList;

    @FindBy(className = "shopping_cart_badge")
    WebElement cartBadge;

    @FindBy(className = "inventory_item")
    List<WebElement> items;

    @FindBy(xpath = "//button[contains(text(),'Add to cart')]")
    List<WebElement> addButtons;

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoaded() {
        return inventoryList.isDisplayed();
    }

    public InventoryPage addFirstItemToCart() {
        waitAndClick(addButtons.get(0));
        return this;
    }

    public InventoryPage addItemByName(String name) {
        for (WebElement item : items) {
            if (item.getText().contains(name)) {
                WebElement btn = item.findElement(
                        org.openqa.selenium.By.tagName("button"));
                waitAndClick(btn);
                break;
            }
        }
        return this;
    }

    public int getCartItemCount() {
        try {
            return Integer.parseInt(cartBadge.getText());
        } catch (Exception e) {
            return 0;
        }
    }

    public CartPage goToCart() {
        cartBadge.click();
        return new CartPage(driver);
    }
}