package Page_Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Map;

// Страница "Персональные данные"
// https://otus.ru/lk/biography/personal/
public class PersonalDataPage extends PageObject {
    private Logger logger = LogManager.getLogger(PersonalDataPage.class);

    // Заголовок раздела "Персональные данные"
    @FindBy(xpath = ".//h3[contains(text(), 'Персональные данные')]")
    private WebElement txtPersonalData;

    // Кнопка "Сохранить и продолжить"
    @FindBy(xpath = ".//button[contains(text(), 'Сохранить и продолжить')]")
    private WebElement btnSaveAndContinue;

    // Блок "ФИ и дата рождения" - Поля: "Имя", "Фамилия", "Имя (латиницей)", "Фамилия (латиницей)", "Имя (в блоге)", "Дата Рождения"
    private NameDateBlock nameDateBlock;
    // Блок "Основная информация" - Поля: "Страна", "Город", "Готовнссть к переезду"
    private LocationBlock locationBlock;
    // Блок "Формат работы" - Поля: "Полный день", "Гибкий график", "Удаленно"
    private WorkFormatBlock workFormatBlock;
    // Блок "Контактная информация"
    private ContactsBlock contactsBlock;

    public PersonalDataPage(WebDriver driver) {
        super(driver);
        nameDateBlock = new NameDateBlock(driver);
        locationBlock = new LocationBlock(driver);
        workFormatBlock = new WorkFormatBlock(driver);
        contactsBlock = new ContactsBlock(driver);
    }

    // Ожидание загрузки страницы
    public void waitForPersonalDataPageLoad() {
        waitForVisibilityOf(txtPersonalData);
        logger.info("Открыта страница - https://otus.ru/lk/biography/personal/");
    }

    // Заполнение блока "ФИ и дата рождения"
    public void fillNameDateBlock (Map<String, String> params) {
        nameDateBlock.enterFNameRu(params.get("fNameRu"));
        logger.info("Заполнено поле Имя - " + params.get("fNameRu"));
        nameDateBlock.enterLNameRu(params.get("lNameRu"));
        logger.info("Заполнено поле Фамилия - " + params.get("lNameRu"));
        nameDateBlock.enterFNameEn(params.get("fNameEn"));
        logger.info("Заполнено поле Имя (Латиницей) - " + params.get("fNameEn"));
        nameDateBlock.enterLNameEn(params.get("lNameEn"));
        logger.info("Заполнено поле Фамилия (латиницей) - " + params.get("lNameEn"));
        nameDateBlock.enterBlogName(params.get("blogName"));
        logger.info("Заполнено поле Имя (в блоге) - " + params.get("blogName"));
        nameDateBlock.enterDateOfBirth(params.get("dateOfBirth"));
        logger.info("Заполнено поле Дата рождения - " + params.get("dateOfBirth"));
    }

    // Заполнение блока "Основная информация"
    public void fillLocationBlock(String country, String city, boolean isReady) {
        locationBlock.enterCountry(country);
        logger.info("Заполнено поле Страна - " + country);
        locationBlock.enterCity(city);
        logger.info("Заполнено поле Город - " + city);
        locationBlock.enterReadyToRelocate(isReady);
        logger.info("Заполнено поле Готовность к переезду - " + isReady);
    }

    // Заполнение блока "Формат работы"
    public void fillWorkFormatBlock(boolean isFull, boolean isFlexible, boolean isRemote) {
        workFormatBlock.enterFull(isFull);
        logger.info("Заполнено поле Формат работы, Полный день - " + isFull);
        workFormatBlock.enterFlexible(isFlexible);
        logger.info("Заполнено поле Формат работы, Гибкий график - " + isFlexible);
        workFormatBlock.enterRemote(isRemote);
        logger.info("Заполнено поле Формат работы, Удаленно - " + isRemote);
    }

    // Заполнение блока "Контактная информация"
    public void fillContactsBlock(String skypeContact, String viberContact) {
        contactsBlock.enterContact("Skype", skypeContact);
        logger.info("Добавлен новый контакт Skype - " + skypeContact);
        contactsBlock.enterContact("Viber", viberContact);
        logger.info("Добавлен новый контакт Viber - " + viberContact);
    }

