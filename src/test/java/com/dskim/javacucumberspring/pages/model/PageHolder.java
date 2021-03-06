package com.dskim.javacucumberspring.pages.model;

import com.dskim.javacucumberspring.pages.impl.Courses.CoursesDataSciencePage;
import com.dskim.javacucumberspring.pages.impl.Courses.CoursesInfrastructurePage;
import com.dskim.javacucumberspring.pages.impl.Courses.CoursesProgrammingPage;
import com.dskim.javacucumberspring.pages.impl.Courses.CoursesSecurityPage;
import com.dskim.javacucumberspring.pages.impl.*;
import com.dskim.javacucumberspring.pages.impl.PersonalData.*;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;

public class PageHolder {
    protected static HashMap<String, Page> pages;

    public static void initPages(WebDriver webDriver) {
        pages = new HashMap<>();
        pages.put("StartPage", new StartPage(webDriver));
        pages.put("LoginPage", new LoginPage(webDriver));
        pages.put("UserMainPage", new UserMainPage(webDriver));
        pages.put("LearningPage", new LearningPage(webDriver));
        pages.put("PersonalDataPage", new PersonalDataPage(webDriver));
        pages.put("NameDateBlock", new NameDateBlock(webDriver));
        pages.put("LocationBlock", new LocationBlock(webDriver));
        pages.put("WorkFormatBlock", new WorkFormatBlock(webDriver));
        pages.put("ContactsBlock", new ContactsBlock(webDriver));
        pages.put("CoursesProgrammingPage", new CoursesProgrammingPage(webDriver));
        pages.put("CoursesInfrastructurePage", new CoursesInfrastructurePage(webDriver));
        pages.put("CoursesSecurityPage", new CoursesSecurityPage(webDriver));
        pages.put("CoursesDataSciencePage", new CoursesDataSciencePage(webDriver));
        pages.put("TeachersPage", new TeachersPage(webDriver));
    }

    public static Page getPage(String name) {
        return pages.get(name);
    }
}
