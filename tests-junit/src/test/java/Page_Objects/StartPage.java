package Page_Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

// Страница "Главная" до авторизации
// https://otus.ru/
public class StartPage extends PageObject {
    private Logger logger = LogManager.getLogger(StartPage.class);

    // Заголовок "Авторские онлайн‑курсы для профессионалов"
    @FindBy(xpath = ".//div/div/h1[contains(text(), 'Авторские онлайн‑курсы')]")
    private WebElement txtAuthorCourses;

    // Кнопка "Вход и регистрация"
    @FindBy(xpath = ".//button[@data-modal-id='new-log-reg']")
    private WebElement btnLoginReg;

    // url = https://otus.ru/
    private String url;

    public StartPage(WebDriver driver, String url) {
        super(driver);
        this.url = url;
    }

    // Ожидание загрузки страницы
    public void waitForStartPageLoad()     {
        waitForTextToBePresentInElement(txtAuthorCourses, "Авторские онлайн‑курсы");
        logger.info("Открыта страница Otus - " + url);
    }

    // Переход на форму "Вход и регистрация"
    public void goToLoginAndRegPage() {
        btnLoginReg.click();
    }
}
