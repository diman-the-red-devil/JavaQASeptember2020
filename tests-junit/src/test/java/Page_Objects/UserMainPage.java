package Page_Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

// Страница "Главная" после авторизации
// https://otus.ru/
public class UserMainPage extends PageObject{
    private Logger logger = LogManager.getLogger(UserMainPage.class);

    // Выпадашка с меню для перехода в "Личный кабинет"
    @FindBy(xpath = ".//div[@class='header2-menu__item header2-menu__item_small header2-menu__item_dropdown header2-menu__item_dropdown_no-border']")
    private WebElement showMenu;

    // Ссылка "Личный кабинет"
    @FindBy(xpath = ".//a[contains(text(), 'Личный кабинет')]")
    private WebElement linkPrivate;

    public UserMainPage(WebDriver driver) {
        super(driver);
    }

    // Ожидание загрузки страницы
    public void waitForUserMainPageLoad() {
        waitForElementToBeClickable(showMenu);
        logger.info("Вход выполнен");
        logger.info("Открыта страница - https://otus.ru");
    }

    // Переход на страницу "Личный кабинет"
    public void goToLearningPage() {
        showMenu.click();
        linkPrivate.click();
    }
}
