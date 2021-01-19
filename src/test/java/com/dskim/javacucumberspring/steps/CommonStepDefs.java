package com.dskim.javacucumberspring.steps;

import com.dskim.javacucumberspring.browsers.WebDriverFactory;
import com.dskim.javacucumberspring.pages.model.PageHolder;
import com.dskim.javacucumberspring.pages.model.WaitFor;
import com.dskim.javacucumberspring.pages.model.WebDriverHolder;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.То;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;

public class CommonStepDefs {
    // private Logger logger = LogManager.getLogger(CommonStepDefs.class);

    @Дано("^Открыть страницу (.*) в браузере$")
    public void openPageInBrowser(String pageName) {
        String url = PageHolder.getPage(pageName).getUrl();
        WebDriverHolder.getDriver().get(url);
        // logger.info("Открыта страница - " + url);
    }

    @И("^Отображается текст (.*) на странице (.*)$")
    public void textDisplayed(String text, String pageName) {
        WebElement webElement = PageHolder.getPage(pageName).getElements().get(text);
        WaitFor.textToBePresentInElement(webElement, text);
    }

    @И("^Отображается меню (.*) на странице (.*)$")
    public void menuDisplayed(String menuName, String pageName) {
        WebElement webElement = PageHolder.getPage(pageName).getElements().get(menuName);
        WaitFor.elementToBeClickable(webElement);
        webElement.click();
    }

    @И("^Нажата кнопка (.*) на странице (.*)$")
    public void buttonPressed(String buttonName, String pageName) {
        WebElement webElement = PageHolder.getPage(pageName).getElements().get(buttonName);
        WaitFor.elementToBeClickable(webElement);
        webElement.click();
    }

    @И("^Нажата ссылка (.*) на странице (.*)$")
    public void linkPressed(String linkName, String pageName) {
        WebElement webElement = PageHolder.getPage(pageName).getElements().get(linkName);
        WaitFor.elementToBeClickable(webElement);
        webElement.click();
    }

    @И("^Заполнено поле (.*) на странице (.*) значением (.*)$")
    public void textBoxFilled(String textBoxName, String pageName, String value) {
        WebElement webElement;
        if(pageName.equals("ContactsBlock") && textBoxName.equals("Контакт")) {
            List<WebElement> webElements = PageHolder.getPage(pageName).getElementLists().get(textBoxName);
            webElement = webElements.get(webElements.size() - 1);
        }
        else
            webElement = PageHolder.getPage(pageName).getElements().get(textBoxName);
        WaitFor.elementToBeClickable(webElement);
        webElement.click();
        webElement.clear();
        webElement.sendKeys(value);
    }

    @И("^Выбрано из списка (.*) на странице (.*) значение (.*)$")
    public void optionItemSelected(String selectName, String pageName, String value) {
        switch(selectName) {
            case "Страна":
                WebElement selCountryItemCurrent = PageHolder.getPage(pageName).getElements().get("Текущее значение в поле Страна");
                WaitFor.elementToBeClickable(selCountryItemCurrent);
                selCountryItemCurrent.click();

                String selCountryItemXpath = ".//button[@title='" + value + "']";
                WebElement selCountryItem = WebDriverHolder.getDriver().findElement(By.xpath(selCountryItemXpath));
                WaitFor.elementToBeClickable(selCountryItem);
                selCountryItem.click();
                break;
            case "Город":
                WebElement selCityItemDefault = PageHolder.getPage(pageName).getElements().get("Дефолтное значение в поле Город");
                WaitFor.elementToBeClickable(selCityItemDefault);
                selCityItemDefault.click();

                String selCityItemXpath = ".//button[@title='" + value + "']";
                WebElement selCityItem = WebDriverHolder.getDriver().findElement(By.xpath(selCityItemXpath));
                WaitFor.elementToBeClickable(selCityItem);
                selCityItem.click();
                break;
            case "Способы связи":
                WebElement selConType = PageHolder.getPage(pageName).getElements().get("Способ связи");
                WaitFor.elementToBeClickable(selConType);
                selConType.click();

                WebElement selConTypeList = PageHolder.getPage(pageName).getElements().get("Способы связи");
                WaitFor.elementToBeClickable(selConTypeList);
                String selConTypeItemXpath = ".//div[@class='lk-cv-block__select-options lk-cv-block__select-options_left js-custom-select-options-container']//button[contains(text(), '" + value + "')]";
                WebElement selConTypeItem = WebDriverHolder.getDriver().findElement(By.xpath(selConTypeItemXpath));
                selConTypeItem.click();
                break;
        }
    }

    @И("^Выбрано из переключателя (.*) на странице (.*) значение (.*)$")
    public void radioButtonItemSelected(String radioButtonName, String pageName, String value) {
        switch(radioButtonName) {
            case "Готовность к переезду":
                if(value.equals("Да")) {
                    WebElement rbReadyToRelocate1 = PageHolder.getPage(pageName).getElements().get("Готовность к переезду - Да");
                    rbReadyToRelocate1.click();
                }
                if(value.equals("Нет")) {
                    WebElement rbReadyToRelocate0 = PageHolder.getPage(pageName).getElements().get("Готовность к переезду - Нет");
                    rbReadyToRelocate0.click();
                }
                break;
        }
    }

