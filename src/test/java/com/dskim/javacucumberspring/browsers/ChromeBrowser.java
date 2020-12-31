package com.dskim.javacucumberspring.browsers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class ChromeBrowser {
    private static Logger logger = LogManager.getLogger(WebDriverFactory.class);

    public WebDriver getDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.setAcceptInsecureCerts(false);
        options.setCapability("unexpectedAlertBehaviour", "dismiss");
        options.setCapability("loadStrategy", PageLoadStrategy.NORMAL);
        options.setCapability(ChromeOptions.CAPABILITY, options);
        return new ChromeDriver(options);
    }
}
