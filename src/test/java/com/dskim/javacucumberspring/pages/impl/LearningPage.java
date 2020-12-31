package com.dskim.javacucumberspring.pages.impl;

import com.dskim.javacucumberspring.pages.model.Page;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.HashMap;
import java.util.List;

// Страница "Личный кабинет"
public class LearningPage implements Page {
    // Логирование
    private Logger logger = LogManager.getLogger(LearningPage.class);

    // URL
    private String url = "https://otus.ru/learning/";

    // Список веб элементов
    private HashMap<String, WebElement> elements = new HashMap<>();
    private HashMap<String, List<WebElement>> elementLists = new HashMap<>();

    // Text "Личный кабинет"
    @FindBy(xpath = ".//h1[contains(text(), 'Личный кабинет')]")
    private WebElement txtPrivate;

    // Link "О себе"
    @FindBy(xpath = ".//a[contains(text(), 'О себе')]")
    private WebElement linkAboutMe;

    public LearningPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        elements.put("Личный кабинет", txtPrivate);
        elements.put("О себе", linkAboutMe);
    }

    // Получение URL страницы
    @Override
    public String getUrl() {
        return this.url;
    }

    // Получение списка веб элементов
    @Override
    public HashMap<String, WebElement> getElements() {
        return this.elements;
    }

    @Override
    public HashMap<String, List<WebElement>> getElementLists() {
        return this.elementLists;
    }
}