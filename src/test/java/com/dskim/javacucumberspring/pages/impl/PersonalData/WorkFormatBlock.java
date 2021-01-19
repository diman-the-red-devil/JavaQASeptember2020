package com.dskim.javacucumberspring.pages.impl.PersonalData;

import com.dskim.javacucumberspring.pages.model.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.HashMap;
import java.util.List;

// Блок "Формат работы"
public class WorkFormatBlock  implements Page {
    // Логирование
    // private Logger logger = LogManager.getLogger(WorkFormatBlock.class);

    // URL
    private String url = "https://otus.ru/lk/biography/personal/";

    // Список веб элементов
    private HashMap<String, WebElement> elements = new HashMap<>();
    private HashMap<String, List<WebElement>> elementLists = new HashMap<>();

    // CheckBox "Полный день"
    @FindBy(xpath = ".//input[@title='Полный день']/following-sibling::span")
    private WebElement cbxFull;

    // CheckBox "Полный день", значение
    @FindBy(xpath = ".//input[@title='Полный день']")
    private WebElement cbxFullInput;

    // CheckBox "Гибкий график"
    @FindBy(xpath = ".//input[@title='Гибкий график']/following-sibling::span")
    private WebElement cbxFlexible;

    // CheckBox "Гибкий график", значение
    @FindBy(xpath = ".//input[@title='Гибкий график']")
    private WebElement cbxFlexibleInput;

    // CheckBox "Удаленно"
    @FindBy(xpath = ".//input[@title='Удаленно']/following-sibling::span")
    private WebElement cbxRemote;

    // CheckBox "Удаленно", значение
    @FindBy(xpath = ".//input[@title='Удаленно']")
    private WebElement cbxRemoteInput;

    public WorkFormatBlock(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        elements.put("Полный день", cbxFull);
        elements.put("Полный день, значение", cbxFullInput);
        elements.put("Гибкий график", cbxFlexible);
        elements.put("Гибкий график, значение", cbxFlexibleInput);
        elements.put("Удаленно", cbxRemote);
        elements.put("Удаленно, значение", cbxRemoteInput);
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