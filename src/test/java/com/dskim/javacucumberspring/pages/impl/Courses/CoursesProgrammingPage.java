package com.dskim.javacucumberspring.pages.impl.Courses;

import com.dskim.javacucumberspring.pages.model.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.HashMap;
import java.util.List;

// Страница "Курсы -> Программирование"
public class CoursesProgrammingPage implements Page {
    // Логирование
    // private Logger logger = LogManager.getLogger(StartPage.class);

    // URL
    private String url = "https://otus.ru/categories/programming/";

    // Список веб элементов
    private HashMap<String, WebElement> elements = new HashMap<>();
    private HashMap<String, List<WebElement>> elementLists = new HashMap<>();

    // Text "Программирование"
    @FindBy(xpath = ".//h1[contains(text(), 'Программирование')]")
    private WebElement txtProgramming;

    // Card "Курсы"
    @FindBy(xpath = ".//div[class='lessons']/a")
    private List<WebElement> cardCourses;

    public CoursesProgrammingPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        elements.put("Программирование", txtProgramming);
        elementLists.put("Курсы по программированию", cardCourses);
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
