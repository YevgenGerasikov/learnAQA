package seleniumWebdriver.net.coursehunters;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


class MainSeleniumTest {

    protected WebDriver chromeDriver;
    protected WebDriverWait wait;

    @BeforeAll
    static void preparations() {
//  set Chrome driver location path
        System.setProperty("webdriver.chrome.driver", "D:\\[IDEA]\\Projects\\mySeleniumCode\\drivers\\chromedriver.exe");
    }

    @BeforeEach
//  initialisation objects
    void initialization() {
        chromeDriver = new ChromeDriver();
        wait = new WebDriverWait(chromeDriver, 5);
    }

    @AfterEach
//  close driver
    void closeAll() {
        chromeDriver.quit();
        chromeDriver = null;
    }

    @Test
    void myFirstTest() {
//        Go to page
        chromeDriver.navigate().to("https://www.google.com");
//        находим искомый елемент на странице и инициализируем обьект класса WebElement
        WebElement searchBox = chromeDriver.findElement(By.name("q"));
//        передаем значение пользовательского ввода функции .sendKeys()
        searchBox.sendKeys("Selenium webdriver");
//        эмулируем нажатие Enter
        searchBox.sendKeys(Keys.ENTER);
//        ожидаем пока заголовок страницы (title) не изменит название согласно условиям поиска
        wait.until(ExpectedConditions.titleContains("Selenium webdriver - Поиск в Google"));
    }

    @Test
    void loginToAdminPanel() {
//        Go to page
        chromeDriver.navigate().to("http://localhost/litecart/admin");
//        wait while page Title will contains search result
        wait.until(ExpectedConditions.titleContains("My Test Store"));
//        fill login field
        WebElement username = chromeDriver.findElement(By.name("username"));
        username.click();
        username.clear();
        username.sendKeys("admin");
//          fill password field
        WebElement password = chromeDriver.findElement(By.name("password"));
        password.click();
        password.clear();
        password.sendKeys("admin");
//        find and click on the "Login" button
        chromeDriver.findElement(By.cssSelector("#box-login > form > div.footer > button[name=login]")).click();
//        wait for admin page successful login alert
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert")));
        Assertions.assertTrue(chromeDriver.findElement(By.className("alert-success")).getText().contains("You are now logged in as admin"));
    }

    //    method for find and add data (such as text) to the input field
    public void fillDataField(String xpathSelector, String value) {
        WebElement inputFieldElement = chromeDriver.findElement(By.xpath(xpathSelector));
        inputFieldElement.click();
        inputFieldElement.clear();
        inputFieldElement.sendKeys(value);
    }

    //    method for select item from drop down list
    void selectFromDropDownList(String listLabel, String selectedItem) {
        new Select(chromeDriver.findElement(By.xpath("//label[text()='" + listLabel + "']/following-sibling::*//select"))).selectByVisibleText(selectedItem);
    }
}