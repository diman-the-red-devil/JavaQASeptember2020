package com.dskim.javacucumberspring.pages.impl.PersonalData;

import com.dskim.javacucumberspring.pages.model.Page;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.HashMap;
import java.util.List;

// Блок "ФИ и дата рождения"
public class NameDateBlock  implements Page {
    // Логирование
    private Logger logger = LogManager.getLogger(NameDateBlock.class);

    // URL
    private String url = "https://otus.ru/lk/biography/personal/";

    // Список веб элементов
    private HashMap<String, WebElement> elements = new HashMap<>();
    private HashMap<String, List<WebElement>> elementLists = new HashMap<>();

    // TextBox "Имя"
    @FindBy(xpath = ".//input[@name='fname']")
    private WebElement tbxFNameRu;

    // TextBox "Фамилия"
    @FindBy(xpath = ".//input[@name='lname']")
    private WebElement tbxLNameRu;

    // TextBox "Имя (латиницей)"
    @FindBy(xpath = ".//input[@name='fname_latin']")
    private WebElement tbxFNameEn;

    // TextBox "Фамилия (латиницей)"
    @FindBy(xpath = ".//input[@name='lname_latin']")
    private WebElement tbxLNameEn;

    // TextBox "Имя (в блоге)"
    @FindBy(xpath = ".//input[@name='blog_name']")
    private WebElement tbxBlogName;

    // TextBox "Дата рождения"
    @FindBy(xpath = ".//input[@name='date_of_birth']")
    private WebElement tbxDateOfBirth;

    public NameDateBlock(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        elements.put("Имя", tbxFNameRu);
        elements.put("Фамилия", tbxLNameRu);
        elements.put("Имя (латиницей)", tbxFNameEn);
        elements.put("Фамилия (латиницей)", tbxLNameEn);
        elements.put("Имя (в блоге)", tbxBlogName);
        elements.put("Дата рождения", tbxDateOfBirth);
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