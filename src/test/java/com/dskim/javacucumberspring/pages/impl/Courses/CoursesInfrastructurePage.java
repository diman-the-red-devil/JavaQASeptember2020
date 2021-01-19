package com.dskim.javacucumberspring.pages.impl.Courses;

import com.dskim.javacucumberspring.pages.model.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.HashMap;
import java.util.List;

// Страница "Курсы -> Инфраструктура"
public class CoursesInfrastructurePage implements Page {
    // Логирование
    // private Logger logger = LogManager.getLogger(StartPage.class);

    // URL
    private String url = "https://otus.ru/categories/operations/";

    // Список веб элементов
    private HashMap<String, WebElement> elements = new HashMap<>();
    private HashMap<String, List<WebElement>> elementLists = new HashMap<>();

    // Text "Инфраструктура"
    @FindBy(xpath = ".//h1[contains(text(), 'Инфраструктура')]")
    private WebElement txtInfrastructure;

    // Card "Курсы"
    @FindBy(xpath = ".//div[class='lessons']/a")
    private List<WebElement> cardCourses;

    public CoursesInfrastructurePage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        elements.put("Инфраструктура", txtInfrastructure);
        elementLists.put("Курсы по инфраструктуре", cardCourses);
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
