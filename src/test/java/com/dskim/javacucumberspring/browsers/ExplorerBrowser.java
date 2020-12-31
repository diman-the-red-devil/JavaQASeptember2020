package com.dskim.javacucumberspring.browsers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class ExplorerBrowser {
    private static Logger logger = LogManager.getLogger(WebDriverFactory.class);

    @Bean
    @Profile("explorer")
    public WebDriver getDriver() {
        WebDriverManager.iedriver().setup();
        InternetExplorerOptions options = new InternetExplorerOptions();
        options.setCapability("unexpectedAlertBehaviour", "dismiss");
        options.setCapability("loadStrategy", PageLoadStrategy.NORMAL);
        return new InternetExplorerDriver(options);
    }
}
