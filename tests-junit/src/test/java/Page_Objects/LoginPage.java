package Page_Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

// Форма "Вход и регистрация"
public class LoginPage extends PageObject{
    private Logger logger = LogManager.getLogger(LoginPage.class);

    // Поле "Электронная почта"
    @FindBy(xpath = "//div[@class='new-log-reg__login']//input[@name='email']")
    private WebElement  tbxEmail;

    // Поле "Пароль"
    @FindBy(xpath = "//input[@name='password']")
    private WebElement  tbxPassword;

    // Кнопка "Войти"
    @FindBy(xpath = "//button[contains(text(),'Войти')]")
    private WebElement  btnEnter;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // Ожидание загрузки страницы
    public void waitForLoginPageLoad() {
        waitForElementToBeClickable(tbxEmail);
        logger.info("Открыта форма входа");
    }

    // Заполнение поля "Электронная почта"
    public void enterEmail(String email) {
        tbxEmail.click();
        tbxEmail.clear();
        tbxEmail.sendKeys(email);
        logger.info("Введен e-mail - " + email);
    }

    // Заполнение поля "Пароль"
    public void enterPassWord(String password) {
        tbxPassword.click();
        tbxPassword.clear();
        tbxPassword.sendKeys(password);
        logger.info("Введен password - " + password);
    }

    // Отправка данных формы
    public void submitLoginPassword() {
        btnEnter.click();
    }
}
