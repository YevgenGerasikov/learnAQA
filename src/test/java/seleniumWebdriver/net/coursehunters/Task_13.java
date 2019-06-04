package seleniumWebdriver.net.coursehunters;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class Task_13 extends MainSeleniumTest {
/*Задание 13. Сделайте сценарий работы с корзиной
Сделайте сценарий для добавления товаров в корзину и удаления товаров из корзины.
Сценарий должен состоять из следующих частей:
1) открыть страницу какого-нибудь товара
2) добавить его в корзину
3) подождать, пока счётчик товаров в корзине обновится
4) вернуться на главную страницу, повторить предыдущие шаги ещё два раза, чтобы в общей сложности в корзине было 3 единицы товара
5) открыть корзину (в правом верхнем углу кликнуть по ссылке Checkout)
6) удалить все товары из корзины один за другим, после каждого удаления подождать, пока внизу обновится таблица
*/


    @Test
    void addToCartTest() {
//        open main shop page
        chromeDriver.get("http://localhost/litecart/");
//        maximize window
        chromeDriver.manage().window().maximize();
//        open products list
        chromeDriver.findElement(By.className("nav-pills")).click();
//        move cursor to product cart
        new Actions(chromeDriver).moveToElement(chromeDriver.findElement(By.xpath("//div[@class='product column hover-light' and @data-id='1']"))).perform();
//        open product page
        chromeDriver.findElement(By.xpath("//div[@class='product column hover-light' and @data-id='1']")).click();
        wait.until((WebDriver d) -> d.findElement(By.xpath("//button[@name='add_cart_product']")));
//        fill data and add to cart
        selectFromDropDownList("Size", "Medium +$2.50");
        fillDataField("//input[@name='quantity']", "2");
        chromeDriver.findElement(By.xpath("//button[@name='add_cart_product']")).click();
//        close pop-up window
        chromeDriver.findElement(By.xpath("//div[@aria-label='Close']")).click();
//        scroll page up
        ((JavascriptExecutor) chromeDriver).executeScript("window.scrollTo(0, 0)");
//        wait for cart counter update
        wait.until((WebDriver d) -> d.findElement(By.xpath("//span[@class='quantity' and text()='2']")));
        System.out.println("Quantity = " + chromeDriver.findElement(By.xpath("//span[@class='quantity']")).getText());


//        Repeat steps 1-3 with product 2
//        move cursor to product cart
        new Actions(chromeDriver).moveToElement(chromeDriver.findElement(By.xpath("//div[@class='product column hover-light' and @data-id='2']"))).perform();
//        click on product cart
        chromeDriver.findElement(By.xpath("//div[@class='product column hover-light' and @data-id='2']")).click();
        wait.until((WebDriver d) -> d.findElement(By.xpath("//button[@name='add_cart_product']")));
//        fill data and add to cart
        fillDataField("//input[@name='quantity']", "3");
        chromeDriver.findElement(By.xpath("//button[@name='add_cart_product']")).click();
//        close pop-up window
        chromeDriver.findElement(By.xpath("//div[@aria-label='Close']")).click();
//        scroll page up
        ((JavascriptExecutor) chromeDriver).executeScript("window.scrollTo(0, 0)");
//        wait for cart counter update
        wait.until((WebDriver d) -> d.findElement(By.xpath("//span[@class='quantity' and text()='5']")));
        System.out.println("Quantity = " + chromeDriver.findElement(By.xpath("//span[@class='quantity']")).getText());
////        click on the Cart
//        chromeDriver.findElement(By.xpath("//div[@id='cart']/a")).click();


//        Repeat steps 1-3 with product 3
//        move cursor to product cart
        new Actions(chromeDriver).moveToElement(chromeDriver.findElement(By.xpath("//div[@class='product column hover-light' and @data-id='3']"))).perform();
//        open product page
        chromeDriver.findElement(By.xpath("//div[@class='product column hover-light' and @data-id='3']")).click();
        wait.until((WebDriver d) -> d.findElement(By.xpath("//button[@name='add_cart_product']")));
//        add to cart
        chromeDriver.findElement(By.xpath("//button[@name='add_cart_product']")).click();
//        close pop-up window
        chromeDriver.findElement(By.xpath("//div[@aria-label='Close']")).click();
//        scroll page up
        ((JavascriptExecutor) chromeDriver).executeScript("window.scrollTo(0, 0)");
//        wait for cart counter update
        wait.until((WebDriver d) -> d.findElement(By.xpath("//span[@class='quantity' and text()='6']")));
        System.out.println("Quantity = " + chromeDriver.findElement(By.xpath("//span[@class='quantity']")).getText());


//        Cart actions
        chromeDriver.findElement(By.xpath("//div[@id='cart']/a")).click();
        wait.until((WebDriver d) -> d.getTitle().contains("Checkout"));
        wait.until((WebDriver d) -> d.findElements(By.xpath("//div[@class='table-responsive']//tbody/tr")).size() == 3);
//        delete 3-d product
        chromeDriver.findElement(By.xpath("//div[@class='table-responsive']//tbody/tr[3]/td/button")).click();
        wait.until((WebDriver d) -> d.findElements(By.xpath("//div[@class='table-responsive']//tbody/tr")).size() == 2);
//        delete 2-d product
        chromeDriver.findElement(By.xpath("//div[@class='table-responsive']//tbody/tr[2]/td/button")).click();
        wait.until((WebDriver d) -> d.findElements(By.xpath("//div[@class='table-responsive']//tbody/tr")).size() == 1);
//        delete 1-st product
        chromeDriver.findElement(By.xpath("//div[@class='table-responsive']//tbody/tr[1]/td/button")).click();
        wait.until((WebDriver d) -> d.findElements(By.xpath("//div[@class='table-responsive']//tbody/tr")).size() == 0);
    }
}
