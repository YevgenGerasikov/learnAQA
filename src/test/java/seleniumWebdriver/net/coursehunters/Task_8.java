package seleniumWebdriver.net.coursehunters;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class Task_8 {
    /*Задание 8. Сделайте сценарий, проверяющий наличие стикеров у товаров
    Сделайте сценарий, проверяющий наличие стикеров у всех товаров в учебном приложении litecart на главной странице.
    Стикеры -- это полоски в левом верхнем углу изображения товара, на которых написано New или Sale или что-нибудь другое.
    Сценарий должен проверять, что у каждого товара имеется ровно один стикер.
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

    @AfterEach
//    close driver
    void closeAll() {
        chromeDriver.quit();
        chromeDriver = null;
    }

    @Test
//    Use search inside the element
    void stickerTest() {
//        Open shop main page
        chromeDriver.navigate().to("http://localhost/litecart/");
//        wait while page Title will contains search result
        wait.until(ExpectedConditions.titleContains("My Test Store | Online Store"));
//        find products cards and add them to the list
        List<WebElement> productsWebelementList = chromeDriver.findElements(By.cssSelector("div.product.column.hover-light"));
        System.out.println("Found " + productsWebelementList.size() + " cards.");
//        search for required items
        for (WebElement productCard : productsWebelementList) {
//            check stickers availability
            if (
                    (productCard.findElements(By.className("sticker"))).size() != 1
            ) {
                Assertions.fail("FAILED. Found an item without a sticker or with more than one sticker. " +
                        "Item tab = " +
                        (productCard.findElement(By.xpath("//div/parent::div[@class=\"tab-pane fade in\"]")).getAttribute("id"))
                        + ", item id = " +
                        (productCard.findElement(By.xpath("//div/ancestor::div[@class=\"product column hover-light\"]")).getAttribute("data-id")));
            }

        }
    }

}
