
import Page_Objects.*;
import config.ServerConfig;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;


public class SampleTest {
    protected static WebDriver driver;
    private Logger logger = LogManager.getLogger(SampleTest.class);
    private ServerConfig cfg = ConfigFactory.create(ServerConfig.class);

    /*
    Варианты команд для запуска
    // mvn clean test -Dlogin=login -Dpass=pass
    // mvn clean test -Dlogin=diman_the_red_devil@mail.ru -Dpass=JAKARTA12345-
    */

    // Читаем передаваемый параметр browser (-Dbrowser)
    String env = System.getProperty("browser", "chrome");
    // Читаем передаваемый параметр login (-Dlogin)
    // Логин - "diman_the_red_devil@mail.ru"
    String email = System.getProperty("login", "diman_the_red_devil@mail.ru");
    // Читаем передаваемый параметр browser (-Dpass)
    // Пароль - "JAKARTA12345-"
    String password = System.getProperty("pass", "JAKARTA12345-");

    // Тестовые данные
    // Блок "ФИ и дата рождения" - Поля: "Имя", "Фамилия", "Имя (латиницей)", "Фамилия (латиницей)", "Имя (в блоге)", "Дата Рождения"
    private static String fNameRu, lNameRu, fNameEn, lNameEn, blogName, dateOfBirth;
    // Блок "Основная информация" - Поля: "Страна", "Город", "Готовнссть к переезду"
    private static String country, city; boolean isReady;
    // Блок "Формат работы" - Поля: "Полный день", "Гибкий график", "Удаленно"
    private static boolean isFull, isFlexible, isRemote;
    // Блок "Контактная информация"
    private static String skypeContact, viberContact;
    // Мапа для передачи параметров
    private static Map<String, String> map;

    @Before
    public void setUp() {
        logger.info("env = " + env);
        driver = WebDriverFactory.create(env);
        logger.info("Драйвер стартовал!");
    }

    @Test
    public void openOtusSite() {
        // 0. Инициализация тестовых данных
        initializeTestData();
        // 1. Открыть https://otus.ru
        openStartPage();
        // 2. Авторизоваться на сайте
        authorize();
        // 3. Войти в личный кабинет
        gotoPersonalDataPage();
        // 4. В разделе "О себе" заполнить все поля "Личные данные" и добавить не менее двух контактов
        PersonalDataPage personalDataPage = new PersonalDataPage(driver);
        personalDataPage.waitForPersonalDataPageLoad();
        personalDataPage.fillNameDateBlock(map);
        personalDataPage.fillLocationBlock(country, city,  isReady);
        personalDataPage.fillWorkFormatBlock(isFull, isFlexible, isRemote);
        personalDataPage.fillContactsBlock(skypeContact, viberContact);
        // 5. Нажать сохранить
        personalDataPage.saveAndContinue();
        driver.close();
        // 6. Открыть https://otus.ru в "чистом браузере"
        driver = WebDriverFactory.create(env);
        openStartPage();
        // 7. Авторизоваться на сайте
        authorize();
        // 8. Войти в личный кабинет
        gotoPersonalDataPage();
        // 9. Проверить, что в разделе "О себе" отображаются указанные ранее данные
        checkPersonalDataPage();
        // Thread.sleep(2500);
    }

    // Инициализация тестовых данных
    private void initializeTestData() {
        fNameRu = "Дмитрий";
        lNameRu = "Ким";
        fNameEn = "Dmitriy";
        lNameEn = "Kim";
        blogName = "Дмитрий";
        dateOfBirth = "17.10.1990";
        map = new HashMap<>();
        map.put("fNameRu", fNameRu);
        map.put("lNameRu", lNameRu);
        map.put("fNameEn", fNameEn);
        map.put("lNameEn", lNameEn);
        map.put("blogName", blogName);
        map.put("dateOfBirth", dateOfBirth);
        country = "Россия";
        city = "Москва";
        isReady = true;
        isFull = false;
        isFlexible = true;
        isRemote = true;
        skypeContact = "skype";
        viberContact = "viber";
    }

    // Открытие страницы Otus
    private void openStartPage() {
        driver.manage().window().maximize();
        String  url = cfg.otus_url();
        driver.get(url);

        StartPage startPage = new StartPage(driver, url);
        startPage.waitForStartPageLoad();
        startPage.goToLoginAndRegPage();
    }

    // Авторизация
    private void authorize() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitForLoginPageLoad();

