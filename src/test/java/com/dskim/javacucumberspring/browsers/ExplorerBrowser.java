package com.dskim.javacucumberspring.browsers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;

public class ExplorerBrowser {
    public WebDriver getDriver() {
        WebDriverManager.iedriver().setup();
        InternetExplorerOptions options = new InternetExplorerOptions();
        options.setCapability("unexpectedAlertBehaviour", "dismiss");
        options.setCapability("loadStrategy", PageLoadStrategy.NORMAL);
        return new InternetExplorerDriver(options);
    }
}
