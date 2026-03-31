import framework.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestBai1 extends BaseTest {

    @Test
    public void testFail() {
        getDriver().get("https://google.com");

        // cố tình fail để chụp ảnh
        Assert.assertEquals(1, 2);
    }
}