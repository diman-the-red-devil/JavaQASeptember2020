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

// Блок "Контактная информация"
public class ContactsBlock  implements Page {
    // Логирование
    private Logger logger = LogManager.getLogger(ContactsBlock.class);

    // URL
    private String url = "https://otus.ru/lk/biography/personal/";

    // Список веб элементов
    private HashMap<String, WebElement> elements = new HashMap<>();
    private HashMap<String, List<WebElement>> elementLists = new HashMap<>();

    // Button "Добавить"
    @FindBy(xpath = ".//button[contains(text(), 'Добавить')]")
    private WebElement btnAddContact;

    // DropDownList "Способ связи"
    @FindBy(xpath = ".//span[contains(text(), 'Способ связи')]")
    private WebElement selConType;

    // Список способов связи
    @FindBy(xpath = ".//div[@class='lk-cv-block__select-options lk-cv-block__select-options_left js-custom-select-options-container']")
    private WebElement selConTypeList;

    // TextBox "Контакт"
    @FindBy(xpath = ".//div[@class='lk-cv-block__select-options lk-cv-block__select-options_left js-custom-select-options-container hide']/../following-sibling::input")
    private List<WebElement> tbxConTypeValues;

    // Список контактов
    @FindBy(xpath = ".//div[@class='container__row js-formset-row']")
    private List<WebElement> listOfContacts;

    public ContactsBlock(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        elements.put("Добавить", btnAddContact);
        elements.put("Способ связи", selConType);
        elements.put("Способы связи", selConTypeList);
        elementLists.put("Контакт", tbxConTypeValues);
        elementLists.put("Список контактов", listOfContacts);
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