package seleniumWebdriver.net.coursehunters;

import org.junit.jupiter.api.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Task_7 {
    /*Задание 7. Сделайте сценарий, проходящий по всем разделам админки
    Сделайте сценарий, который выполняет следующие действия в учебном приложении litecart.
    1) входит в панель администратора http://localhost/litecart/admin
    2) прокликивает последовательно все пункты меню слева, включая вложенные пункты
    3) для каждой страницы проверяет наличие заголовка
    */
    private WebDriver chromeDriver;
    private WebDriverWait wait;

    @BeforeAll
    static void preparations() {
        //set Chrome driver location path
        System.setProperty("webdriver.chrome.driver", "D:\\[IDEA]\\Projects\\mySeleniumCode\\drivers\\chromedriver.exe");
    }

    @BeforeEach
    void initialization() {
        chromeDriver = new ChromeDriver();
        wait = new WebDriverWait(chromeDriver, 5);
    }

    @Test
    void correctLogin() {
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
//        wait for page Title content
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div#sidebar")));
//        find all menu items and add them to the Java list object
        List<WebElement> menuList = chromeDriver.findElements(By.cssSelector("ul#box-apps-menu li"));
//        create list of menu items id's
        List<String> menuId = new ArrayList<>();
        for (WebElement menuElement : menuList) {
            menuId.add(menuElement.getAttribute("id"));
        }
//        create cycle for sequential passage through all menu items
        for (String menuIdElement : menuId) {
            chromeDriver.findElement(By.id(menuIdElement)).click();
//            check for submenu and add additional cycle
            if (chromeDriver.findElements(By.cssSelector("ul.docs li")).size() > 0) {
                List<WebElement> subMenuList = chromeDriver.findElements(By.cssSelector("ul.docs li"));
                List<String> subMenuId = new ArrayList<>();
                //fill subMenuId list
                for (WebElement submenuElement : subMenuList) {
                    subMenuId.add((submenuElement.getAttribute("id")));
                }
                //clicking submenu items
                for (String subMenuElementId : subMenuId) {
                    chromeDriver.findElement(By.id(subMenuElementId)).click();
                    //checking page title
                    if (chromeDriver.getTitle().equals("")) {
                        System.out.println("FAILED. Found page without title. Page URL is " + chromeDriver.getCurrentUrl());
                    } else {
                        System.out.println("Page title is " + chromeDriver.getTitle());
                    }
                }
            }
        }
    }

    @AfterEach
//    close driver
    void closeAll() {
        chromeDriver.quit();
        chromeDriver = null;
    }
}
