package com.dskim.javacucumberspring.browsers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class FirefoxBrowser {
    public WebDriver getDriver() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        options.setAcceptInsecureCerts(false);
        options.setCapability("unexpectedAlertBehaviour", "dismiss");
        options.setCapability("loadStrategy", PageLoadStrategy.NORMAL);
        return new FirefoxDriver(options);
    }
}
