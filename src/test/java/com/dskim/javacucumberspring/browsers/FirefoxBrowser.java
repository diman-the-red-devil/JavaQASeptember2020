package com.dskim.javacucumberspring.browsers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class FirefoxBrowser {
    private static Logger logger = LogManager.getLogger(WebDriverFactory.class);

    @Bean
    @Profile("firefox")
    public WebDriver getDriver() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        options.setAcceptInsecureCerts(false);
        options.setCapability("unexpectedAlertBehaviour", "dismiss");
        options.setCapability("loadStrategy", PageLoadStrategy.NORMAL);
        return new FirefoxDriver(options);
    }
}
