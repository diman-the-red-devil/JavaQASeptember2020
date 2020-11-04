import config.ServerConfig;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;


public class SampleTest {
    protected static WebDriver driver;
    private Logger logger = LogManager.getLogger(SampleTest.class);
    private ServerConfig cfg = ConfigFactory.create(ServerConfig.class);

    // Читаем передаваемый параметр browser (-Dbrowser)
    String env = System.getProperty("browser", "chrome");

    @Before
    public void setUp() {
        logger.info("env = " + env);
        driver = WebDriverFactory.create(env);
        logger.info("Драйвер стартовал!");
    }

    // Добавление в сравнение двух смартфонов в Яндекс.Маркете
    @Test
    public void openPage() {
        WebDriverWait wait = new WebDriverWait(driver, 10, 1000);

        // 1 - Открыть в Chrome сайт Яндекс.Маркет - "Электроника"-> "Смартфоны"
        // Переходим на главную страницу Яндекс.Маркета
        driver.get(cfg.url());
        logger.info("Открыта страница Яндекс.Маркет - " + cfg.url());
        // Переходим в раздел "Электроника"
        driver.findElement(
                By.xpath(".//span[text() = 'Электроника']")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath(".//a[text() = 'Смартфоны']")));
        // Переходим в раздел "Смартфоны"
        driver.findElement(
                By.xpath(".//a[text() = 'Смартфоны']")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath(".//li//span[text() = 'Samsung']")));

        // 2 - Отфильтровать список товаров: Samsung и Xiaomi
        // Выбираем фильтр "Samsung"
        driver.findElement(
                By.xpath(".//li//span[text() = 'Samsung']")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath(".//li//span[text() = 'Xiaomi']")));
        // Выбираем фильтр "Xiaomi"
        driver.findElement(
                By.xpath(".//li//span[text() = 'Xiaomi']")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath(".//button[@data-autotest-id = 'dprice']")));

        // 3 - Отсортировать список товаров по цене (от меньшей к большей)
        // Выбираем сортировку товаров по цене (от меньшей к большей)
        driver.findElement(
                By.xpath(".//button[@data-autotest-id = 'dprice']")).click();
        // Думал завязаться на что-то более универсальное, но не получилось :(
        // Фронт просто перерисовывает блок со список товаров
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath(".//span[contains(text(), 'Смартфон Xiaomi Redmi Go 1/8GB')]")));

        // 4 - Добавить первый в списке Samsung
        // Отбираем в список все смартфоны Samsung
        logger.info("Список смартфонов Samsung: ");
        List<WebElement> samsungList = driver.findElements(
                By.xpath(".//span[contains(text(), 'Samsung')]"));
        for(WebElement samsungItem : samsungList) {
            logger.info(samsungItem.getText());
        }
        // Добавляем в сравнение первый в списке Samsung
        String samsungFirstItemName = samsungList.get(1).getText();
        String samsungXpath = ".//span[text() = '" + samsungFirstItemName + "']/ancestor::article//div[2]/div";
        driver.findElement(
                By.xpath(samsungXpath)).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath(".//div[@data-apiary-widget-id = '/content/popupInformer']/div")));

        // 5 -- Проверить, что отобразилась плашка "Товар {имя товара} добавлен к сравнению"
        // Проверяем, что отобразилась плашка с именем товара
        WebElement samsungItemAddedToCompare = driver.findElement(
                By.xpath(".//div[@data-apiary-widget-id = '/content/popupInformer']/div"));
        String samsungItemAddedToCompareText = samsungItemAddedToCompare.getText();
        logger.info(samsungItemAddedToCompareText);
        Assert.assertTrue(samsungItemAddedToCompareText.contains(samsungFirstItemName));

        // 6 - Добавить первый в списке Xiaomi
        // Отбираем в список все смартфоны Xiaomi
        logger.info("Список смартфонов Xiaomi: ");
        List<WebElement> xiaomiList = driver.findElements(
                By.xpath(".//span[contains(text(), 'Xiaomi')]"));
        for(WebElement xiaomiItem : xiaomiList) {
            logger.info(xiaomiItem.getText());
        }
        // Добавляем в сравнение первый в списке Xiaomi
        String xiaomiFirstItemName = xiaomiList.get(1).getText();
        String xiaomiXpath = ".//span[text() = '" + xiaomiFirstItemName + "']/ancestor::article//div[2]/div";
        driver.findElement(
                By.xpath(xiaomiXpath)).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath(".//div[@data-apiary-widget-id = '/content/popupInformer']/div")));

        // 7 -- Проверить, что отобразилась плашка "Товар {имя товара} добавлен к сравнению"
        // Проверяем, что отобразилась плашка с именем товара
        WebElement xiaomiItemAddedToCompare = driver.findElement(
                By.xpath(".//div[@data-apiary-widget-id = '/content/popupInformer']/div"));
        String xiaomiItemAddedToCompareText = xiaomiItemAddedToCompare.getText();
        logger.info(xiaomiItemAddedToCompareText);
        Assert.assertTrue(xiaomiItemAddedToCompareText.contains(xiaomiFirstItemName));

        // 8 - Перейти в раздел Сравнение
        // Переходим в раздаел "Сравнение"
        driver.findElement(
                By.xpath(".//span[text() = 'Сравнить']")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath(".//div[text() = 'Сравнение товаров']")));

        // 9 -- Проверить, что в списке товаров 2 позиции
        // Проверяем, что в списке товаров 2 позиции (1 Samsung + 1 Xiaomi)
        List<WebElement> itemsAddedToCompareCount = driver.findElements(
                By.xpath(".//div[@data-apiary-widget-id = '/content/compareContent']//img"));
        Assert.assertEquals(itemsAddedToCompareCount.size(), 2);
    }

    @After
    public void setDown() {
        if(driver != null) {
            driver.quit();
            logger.info("Драйвер остановлен!");
        }
    }
}
