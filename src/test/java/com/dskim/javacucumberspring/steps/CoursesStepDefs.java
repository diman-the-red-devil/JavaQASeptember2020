package com.dskim.javacucumberspring.steps;

import com.dskim.javacucumberspring.pages.model.PageHolder;
import com.dskim.javacucumberspring.pages.model.WaitFor;
import io.cucumber.java.ru.То;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class CoursesStepDefs {
    private Logger logger = LogManager.getLogger(CoursesStepDefs.class);

    @То("^Проверить: В карточке курса по (.*) отображается текст (.*)$")
    public void assertThatCardValueIs(String course, String value) {
        List<String> values = new ArrayList<>();
        List<WebElement> webElements = null;
        switch (course) {
            case "Программирование" :
                webElements = PageHolder.getPage("CoursesProgrammingPage").getElementLists().get("Курсы по программированию");
                break;
            case "Инфраструктура" :
                webElements = PageHolder.getPage("CoursesInfrastructurePage").getElementLists().get("Курсы по инфраструктуре");
                break;
            case "Информационная безопасность" :
                webElements = PageHolder.getPage("CoursesSecurityPage").getElementLists().get("Курсы по информационной безопасности");
                break;
            case "Data Science" :
                webElements = PageHolder.getPage("CoursesDataSciencePage").getElementLists().get("Курсы по Data Science");
                break;
        }

        for(WebElement webElement : webElements) {
            WebElement courseName = webElement.findElement(By.xpath("./div/div[@class='lessons__new-item-title lessons__new-item-title_with-bg js-ellipse']"));
            values.add(courseName.getText());
        }
        String expectedValue = value;
        for(int i = 0; i < values.size(); i++) {
            String actualValue = values.get(i);
            if(actualValue.contains(expectedValue)) {
                String errorMessage = "Fail! expectedValue: " + expectedValue + " != " + " actualValue: " + actualValue;
                Assert.assertEquals(errorMessage, expectedValue, expectedValue);
            }
        }
    }
}