    // Отправка данных формы
    public void saveAndContinue() {
        btnSaveAndContinue.click();
    }

    // Получение ссылки на блок "ФИ и дата рождения"
    public NameDateBlock getNameDateBlock() {
        return nameDateBlock;
    }

    // Получение ссылки на блок "Основная информация"
    public LocationBlock getLocationBlock() {
        return locationBlock;
    }

    // Получение ссылки на блок "Формат работы"
    public WorkFormatBlock getWorkFormatBlock() {
        return workFormatBlock;
    }

    // Получение ссылки на блок "Контактная информация"
    public ContactsBlock getContactsBlock() {
        return contactsBlock;
    }

    // Блок "ФИ и дата рождения"
    public class NameDateBlock extends PageObject {
        // Поле "Имя"
        @FindBy(xpath = ".//input[@name='fname']")
        private WebElement tbxFNameRu;

        // Поле "Фамилия"
        @FindBy(xpath = ".//input[@name='lname']")
        private WebElement tbxLNameRu;

        // Поле "Имя (латиницей)"
        @FindBy(xpath = ".//input[@name='fname_latin']")
        private WebElement tbxFNameEn;

        // Поле "Фамилия (латиницей)"
        @FindBy(xpath = ".//input[@name='lname_latin']")
        private WebElement tbxLNameEn;

        // Поле "Имя (в блоге)"
        @FindBy(xpath = ".//input[@name='blog_name']")
        private WebElement tbxBlogName;

        // Поле "Дата рождения"
        @FindBy(xpath = ".//input[@name='date_of_birth']")
        private WebElement tbxDateOfBirth;

        public NameDateBlock(WebDriver driver) {
            super(driver);
        }

        // Заполнение поля "Имя"
        private void enterFNameRu(String fNameRu) {
            tbxFNameRu.click();
            tbxFNameRu.clear();
            tbxFNameRu.sendKeys(fNameRu);
        }

        // Заполнение поля "Фамилия"
        private void enterLNameRu(String lNameRu) {
            tbxLNameRu.click();
            tbxLNameRu.clear();
            tbxLNameRu.sendKeys(lNameRu);
        }

        // Заполнение поля "Имя (латиницей)"
        private void enterFNameEn(String fNameEn) {
            tbxFNameEn.click();
            tbxFNameEn.clear();
            tbxFNameEn.sendKeys(fNameEn);
        }

        // Заполнение поля "Фамилия (латиницей)"
        private void enterLNameEn(String lNameEn) {
            tbxLNameEn.click();
            tbxLNameEn.clear();
            tbxLNameEn.sendKeys(lNameEn);
        }

        // Заполнение поля "Имя (в блоге)"
        private void enterBlogName(String blogName) {
            tbxBlogName.click();
            tbxBlogName.clear();
            tbxBlogName.sendKeys(blogName);
        }

        // Заполнение поля "Дата рождения"
        private void enterDateOfBirth(String dateOfBirth) {
            tbxDateOfBirth.click();
            tbxDateOfBirth.clear();
            tbxDateOfBirth.sendKeys(dateOfBirth);
        }

        // Получение значения поля "Имя"
        public String getFNameRuValue() {
            return tbxFNameRu.getAttribute("value");
        }

        // Получение значения поля "Фамилия"
        public String getLNameRuValue() {
            return tbxLNameRu.getAttribute("value");
        }

        // Получение значения поля "Имя (латиницей)"
        public String getFNameEnValue() {
            return tbxFNameEn.getAttribute("value");
        }

        // Получение значения поля "Фамилия (латиницей)"
        public String getLNameEnValue() {
            return tbxLNameEn.getAttribute("value");
        }

        // Получение значения поля "Имя (в блоге)"
        public String getBlogNameValue() {
            return tbxBlogName.getAttribute("value");
        }

