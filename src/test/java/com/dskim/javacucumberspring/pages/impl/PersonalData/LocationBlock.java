package com.dskim.javacucumberspring.pages.impl.PersonalData;

import com.dskim.javacucumberspring.pages.model.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.HashMap;
import java.util.List;

// Блок "Основная информация"
public class LocationBlock  implements Page {
    // Логирование
    // private Logger logger = LogManager.getLogger(LocationBlock.class);

    // URL
    private String url = "https://otus.ru/lk/biography/personal/";

    // Список веб элементов
    private HashMap<String, WebElement> elements = new HashMap<>();
    private HashMap<String, List<WebElement>> elementLists = new HashMap<>();

    // Текущее значение в DropDownList "Страна"
    @FindBy(xpath = ".//p[contains(text(), 'Страна')]/../following-sibling::div//label/div")
    private WebElement selCountryItemCurrent;

    // Текущее значение в DropDownList "Город"
    @FindBy(xpath = ".//p[contains(text(), 'Город')]/../following-sibling::div//label/div")
    private WebElement selCityItemCurrent;

    // Дефолтное значение в DropDownList "Город"
    @FindBy(xpath = ".//p[contains(text(), 'Город')]/../following-sibling::div//label/div/span")
    private WebElement selCityItemDefault;

    // RadioButton "Готовность к переезду" - "Нет"
    @FindBy(xpath = ".//input[@id='id_ready_to_relocate_0']/following-sibling::span")
    private WebElement rbReadyToRelocate0;

    // RadioButton "Готовность к переезду" - "Нет", значение
    @FindBy(xpath = ".//input[@id='id_ready_to_relocate_0']")
    private WebElement rbReadyToRelocate0Input;

    // RadioButton "Готовность к переезду" - "Да"
    @FindBy(xpath = ".//input[@id='id_ready_to_relocate_1']/following-sibling::span")
    private WebElement rbReadyToRelocate1;

    // RadioButton "Готовность к переезду" - "Да", значение
    @FindBy(xpath = ".//input[@id='id_ready_to_relocate_1']")
    private WebElement rbReadyToRelocate1Input;

    public LocationBlock(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        elements.put("Текущее значение в поле Страна", selCountryItemCurrent);
        elements.put("Текущее значение в поле Город", selCityItemCurrent);
        elements.put("Дефолтное значение в поле Город", selCityItemDefault);
        elements.put("Готовность к переезду - Нет", rbReadyToRelocate0);
        elements.put("Готовность к переезду - Нет, значение", rbReadyToRelocate0Input);
        elements.put("Готовность к переезду - Да", rbReadyToRelocate1);
        elements.put("Готовность к переезду - Да, значение", rbReadyToRelocate1Input);
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