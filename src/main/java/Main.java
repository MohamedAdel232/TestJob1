import Factories.DriverFactory;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Main {

    @BeforeMethod
    @Parameters("browser")
    public void beforeMethod(@Optional("chrome") String browser) throws IOException {
        Properties properties = new Properties();
        properties.load(new FileReader("environment.properties"));
        String executionType = properties.get("executionType").toString();

        DriverFactory.setupDriver(executionType, browser);
        DriverFactory.getDriver().get("https://www.saucedemo.com/");
    }

    @AfterMethod
    public void afterMethod() {
        DriverFactory.getDriver().quit();
    }

    @Test
    public void testcase1() throws InterruptedException {
        String title = DriverFactory.getDriver().getTitle();
        Assert.assertEquals(title, "Swag Labs");
//        Thread.sleep(30000);
    }

    @Test
    public void testcase2() {
        DriverFactory.getDriver().findElement(By.id("user-name")).sendKeys("standard_user");
        DriverFactory.getDriver().findElement(By.id("password")).sendKeys("secret_sauce");
        DriverFactory.getDriver().findElement(By.id("login-button")).click();
        String url = DriverFactory.getDriver().getCurrentUrl();
        Assert.assertEquals(url, "https://www.saucedemo.com/inventory.html");
    }
}
