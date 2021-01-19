package com.dskim.javacucumberspring.pages.impl.PersonalData;

import com.dskim.javacucumberspring.pages.model.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.HashMap;
import java.util.List;

// Страница "Персональные данные"
public class PersonalDataPage implements Page {
    // Логирование
    // private Logger logger = LogManager.getLogger(PersonalDataPage.class);

    // URL
    private String url = "https://otus.ru/lk/biography/personal/";

    // Список веб элементов
    private HashMap<String, WebElement> elements = new HashMap<>();
    private HashMap<String, List<WebElement>> elementLists = new HashMap<>();

    // Text "Персональные данные"
    @FindBy(xpath = ".//h3[contains(text(), 'Персональные данные')]")
    private WebElement txtPersonalData;

    // Button "Сохранить и продолжить"
    @FindBy(xpath = ".//button[contains(text(), 'Сохранить и продолжить')]")
    private WebElement btnSaveAndContinue;

    public PersonalDataPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        elements.put("Персональные данные", txtPersonalData);
        elements.put("Сохранить и продолжить", btnSaveAndContinue);
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