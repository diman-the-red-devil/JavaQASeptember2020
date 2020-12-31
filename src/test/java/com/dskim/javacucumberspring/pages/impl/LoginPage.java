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

// Форма "Вход и регистрация"
public class LoginPage implements Page {
    // Логирование
    private Logger logger = LogManager.getLogger(LoginPage.class);

    // URL
    private String url = "https://otus.ru/";

    // Список веб элементов
    private HashMap<String, WebElement> elements = new HashMap<>();
    private HashMap<String, List<WebElement>> elementLists = new HashMap<>();

    // TextBox "Электронная почта"
    @FindBy(xpath = "//div[@class='new-log-reg__login']//input[@name='email']")
    private WebElement tbxEmail;

    // TextBox "Пароль"
    @FindBy(xpath = "//input[@name='password']")
    private WebElement tbxPassword;

    // Button "Войти"
    @FindBy(xpath = "//button[contains(text(),'Войти')]")
    private WebElement btnEnter;

    public LoginPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        elements.put("Электронная почта", tbxEmail);
        elements.put("Пароль", tbxPassword);
        elements.put("Войти", btnEnter);
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