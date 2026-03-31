import framework.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestBai1 extends BaseTest {

    @Test
    public void testFail() {
        getDriver().get("https://google.com");

        Assert.assertEquals(1, 1);
    }
}