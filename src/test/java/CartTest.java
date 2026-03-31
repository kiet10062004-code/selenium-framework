import framework.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import framework.pages.CartPage;
import framework.pages.InventoryPage;
import framework.pages.LoginPage;

public class CartTest extends BaseTest {

    @Test
    public void addItemToCart() {
        int count = new LoginPage(getDriver())
                .login("standard_user", "secret_sauce")
                .addFirstItemToCart()
                .getCartItemCount();

        Assert.assertEquals(count, 1);
    }

    @Test
    public void removeItem() {
        LoginPage login = new LoginPage(getDriver());

        InventoryPage inventory = login.login("standard_user", "secret_sauce");

        inventory.addFirstItemToCart();

        CartPage cart = inventory.goToCart();

        cart.removeFirstItem();

        Assert.assertEquals(cart.getItemCount(), 0);
    }

    @Test
    public void cartEmpty() {
        LoginPage login = new LoginPage(getDriver());

        InventoryPage inventory = login.login("standard_user", "secret_sauce");

        Assert.assertTrue(inventory.isLoaded());

        inventory.addFirstItemToCart();

        CartPage cart = inventory.goToCart();

        Assert.assertEquals(cart.getItemCount(), 1);
    }

}
