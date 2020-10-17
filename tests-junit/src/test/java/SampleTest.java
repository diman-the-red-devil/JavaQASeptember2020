import config.ServerConfig;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;


public class SampleTest {
    protected static WebDriver driver;
    private Logger logger = LogManager.getLogger(SampleTest.class);
    private ServerConfig cfg = ConfigFactory.create(ServerConfig.class);

    /*
    Варианты команд для запуска

    mvn clean test -Dbrowser='cHrOmE'
    mvn clean test -Dbrowser=cHrOmE
    mvn clean test -Dbrowser='chrome'
    mvn clean test -Dbrowser=chrome

    mvn clean test -Dbrowser='FiReFoX'
    mvn clean test -Dbrowser=FiReFoX
    mvn clean test -Dbrowser='firefox'
    mvn clean test -Dbrowser=firefox
     */

    // Читаем передаваемый параметр browser (-Dbrowser)
    String env = System.getProperty("browser", "chrome");

    @Before
    public void setUp() {
        logger.info("env = " + env);
        driver = WebDriverFactory.create(env);
        logger.info("Драйвер стартовал!");
    }

    @Test
    public void openPage() throws InterruptedException {
        driver.get(cfg.url());
        logger.info("Открыта страница Отус - " + cfg.url());
    }

    @After
    public void setDown() {
        if(driver != null) {
            driver.quit();
            logger.info("Драйвер остановлен!");
        }
    }
}
