package com.dskim.javacucumberspring.pages.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;

public interface Page {
    HashMap<String, WebElement> getElements();
    HashMap<String, List<WebElement>> getElementLists();
    String getUrl();
}
