package Page_Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

// Страница "Главная" после авторизации
// https://otus.ru/learning/
public class LearningPage extends PageObject {
    private Logger logger = LogManager.getLogger(LearningPage.class);

    // Заголовок "Личный кабинет"
    @FindBy(xpath = ".//h1[contains(text(), 'Личный кабинет')]")
    private WebElement txtPrivate;

    // Ссылка "О себе"
    @FindBy(xpath = ".//a[contains(text(), 'О себе')]")
    private WebElement linkAboutMe;

    public LearningPage(WebDriver driver) {
        super(driver);
    }

    // Ожидание загрузки страницы
    public void waitForLearningPageLoad() {
        waitForElementToBeClickable(txtPrivate);
        logger.info("Открыта страница - https://otus.ru/learning/");
    }

    // Переход на страницу "Персональные данные"
    public void goToPersonalDataPage() {
        linkAboutMe.click();
    }
}