        // Получение значения поля "Дата рождения"
        public String getDateOfBirthValue() {
            return tbxDateOfBirth.getAttribute("value");
        }
    }

    // Блок "Основная информация"
    public class LocationBlock extends PageObject {
        // Текущее значение в поле "Страна"
        @FindBy(xpath = ".//p[contains(text(), 'Страна')]/../following-sibling::div//label/div")
        private WebElement selCountryItemCurrent;

        // Текущее значение в поле "Город"
        @FindBy(xpath = ".//p[contains(text(), 'Город')]/../following-sibling::div//label/div")
        private WebElement selCityItemCurrent;

        // Дефолтное значение в поле "Город"
        @FindBy(xpath = ".//p[contains(text(), 'Город')]/../following-sibling::div//label/div/span")
        private WebElement selCityItemDefault;

        // Поле "Готовность к переезду", значение "Нет"
        @FindBy(xpath = ".//input[@id='id_ready_to_relocate_0']/following-sibling::span")
        private WebElement  rbReadyToRelocate0;

        @FindBy(xpath = ".//input[@id='id_ready_to_relocate_0']")
        private WebElement  rbReadyToRelocate0Input;

        // Поле "Готовность к переезду", значение "Да"
        @FindBy(xpath = ".//input[@id='id_ready_to_relocate_1']/following-sibling::span")
        private WebElement  rbReadyToRelocate1;

        @FindBy(xpath = ".//input[@id='id_ready_to_relocate_1']")
        private WebElement  rbReadyToRelocate1Input;

        public LocationBlock(WebDriver driver) {
            super(driver);
        }

        // Заполнение поля "Страна"
        public void enterCountry(String country) {
            waitForElementToBeClickable(selCountryItemCurrent);
            selCountryItemCurrent.click();
            String selCountryItemXpath = ".//button[@title='" + country + "']";
            WebElement selCountryItem = driver.findElement(By.xpath(selCountryItemXpath));
            waitForElementToBeClickable(selCountryItem);
            selCountryItem.click();
        }

        // Заполнение поля "Город"
        public void enterCity(String city) {
            waitForElementToBeClickable(selCityItemDefault);
            selCityItemDefault.click();
            String selCityItemXpath = ".//button[@title='" + city + "']";
            WebElement selCityItem = driver.findElement(By.xpath(selCityItemXpath));
            waitForElementToBeClickable(selCityItem);
            selCityItem.click();
        }

        // Заполнение поля "Готовность к переезду"
        public void enterReadyToRelocate(boolean isReady) {
            if(isReady)
                rbReadyToRelocate1.click();
            else
                rbReadyToRelocate0.click();
        }

        // Получение значения поля "Страна"
        public String getCountry() {
            return selCountryItemCurrent.getText();
        }

        // Получение значения поля "Город"
        public String getCity() {
            return selCityItemCurrent.getText();
        }

        // Получение значения поля "Готовность к переезду", значение "Нет"
        public boolean getReadyToRelocate0() {
            String rbReadyToRelocate0Checked =  rbReadyToRelocate0Input.getAttribute("checked");
            return rbReadyToRelocate0Checked != null;
        }

        // Получение значения поля "Готовность к переезду", значение "Да"
        public boolean getReadyToRelocate1() {
            String rbReadyToRelocate1Checked =  rbReadyToRelocate1Input.getAttribute("checked");
            return rbReadyToRelocate1Checked != null;
        }
    }

    // Блок "Формат работы"
    public class WorkFormatBlock extends PageObject {
        // Поле "Полный день"
        @FindBy(xpath = ".//input[@title='Полный день']/following-sibling::span")
        private WebElement cbxFull;

        @FindBy(xpath = ".//input[@title='Полный день']")
        private WebElement cbxFullInput;

        // Поле "Гибкий график"
        @FindBy(xpath = ".//input[@title='Гибкий график']/following-sibling::span")
        private WebElement cbxFlexible;

        @FindBy(xpath = ".//input[@title='Гибкий график']")
        private WebElement cbxFlexibleInput;

