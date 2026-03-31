package framework.pages;

import framework.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends BasePage {

    @FindBy(className = "cart_item")
    List<WebElement> cartItems;

    @FindBy(xpath = "//button[text()='Remove']")
    List<WebElement> removeBtns;

    @FindBy(id = "checkout")
    WebElement checkoutBtn;

    @FindBy(className = "inventory_item_name")
    List<WebElement> itemNames;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public int getItemCount() {
        if (cartItems == null || cartItems.isEmpty())
            return 0;
        return cartItems.size();
    }

    public CartPage removeFirstItem() {
        if (!removeBtns.isEmpty()) {
            waitAndClick(removeBtns.get(0));
        }
        return this;
    }

    public CartPage goToCheckout() {
        waitAndClick(checkoutBtn);
        return this;
    }

    public List<String> getItemNames() {
        List<String> names = new ArrayList<>();
        for (WebElement e : itemNames) {
            names.add(e.getText());
        }
        return names;
    }
}