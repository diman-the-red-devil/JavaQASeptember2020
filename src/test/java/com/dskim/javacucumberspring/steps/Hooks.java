package com.dskim.javacucumberspring.steps;


import com.dskim.javacucumberspring.browsers.WebDriverFactory;
import com.dskim.javacucumberspring.pages.model.PageHolder;
import com.dskim.javacucumberspring.pages.model.WaitFor;
import com.dskim.javacucumberspring.pages.model.WebDriverHolder;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class Hooks {
    private Logger logger = LogManager.getLogger(Hooks.class);

    @Before(order = 10)
    public void startWebDriver() {
        // startRemote();
        startLocal();
    }

    public void startLocal() {
        String env = System.getProperty("browser", "chrome");
        logger.info("env = " + env);
        WebDriverHolder.setDriver(WebDriverFactory.create(env));
        WebDriverHolder.getDriver().manage().window().maximize();
        logger.info("Драйвер стартовал!");
        WaitFor.initWait(WebDriverHolder.getDriver(), 5, 1000);
        PageHolder.initPages(WebDriverHolder.getDriver());
    }

    public void startRemote() {
        try {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            //capabilities.setCapability("build", "Build");
            //capabilities.setCapability("name", "Test");
            //capabilities.setCapability("platform", "Windows 10");
            capabilities.setCapability("browserName", "chrome");
            WebDriver webDriver = new RemoteWebDriver(new URL("http://192.168.1.2:4444/wd/hub"), capabilities);
            WebDriverHolder.setDriver(webDriver);
            WebDriverHolder.getDriver().manage().window().maximize();
            logger.info("Драйвер стартовал!");
            WaitFor.initWait(WebDriverHolder.getDriver(), 10, 100);
            PageHolder.initPages(WebDriverHolder.getDriver());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @After
    public void stopWebDriver() {
        if(WebDriverHolder.getDriver() != null) {
            WebDriverHolder.getDriver().quit();
            logger.info("Драйвер остановлен!");
        }
    }
}
