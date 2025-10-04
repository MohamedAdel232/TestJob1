package Factories;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;


public class DriverFactory {
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    public static void setupDriver(String executionType, String browserName) throws MalformedURLException {
        if (executionType.equalsIgnoreCase("Local")) {
            switch (browserName.toLowerCase()) {
                case "firefox":
                    DriverFactory.driverThreadLocal.set(new FirefoxDriver());
                    break;
                default:
                    DriverFactory.driverThreadLocal.set(new ChromeDriver());
            }
        } else if (executionType.equalsIgnoreCase("Grid")) {
            URL hub = new URL("http://192.168.192.1:4444/wd/hub");
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setPlatform(Platform.LINUX);

            switch (browserName.toLowerCase()) {
                case "firefox":
                    desiredCapabilities.setBrowserName("firefox");
                    driverThreadLocal.set(new RemoteWebDriver(hub, desiredCapabilities));
                    break;
                default:
                    desiredCapabilities.setBrowserName("chrome");
                    driverThreadLocal.set(new RemoteWebDriver(hub, desiredCapabilities));
            }
        }
    }

    public static WebDriver getDriver() {
        return driverThreadLocal.get();
    }
}
