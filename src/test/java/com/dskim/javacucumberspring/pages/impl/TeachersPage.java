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

// Страница "Преподаватели"
public class TeachersPage implements Page {
    // Логирование
    private Logger logger = LogManager.getLogger(StartPage.class);

    // URL
    private String url = "https://otus.ru/teacher/";

    // Список веб элементов
    private HashMap<String, WebElement> elements = new HashMap<>();
    private HashMap<String, List<WebElement>> elementLists = new HashMap<>();

    // Text "Преподаватели"
    @FindBy(xpath = ".//h1[contains(text(), 'Преподаватели')]")
    private WebElement txtTeachers;

    public TeachersPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        elements.put("Преподаватели", txtTeachers);
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
