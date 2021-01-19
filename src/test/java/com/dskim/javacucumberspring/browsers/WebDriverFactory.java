package com.dskim.javacucumberspring.browsers;

import org.openqa.selenium.WebDriver;

public class WebDriverFactory {
    public static WebDriver create(String name) {
        String browserName = name.replace("\'", "").toLowerCase();
        if (browserName.equals(Browser.CHROME.getBrowserName())) {
            ChromeBrowser chromeBrowser = new ChromeBrowser();
            return chromeBrowser.getDriver();
        } else if (browserName.equals(Browser.FIREFOX.getBrowserName())) {
            FirefoxBrowser firefoxBrowser = new FirefoxBrowser();
            return firefoxBrowser.getDriver();
        } else if (browserName.equals(Browser.IE.getBrowserName())) {
            ExplorerBrowser explorerBrowser = new ExplorerBrowser();
            return explorerBrowser.getDriver();
        } else
            throw new RuntimeException("Incorrect browser name");
    }
}