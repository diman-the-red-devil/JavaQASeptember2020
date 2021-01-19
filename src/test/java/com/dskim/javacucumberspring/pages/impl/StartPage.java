package com.dskim.javacucumberspring.pages.impl;

import com.dskim.javacucumberspring.pages.model.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.HashMap;
import java.util.List;

// Страница "Главная" до авторизации
public class StartPage implements Page {
    // Логирование
    // private Logger logger = LogManager.getLogger(StartPage.class);

    // URL
    private String url = "https://otus.ru/";

    // Список веб элементов
    private HashMap<String, WebElement> elements = new HashMap<>();
    private HashMap<String, List<WebElement>> elementLists = new HashMap<>();

    // Text "Авторские онлайн‑курсы для профессионалов"
    @FindBy(xpath = ".//div/div/h1[contains(text(), 'Авторские онлайн‑курсы')]")
    private WebElement txtAuthorCourses;

    // Menu "Курсы"
    @FindBy(xpath = ".//p[contains(text(), 'Курсы')]")
    private WebElement menuCourses;

    // Link "Программирование"
    @FindBy(xpath = ".//a[contains(text(), 'Программирование')]")
    private WebElement linkCoursesProgramming;

    // Link "Инфраструктура"
    @FindBy(xpath = ".//a[contains(text(), 'Инфраструктура')]")
    private WebElement linkCoursesInfrastructure;

    // Link "Информационная безопасность"
    @FindBy(xpath = ".//a[contains(text(), 'Информационная безопасность')]")
    private WebElement linkCoursesSecurity;

    // Link "Data Science"
    @FindBy(xpath = ".//a[contains(text(), 'Data Science')]")
    private WebElement linkCoursesDataScience;

    // Menu "Преподаватели"
    @FindBy(xpath = ".//p[contains(text(), 'Преподавателям')]")
    private WebElement menuTeachers;

    // Link "Наши преподаватели"
    @FindBy(xpath = ".//a[contains(text(), 'Наши преподаватели')]")
    private WebElement linkTeachers;

    // Button "Вход и регистрация"
    @FindBy(xpath = ".//button[@data-modal-id='new-log-reg']")
    private WebElement btnLoginReg;

    public StartPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        elements.put("Авторские онлайн‑курсы", txtAuthorCourses);
        elements.put("Курсы", menuCourses);
            elements.put("Программирование", linkCoursesProgramming);
            elements.put("Инфраструктура", linkCoursesInfrastructure);
            elements.put("Информационная безопасность", linkCoursesSecurity);
            elements.put("Data Science", linkCoursesDataScience);
        elements.put("Преподаватели", menuTeachers);
            elements.put("Наши преподаватели", linkTeachers);
        elements.put("Вход и регистрация", btnLoginReg);
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