package com.dskim.javacucumberspring.pages.model;

import org.openqa.selenium.WebDriver;

public class WebDriverHolder {
    private static WebDriver driver;

    public static void setDriver(WebDriver webDriver) {
        driver = webDriver;
    }

    public static WebDriver getDriver() {
        return driver;
    }
}