        loginPage.enterEmail(email);
        loginPage.enterPassWord(password);
        loginPage.submitLoginPassword();
    }

    // Вход в личный кабинет
    private void gotoPersonalDataPage() {
        UserMainPage userMainPage = new UserMainPage(driver);
        userMainPage.waitForUserMainPageLoad();
        userMainPage.goToLearningPage();

        LearningPage learningPage = new LearningPage(driver);
        learningPage.waitForLearningPageLoad();
        learningPage.goToPersonalDataPage();
    }

    // Проверка ранннее введенных данных в разделе "О себе"
    public void checkPersonalDataPage() {
        String expS, actS, msg;
        boolean expB, actB;

        PersonalDataPage personalDataPage = new PersonalDataPage(driver);
        personalDataPage.waitForPersonalDataPageLoad();
        // Проверка поля "Имя"
        expS = fNameRu; actS = personalDataPage.getNameDateBlock().getFNameRuValue();
        msg = "Fail! - " + expS + " != " + actS;
        Assert.assertEquals(msg, expS, actS);
        // Проверка поля "Фамилия"
        expS = lNameRu; actS = personalDataPage.getNameDateBlock().getLNameRuValue();
        msg = "Fail! - " + expS + " != " + actS;
        Assert.assertEquals(msg, expS, actS);
        // Проверка поля "Имя (латиницей)"
        expS = fNameEn; actS = personalDataPage.getNameDateBlock().getFNameEnValue();
        msg = "Fail! - " + expS + " != " + actS;
        Assert.assertEquals(msg, expS, actS);
        // Проверка поля "Фамилия (латиницей)"
        expS = lNameEn; actS = personalDataPage.getNameDateBlock().getLNameEnValue();
        msg = "Fail! - " + expS + " != " + actS;
        Assert.assertEquals(msg, expS, actS);
        // Проверка поля "Имя (в блоге)"
        expS = blogName; actS = personalDataPage.getNameDateBlock().getBlogNameValue();
        msg = "Fail! - " + expS + " != " + actS;
        Assert.assertEquals(msg, expS, actS);
        // Проверка поля "Дата рождения"
        expS = dateOfBirth; actS = personalDataPage.getNameDateBlock().getDateOfBirthValue();
        msg = "Fail! - " + expS + " != " + actS;
        Assert.assertEquals(msg, expS, actS);
        // Проверка поля "Страна"
        expS = country; actS = personalDataPage.getLocationBlock().getCountry();
        msg = "Fail! - " + expS + " != " + actS;
        Assert.assertEquals(msg, expS, actS);
        // Проверка поля "Город"
        expS = city; actS = personalDataPage.getLocationBlock().getCity();
        msg = "Fail! - " + expS + " != " + actS;
        Assert.assertEquals(msg, expS, actS);
        // Проверка поля "Готовность к переезду"
        expB = isReady; actB = personalDataPage.getLocationBlock().getReadyToRelocate1();
        msg = "Fail! - " + expB + " != " + actB;
        Assert.assertEquals(msg, expB, actB);
        // Проверка поля "Полный день"
        expB = isFull; actB = personalDataPage.getWorkFormatBlock().getFull();
        msg = "Fail! - " + expB + " != " + actB;
        Assert.assertEquals(msg, expB, actB);
        // Проверка поля "Гибкий график"
        expB = isFlexible; actB = personalDataPage.getWorkFormatBlock().getFlexible();
        msg = "Fail! - " + expB + " != " + actB;
        Assert.assertEquals(msg, expB, actB);
        // Проверка поля "Удаленно"
        expB = isRemote; actB = personalDataPage.getWorkFormatBlock().getRemote();
        msg = "Fail! - " + expB + " != " + actB;
        Assert.assertEquals(msg, expB, actB);
        // Проверка поля "Контакт"
        expB = true; actB = personalDataPage.getContactsBlock().isContactExists("Skype", skypeContact);
        msg = "Fail! - " + expB + " != " + actB;
        Assert.assertEquals(msg, expB, actB);
        // Проверка поля "Контакт"
        expB = true; actB = personalDataPage.getContactsBlock().isContactExists("Viber", viberContact);
        msg = "Fail! - " + expB + " != " + actB;
        Assert.assertEquals(msg, expB, actB);
    }

    @After
    public void setDown() {
        if(driver != null) {
            driver.quit();
            logger.info("Драйвер остановлен!");
        }
    }
}