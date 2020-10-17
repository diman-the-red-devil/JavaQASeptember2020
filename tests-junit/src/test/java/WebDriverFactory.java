import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class WebDriverFactory {
    private static Logger logger = LogManager.getLogger(WebDriverFactory.class);

    public static WebDriver create(String name) {
        String browserName = name.replace("\'","").toLowerCase();
        logger.info(browserName);
        if (browserName.equals(Browser.CHROME.getBrowserName())) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setAcceptInsecureCerts(false);
            caps.setCapability("unexpectedAlertBehaviour", "dismiss");
            caps.setCapability("loadStrategy", PageLoadStrategy.NORMAL);
            caps.setCapability(ChromeOptions.CAPABILITY, options);
            return new ChromeDriver(caps);
        }
        else
        if (browserName.equals(Browser.FIREFOX.getBrowserName())) {
            // Установить веб драйвер для Firefox в папку где лежит приложение
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();
            return new FirefoxDriver(options);
        }
        else
        if (browserName.equals(Browser.IE.getBrowserName())) {
            WebDriverManager.iedriver().setup();
            InternetExplorerOptions options = new InternetExplorerOptions();
            return new InternetExplorerDriver(options);
        }
        else
            throw new RuntimeException("Incorrect browser name");
    }
}