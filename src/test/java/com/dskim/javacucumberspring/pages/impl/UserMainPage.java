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

// Страница "Главная" после авторизации
public class UserMainPage implements Page {
    // Логирование
    private Logger logger = LogManager.getLogger(UserMainPage.class);

    // URL
    private String url = "https://otus.ru/";

    // Список веб элементов
    private HashMap<String, WebElement> elements = new HashMap<>();
    private HashMap<String, List<WebElement>> elementLists = new HashMap<>();

    // Menu пользователя, с ссылкой для перехода в "Личный кабинет"
    @FindBy(xpath = ".//div[@class='header2-menu__item header2-menu__item_small header2-menu__item_dropdown header2-menu__item_dropdown_no-border']")
    private WebElement menuUser;

    // Link "Личный кабинет"
    @FindBy(xpath = ".//a[contains(text(), 'Личный кабинет')]")
    private WebElement linkPrivate;

    public UserMainPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        elements.put("Меню пользователя", menuUser);
        elements.put("Личный кабинет", linkPrivate);
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