    @И("^Проставлен флажок (.*) на странице (.*) значением (.*)$")
    public void checkBoxChecked(String checkBoxName, String pageName, String value) {
        WebElement checkBoxValue = PageHolder.getPage(pageName).getElements().get(checkBoxName + ", значение");
        WebElement checkBox = PageHolder.getPage(pageName).getElements().get(checkBoxName);
        if(value.equals("Да")) {
            if(checkBoxValue.getAttribute("checked") == null)
                checkBox.click();
            }
        if(value.equals("Нет"))  {
            if(checkBoxValue.getAttribute("checked") != null)
                checkBox.click();
        }
    }

    @То("^Проверить: Отображается текст (.*) на странице (.*)$")
    public void assertThatTextIs(String textName, String pageName) {
        WebElement webElement = PageHolder.getPage(pageName).getElements().get(textName);
        String actualValue = webElement.getAttribute("value");
        String expectedValue = textName;
        String errorMessage = "Fail! expectedValue: " + expectedValue + " != " + " actualValue: " + actualValue;
        Assert.assertEquals(errorMessage, expectedValue, expectedValue);
    }

    @То("^Проверить: В поле (.*) на странице (.*) отображается значение (.*)$")
    public void assertThatFieldValueIs(String textBoxName, String pageName, String value) {
        WebElement webElement = PageHolder.getPage(pageName).getElements().get(textBoxName);
        WaitFor.elementToBeClickable(webElement);
        String actualValue = webElement.getAttribute("value");
        String expectedValue = value;
        String errorMessage = "Fail! expectedValue: " + expectedValue + " != " + " actualValue: " + actualValue;
        Assert.assertEquals(errorMessage, expectedValue, expectedValue);
    }

    @То("^Проверить: В списке (.*) на странице (.*) отображается значение (.*)$")
    public void assertThatDropDownValueIs(String selectName, String pageName, String value ) {
        WebElement webElement = PageHolder.getPage(pageName).getElements().get(selectName);
        WaitFor.elementToBeClickable(webElement);
        String actualValue = webElement.getText();
        String expectedValue = value;
        String errorMessage = "Fail! expectedValue: " + expectedValue + " != " + " actualValue: " + actualValue;
        Assert.assertEquals(errorMessage, expectedValue, expectedValue);
    }

    @То("^Проверить: В переключателе (.*) на странице (.*) отображается значение (.*)$")
    public void assertThatRadioButtonValueIs(String radioButtonName, String pageName, String value ) {
        WebElement webElement;
        switch(radioButtonName) {
            case "Готовность к переезду":
                String actualValue = "";
                if(value.equals("Да")) {
                    WebElement rbReadyToRelocate1 = PageHolder.getPage(pageName).getElements().get("Готовность к переезду - Да, значение");
                    if(rbReadyToRelocate1.getAttribute("checked") != null)
                        actualValue = "Да";
                    else
                        actualValue = "Нет";
                }
                if(value.equals("Нет")) {
                    WebElement rbReadyToRelocate0 = PageHolder.getPage(pageName).getElements().get("Готовность к переезду - Нет, значение");
                    if(rbReadyToRelocate0.getAttribute("checked") != null)
                        actualValue = "Нет";
                    else
                        actualValue = "Да";
                }
                String expectedValue = value;
                String errorMessage = "Fail! expectedValue: " + expectedValue + " != " + " actualValue: " + actualValue;
                Assert.assertEquals(errorMessage, expectedValue, expectedValue);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + radioButtonName);
        }
    }

    @То("^Проверить: В чекбоксе (.*) на странице (.*) отображается значение (.*)$")
    public void assertThatCheckBoxValueIs(String checkBoxName, String pageName, String value ) {
        WebElement checkBoxValue = PageHolder.getPage(pageName).getElements().get(checkBoxName + ", значение");
        String actualValue = "";
        if (value.equals("Да")) {
            if (checkBoxValue.getAttribute("checked") != null)
                actualValue = "Да";
            else
                actualValue = "Нет";
        }
        if (value.equals("Нет")) {
            if (checkBoxValue.getAttribute("checked") != null)
                actualValue = "Нет";
            else
                actualValue = "Да";
        }
        String expectedValue = value;
        String errorMessage = "Fail! expectedValue: " + expectedValue + " != " + " actualValue: " + actualValue;
        Assert.assertEquals(errorMessage, expectedValue, expectedValue);
    }

    @И("Закрыть браузер")
    public void closeBrowser() {
        WebDriverHolder.getDriver().close();
    }

    @И("Открыть браузер")
    public void openBrowser() {
        String env = System.getProperty("browser", "chrome");
        // logger.info("env = " + env);
        WebDriverHolder.setDriver(WebDriverFactory.create(env));
        WebDriverHolder.getDriver().manage().window().maximize();
        // logger.info("Драйвер стартовал!");
        WaitFor.initWait(WebDriverHolder.getDriver(), 10, 100);
        PageHolder.initPages(WebDriverHolder.getDriver());
    }
}
