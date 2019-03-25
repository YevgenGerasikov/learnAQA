package seleniumWebdriver.net.coursehunters;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


class MainSeleniumTest {

    private WebDriver chromeDriver;
    private WebDriverWait wait;

    @BeforeAll
    static void preparations() {
        //указываем расположение драйвера
        System.setProperty("webdriver.chrome.driver", "D:\\[IDEA]\\Projects\\mySeleniumCode\\drivers\\chromedriver.exe");
    }

    @BeforeEach
    void initialization() {
        chromeDriver = new ChromeDriver();
        wait = new WebDriverWait(chromeDriver, 10);
    }

    @Test
    void myFirstTest() {
//        переход на страницу
        chromeDriver.navigate().to("https://www.google.com");
//        находим искомый елемент на странице и присваиваем инициализируем обьект класса WebElement
        WebElement searchBox = chromeDriver.findElement(By.name("q"));
//        передаем значение пользовательского ввода функции .sendKeys()
        searchBox.sendKeys("Selenium webdriver");
//        находим значение кнопки поиска на странице по имени елемента и эмулируем её нажатие используя функцию .click()
        chromeDriver.findElement(By.name("btnK")).click();
//        ожидаем пока заголовок страницы (title) не изменит название согласно условиям поиска
        wait.until(ExpectedConditions.titleContains("Selenium webdriver - Поиск в Google"));
    }

    @AfterEach
//    закрываем драйвер
    void closeAll() {
        chromeDriver.quit();
        chromeDriver = null;
    }
}