package com.dskim.javacucumberspring.steps;

import com.dskim.javacucumberspring.pages.model.PageHolder;
import io.cucumber.java.ru.И;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebElement;
import java.util.List;

public class BiographyPersonalStepDefs {
    // private Logger logger = LogManager.getLogger(BiographyPersonalStepDefs.class);

    @И("Проверить: Контакт (.*) на странице (.*) отображается со значением (.*)$")
    public void assertThatFielsContactValueIs(String contactType, String pageName, String value) {
        WebElement contact = null;
        List<WebElement> webElements = PageHolder.getPage(pageName).getElementLists().get("Список контактов");
        for (WebElement webElement : webElements) {
            String contactTypeXpath = ".//div/label/div[contains(text(), '" + contactType + "')]/../../following-sibling::input[@value='" + value + "']";
            try {
                contact = webElement.findElement(By.xpath(contactTypeXpath));
            } catch (NotFoundException e) {
                // logger.info("Контакт типа " + contactType + " с значением " + value + " не найден");
                continue;
            }
        }
        boolean actualValue = contact != null;
        boolean expectedValue = true;
        String errorMessage = "Fail! expectedValue: " + expectedValue + " != " + " actualValue: " + actualValue;
        Assert.assertEquals(errorMessage, actualValue, expectedValue);
    }
}
