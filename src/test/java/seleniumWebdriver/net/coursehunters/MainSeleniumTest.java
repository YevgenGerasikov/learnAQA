package seleniumWebdriver.net.coursehunters;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
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
//        находим значение кнопки поиска на странице по имени елемента и эмулируем её нажатие используя функцию .click()
        chromeDriver.findElement(By.cssSelector("div.FPdoLc.VlcLAe input.gNO89b[name=btnK]")).click();
//        ожидаем пока заголовок страницы (title) не изменит название согласно условиям поиска
        wait.until(ExpectedConditions.titleContains("Selenium webdriver - Поиск в Google"));
    }

    @Test
    void correctLoginToAdminPanel() {
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
//        wait for admin page menu content
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div#sidebar")));
//        find all menu items and add them to the Java list object
    }
}