        // Поле "Удаленно"
        @FindBy(xpath = ".//input[@title='Удаленно']/following-sibling::span")
        private WebElement cbxRemote;

        @FindBy(xpath = ".//input[@title='Удаленно']")
        private WebElement cbxRemoteInput;

        public WorkFormatBlock(WebDriver driver) {
            super(driver);
        }

        // Заполнение поля "Полный день"
        public void enterFull(boolean isFull) {
            if(isFull) {
                if(!getFull())
                    cbxFull.click();
            }
            else {
                if(getFull())
                    cbxFull.click();
            }

        }

        // Заполнение поля "Гибкий график"
        public void enterFlexible(boolean isFlexible) {
            if(isFlexible) {
                if(!getFlexible())
                    cbxFlexible.click();
            }
            else {
                if(getFlexible())
                    cbxFlexible.click();
            }
        }

        // Заполнение поля "Удаленно"
        public void enterRemote(boolean isRemote) {
            if(isRemote) {
                if(!getRemote())
                    cbxRemote.click();
            }
            else {
                if(getRemote())
                    cbxRemote.click();
            }
        }

        // Получение значения поля "Полный день"
        public boolean getFull() {
            String cbxFullChecked = cbxFullInput.getAttribute("checked");
            return cbxFullChecked != null;
        }

        // Получение значения поля "Гибкий график"
        public boolean getFlexible() {
            String cbxFlexibleChecked = cbxFlexibleInput.getAttribute("checked");
            return cbxFlexibleChecked != null;
        }

        // Получение значения поля "Удаленно"
        public boolean getRemote() {
            String cbxRemoteChecked = cbxRemoteInput.getAttribute("checked");
            return cbxRemoteChecked != null;
        }
    }

    // Блок "Контактная информация"
    public class ContactsBlock extends PageObject {
        // Кнопка "Добавить"
        @FindBy(xpath = ".//button[contains(text(), 'Добавить')]")
        private WebElement btnAddContact;

        // Поле "Способ связи"
        @FindBy(xpath = ".//span[contains(text(), 'Способ связи')]")
        private WebElement selConType;

        // Список способов связи
        @FindBy(xpath = ".//div[@class='lk-cv-block__select-options lk-cv-block__select-options_left js-custom-select-options-container']")
        private WebElement selConTypeList;

        // Поле "Контакт"
        @FindBy(xpath = ".//div[@class='lk-cv-block__select-options lk-cv-block__select-options_left js-custom-select-options-container hide']/../following-sibling::input")
        private List<WebElement> tbxConTypeValue;

        // Список контактов
        @FindBy(xpath = ".//div[@class='container__row js-formset-row']")
        private List<WebElement> listOfContacts;

        public ContactsBlock(WebDriver driver) {
            super(driver);
        }

        // Добавление контакта
        public void enterContact(String contactType, String value) {
            btnAddContact.click();
            waitForElementToBeClickable(selConType);
            selConType.click();
            waitForElementToBeClickable(selConTypeList);
            String selConTypeItemXpath = ".//div[@class='lk-cv-block__select-options lk-cv-block__select-options_left js-custom-select-options-container']//button[contains(text(), '" + contactType + "')]";
            WebElement selConTypeItem = driver.findElement(By.xpath(selConTypeItemXpath));
            selConTypeItem.click();
            tbxConTypeValue.get(tbxConTypeValue.size() - 1).sendKeys(value);
        }

        // Проверка "Существует ли заданный контакт"
        public boolean isContactExists(String contactType, String value) {
            for(WebElement webElement : listOfContacts) {
                String contactTypeXpath = ".//div/label/div[contains(text(), '" + contactType + "')]/../../following-sibling::input[@value='" + value + "']";
                WebElement contact;
                try {
                    contact = webElement.findElement(By.xpath(contactTypeXpath));
                } catch (NotFoundException e) {
                    logger.info("Контакт типа " + contactType + " с значением " + value + " не найден");
                    continue;
                }
                return contact != null;
            }
            return false;
        }
    }
}